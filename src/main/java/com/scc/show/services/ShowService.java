package com.scc.show.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.scc.show.config.ServiceConfig;
import com.scc.show.model.Show;
import com.scc.show.repository.ShowRepository;
import com.scc.show.template.ResponseObjectList;
import com.scc.show.template.ShowObject;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ShowService {

    private static final Logger logger = LoggerFactory.getLogger(ShowService.class);

    @Autowired
    private Tracer tracer;

    @Autowired
    private ShowRepository showRepository;
   
    @Autowired
    ServiceConfig config;

    @HystrixCommand(fallbackMethod = "buildFallbackShowList",
            threadPoolKey = "getShowsThreadPool",
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
    public ResponseObjectList<ShowObject> getShows(){

        Span newSpan = tracer.createSpan("getShows");
        logger.debug("In the showService.getShows() call, trace id: {}", tracer.getCurrentSpan().traceIdString());
        try {
        	
        	List<Show> list = new ArrayList<Show>(); 
        	list = showRepository.findAll();
        		
        	List<ShowObject> results = new ArrayList<ShowObject>();
	    	
	    	for (Show _show: list) {
	    		
	    		ShowObject result = new ShowObject();

		    	// Construction de la réponse
	    		result.withId(_show.getId() )
	    			.withTypeCode(_show.getTypeCode() )
	    			.withDescription( _show.getDescription() )
	    			.withYear( _show.getYear() )
	    			.withStartDate( _show.getStartDate() )
	    			.withEndDate( _show.getEndDate() )
	    			.withAddress( _show.getAddress())
	    			.withCity( _show.getCity() )
	    			.withZipCode( _show.getZipCode() )
	    			.withCity( _show.getCity() )
	    			.withOrganizingClubId( _show.getOrganizingClubId() )
	    		;
	    		
	    		results.add(result);
	    	}
	    	return new ResponseObjectList<ShowObject>(results.size(),results);
        }
	    finally{
	    	newSpan.tag("peer.service", "postgres");
	        newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
	        tracer.close(newSpan);
	    }
    }

    private ResponseObjectList<ShowObject> buildFallbackShowList(){
    	
    	List<ShowObject> list = new ArrayList<ShowObject>(); 
    	list.add(new ShowObject()
                .withId(0))
    	;
        return new ResponseObjectList<ShowObject>(list.size(),list);
    }
    
}
