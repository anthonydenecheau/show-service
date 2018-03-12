package com.scc.enci.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.enci.model.JudgeBreed;

@Repository
public interface BreedRepository extends CrudRepository<JudgeBreed, String> {
	
    public List<JudgeBreed> findById(int id);

}
