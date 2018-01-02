package com.scc.onlinedogshow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.scc.onlinedogshow.config.ServiceConfig;
import com.scc.onlinedogshow.model.Dog;
import com.scc.onlinedogshow.model.Title;
import com.scc.onlinedogshow.repository.DogRepository;
import com.scc.onlinedogshow.template.Breed;
import com.scc.onlinedogshow.model.Breeder;
import com.scc.onlinedogshow.model.Owner;
import com.scc.onlinedogshow.template.Pedigree;
import com.scc.onlinedogshow.model.Parent;
import com.scc.onlinedogshow.template.ResponseObject;
import com.scc.onlinedogshow.template.ResponseObjectList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DogService {

    private static final Logger logger = LoggerFactory.getLogger(DogService.class);

    @Autowired
    private Tracer tracer;

    @Autowired
    private BreederService breederService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private ParentService parentService;

    @Autowired
    ServiceConfig config;

    public Dog getDogById(int dogId){
        Span newSpan = tracer.createSpan("getDogById");
        logger.debug("In the dogService.getDogById() call, trace id: {}", tracer.getCurrentSpan().traceIdString());
        try {
        	return dogRepository.findById(dogId);
        }
        finally{
          newSpan.tag("peer.service", "postgres");
          newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
          tracer.close(newSpan);
        }

    }

    @HystrixCommand(fallbackMethod = "buildFallbackDogIdentifiant",
            threadPoolKey = "dogByIdentifiantThreadPool",
            threadPoolProperties =
                    {@HystrixProperty(name = "coreSize",value="30"),
                     @HystrixProperty(name="maxQueueSize", value="10")},
            commandProperties={
                     @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
                     @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
                     @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
                     @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
                     @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")}
    )
    public ResponseObjectList<ResponseObject> getDogByToken(String token){

        Span newSpan = tracer.createSpan("getDogByToken");
        logger.debug("In the dogService.getDogByToken() call, trace id: {}", tracer.getCurrentSpan().traceIdString());
        try {
	    	/*
	        * norme ISO (FDXB) = 15 chiffres
	        */
	    	String regex = "^[0-9]{15}$";
	    	List<Dog> list = new ArrayList<Dog>(); 
	    	if (!token.matches(regex))
	    		list = dogRepository.findByTatouage(token);
	    	else
	    		list = dogRepository.findByTranspondeur(token);
	    
	    	List<ResponseObject> results = new ArrayList<ResponseObject>();
	    	ResponseObject result = new ResponseObject();
	    	for (Dog _dog : list) {
	    		
	    		// Construction de la réponse
	    		result.withId(_dog.getId() )
	    			.withGender( _dog.getSexe() )
	    			.withBirthDate( _dog.getDateNaissance() )
	    			.withBirthCountry( _dog.getPays() )
	    			.withPedigrees( searchPedigrees ( _dog.getNumlof(), _dog.getNumconfirmation(), _dog.getDateConfirmation() ))
	    			.withTokens( searchTokens ( _dog.getTatouage(), _dog.getTranspondeur()))
	    			.withBreed( searchBreed(_dog))
	    			.withFather( ( _dog.getIdEtalon() == 0 ? null : searchParent( _dog.getIdEtalon()) ))
	    			.withMother( ( _dog.getIdLice() == 0 ? null : searchParent( _dog.getIdLice()) ))
	    			.withBreeder( searchBreeder ( _dog.getId() ))
	    			.withOwners( searchOwners ( _dog.getId() ))
	    			.withTitles( searchTitles ( _dog.getId() ))
	    		;
	    		
	    		// Lecture de l'éleveur pour afficher le nom complet du chien
    			String _name = buildName (_dog.getNom()
    					, _dog.getAffixe()
    					, (result.getBreeder() == null ? "O" : result.getBreeder().getOnSuffixe())
    			);
	    		result.withName( _name );

	    		results.add(result);
	    	}
	    	return new ResponseObjectList<ResponseObject>(results.size(),results);
        }
	    finally{
	    	newSpan.tag("peer.service", "postgres");
	        newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
	        tracer.close(newSpan);
	    }
    }

    private Breed searchBreed (Dog _dog) {
    	
    	Breed _breed = new Breed();
    	
    	try  {
    		
	    	_breed.setId( _dog.getIdVariete() );
	    	_breed.setFciNumber( _dog.getCodeFci() );
	    	
	    	if (_dog.getRace() != null) {
		    	Map<String, Object> _name = new HashMap<String, Object>();
		    	_name.put("fr", _dog.getRace() );
		    	_breed.setName( _name );
	    	}
	    	
	    	if (_dog.getCouleur() != null) {
		    	Map<String, Object> _couleur = new HashMap<String, Object>();
		    	_couleur.put("fr", _dog.getCouleur() );
		    	_breed.setColor( _couleur );
	    	}
	
	    	if (_dog.getVariete() != null) {
		    	Map<String, Object> _variete = new HashMap<String, Object>();
		    	_variete.put("fr", _dog.getVariete() );
		    	_breed.setVariety( _variete );
	    	}

    	} catch (Exception e) {

    	}
    	return _breed;
    }

    private String buildName (String _name, String _affixe, String _onSuffixe) {
    	String result = "";
    	
    	try {
    		
    		if (_affixe != null && !"".equals(_affixe)) {
    			if (_onSuffixe.equals("O")) {
    				result = _name + " " + _affixe;
    			} else {
    				result = _affixe + " " + _name;    				
    			}
    		} else {
    			result = _name;
    		}
    		
    	} catch (Exception e) {
    		
    	}
    	
    	return result;
    }
    
    private HashMap<String, Object> searchParent (int _id) {
    	
    	HashMap<String, Object> _info = new HashMap<String, Object>();
    	Parent _parent = new Parent();
    	
    	try {
    		
    		_parent = parentService.getParentById(_id);
    		_info.put("name",buildName(_parent.getName(), _parent.getAffixe(), _parent.getOnSuffixe()));
    		/*
    		if (_parent.getAffixe() != null && !"".equals(_parent.getAffixe())) {
    			if (_parent.getOnSuffixe().equals("O")) {
    				_info.put("name", _parent.getName() + " " + _parent.getAffixe());
    			} else {
    				_info.put("name", _parent.getAffixe() + " " + _parent.getName());    				
    			}
    		} else {
    			_info.put("name", _parent.getName());
    		}
    		*/
    		
    	} catch (Exception e) {
    		
    	}
    	
    	return _info;
    }
    
    private Breeder searchBreeder(int _id) {
    	
    	Breeder _breeder = new Breeder();
    	
    	try {
    		_breeder = breederService.getBreederByIdDog( _id );
    		/*
    		 * Gestion de la règle particulier / eleveur (professionnel)
    		 * La raison sociale remplace le nom de l'éleveur; le prénom est réinitialisé. 
    		 */
    		if (_breeder.getTypeProfil().equals("E") 
    			&& _breeder.getProfessionnelActif().equals("O")
    			&& (_breeder.getRaisonSociale()!=null && !"".equals(_breeder.getRaisonSociale()) )) {
    			_breeder.setLastName(_breeder.getRaisonSociale());
    			_breeder.setFirstName("");
    		} 
    		
    	} catch (Exception e) {
    		
    	}
    	return _breeder;
    }

    private List<Owner> searchOwners(int _id) {
    	
    	List<Owner> _owners = new ArrayList<Owner>();
    	
    	try {
    		_owners.add(ownerService.getOwnerByIdDog( _id ));
    		
    	} catch (Exception e) {
    		
    	}
    	return _owners;
    }

    private List<Title> searchTitles(int _id) {
    	
    	List<Title> _titles = new ArrayList<Title>();
    	
    	try {
    		_titles = titleService.getTitlesByIdDog( _id );
    	} catch (Exception e) {
    		
    	}
    	return _titles;
    	
    }
    
    private List<Pedigree> searchPedigrees(String _numLof, String _numconfirmation, String _obtentionDate) {
    	
    	List<Pedigree> _pedigrees = new ArrayList<Pedigree>();
    	
    	try {
    		Pedigree _pedigree = new Pedigree();
    		_pedigree.setCountry("FR");
    		_pedigree.setType("LOF");
    		_pedigree.setNumber(_numLof + ((_numconfirmation==null || "".equals(_numconfirmation)) ? "" : "/" + _numconfirmation));
    		_pedigree.setObtentionDate(_obtentionDate);
    		_pedigrees.add(_pedigree);
    	} catch (Exception e) {
    		
    	}
    	return _pedigrees;
    	
    }
    
    private List<Map<String, Object>> searchTokens (String _tattoo, String _chip) {

    	List<Map<String, Object>> tokens = new ArrayList<Map<String, Object>>();
    	Map<String, Object> token = new HashMap<String, Object>();
    	logger.debug("searchTokens: tattoo {}, chip {}",_tattoo,_chip);
    	try {
    		token.clear();
    		if (_tattoo != null && !"".equals(_tattoo) ) {
    			token.put("type", "tattoo");
    			token.put("number", _tattoo);
    			tokens.add(new HashMap(token));
    		}
    		token.clear();
    		if (_chip != null && !"".equals(_chip) ) {
    			token.put("type", "chip");
    			token.put("number", _chip);
    			tokens.add(new HashMap(token));
    		}

    	} catch (Exception e) {
    		
    	}
    	return tokens;

    }
    
    public void saveDog(Dog _dog){

    }

    public void updateDog(Dog _dog){

    }

    public void deleteDog(Dog _dog){

    }

    private ResponseObjectList<ResponseObject> buildFallbackDogIdentifiant(String token){
    	
    	List<ResponseObject> list = new ArrayList<ResponseObject>(); 
    	list.add(new ResponseObject()
                .withId(0))
    	;
        return new ResponseObjectList<ResponseObject>(list.size(),list);
    }

}
