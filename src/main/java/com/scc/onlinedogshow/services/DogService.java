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
import com.scc.onlinedogshow.repository.TitleRepository;
import com.scc.onlinedogshow.template.Breed;
import com.scc.onlinedogshow.model.Breeder;
import com.scc.onlinedogshow.model.Owner;
import com.scc.onlinedogshow.template.Pedigree;
import com.scc.onlinedogshow.template.Parent;
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
	    		result.withId(_dog.getId() )
	    			.withName( _dog.getNom() )
	    			.withGender( _dog.getSexe() )
	    			.withBirthDate( _dog.getDateNaissance() )
	    			.withBirthCountry( _dog.getPays() )
	    			.withPedigrees( searchPedigrees ( _dog.getNumlof(), _dog.getDateConfirmation() ))
	    			.withTokens( searchTokens ( token))
	    			.withBreed( searchBreed(_dog))
	    			.withFather( searchParent( _dog.getEtalon()) )
	    			.withMother( searchParent( _dog.getLice()) )
	    			.withBreeder( searchBreeder ( _dog.getId() ))
	    			.withOwners( searchOwners ( _dog.getId() ))
	    			.withTitles( searchTitles ( _dog.getId() ))
	    		;
	    		
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
    
    private Parent searchParent (String _name) {
    	
    	Parent _parent = new Parent();
    	
    	try {
    		
    		if (_name != null)
    			_parent.setName(_name);
    		
    	} catch (Exception e) {
    		
    	}
    	
    	return _parent;
    }
    
    private Breeder searchBreeder(int _id) {
    	
    	Breeder _breeder = new Breeder();
    	
    	try {
    		_breeder = breederService.getBreederByIdDog( _id );
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
    
    private List<Pedigree> searchPedigrees(String _numLof, String _obtentionDate) {
    	
    	List<Pedigree> _pedigrees = new ArrayList<Pedigree>();
    	
    	try {
    		Pedigree _pedigree = new Pedigree();
    		_pedigree.setCountry("FR");
    		_pedigree.setType("LOF");
    		_pedigree.setNumber(_numLof);
    		_pedigree.setObtentionDate(_obtentionDate);
    		_pedigrees.add(_pedigree);
    	} catch (Exception e) {
    		
    	}
    	return _pedigrees;
    	
    }
    
    private Map<String, Object> searchTokens (String _token) {

    	Map<String, Object> tokens = new HashMap<String, Object>();
    	logger.debug("searchTokens: {},{}",_token);
    	try {
    		tokens.put("number", _token);

    	} catch (Exception e) {
    		
    	}
    	return tokens;

    }
    
    public void saveLicense(Dog license){

    }

    public void updateLicense(Dog license){

    }

    public void deleteLicense(Dog license){

    }

    private ResponseObjectList<ResponseObject> buildFallbackDogIdentifiant(String token){
    	
    	List<ResponseObject> list = new ArrayList<ResponseObject>(); 
    	list.add(new ResponseObject()
                .withId(0))
    	;
        return new ResponseObjectList<ResponseObject>(list.size(),list);
    }

}
