package com.scc.enci.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.enci.model.Show;

@Repository
public interface ShowRepository extends CrudRepository<Show, String> {
	
    public List<Show> findAll();
    
}
