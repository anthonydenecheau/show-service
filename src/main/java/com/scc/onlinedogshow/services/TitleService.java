package com.scc.onlinedogshow.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import com.scc.onlinedogshow.config.ServiceConfig;
import com.scc.onlinedogshow.model.Title;
import com.scc.onlinedogshow.repository.TitleRepository;

@Service
public class TitleService {

    private static final Logger logger = LoggerFactory.getLogger(TitleService.class);

    @Autowired
    private Tracer tracer;

    @Autowired
    private TitleRepository titleRepository;
    
    @Autowired
    ServiceConfig config;

    public List<Title> getTitlesByIdDog(int dogId){
        Span newSpan = tracer.createSpan("getTitlesByIdDog");
        logger.debug("In the breederService.getTitlesByIdDog call, trace id: {}", tracer.getCurrentSpan().traceIdString());
        try {
        	return titleRepository.findByIdDog(dogId);
        }
        finally{
          newSpan.tag("peer.service", "postgres");
          newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
          tracer.close(newSpan);
        }

    }
}
