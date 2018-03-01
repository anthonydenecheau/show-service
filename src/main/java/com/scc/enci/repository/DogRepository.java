package com.scc.enci.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.enci.model.Dog;

@Repository
public interface DogRepository extends CrudRepository<Dog,String>  {
	
    public Dog findById(int id);
    public List<Dog> findByTatouage(String toutage);
    public List<Dog> findByTranspondeur(String transpondeur);

    
}
