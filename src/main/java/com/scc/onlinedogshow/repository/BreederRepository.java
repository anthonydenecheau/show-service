package com.scc.onlinedogshow.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.onlinedogshow.model.Breeder;

@Repository
public interface BreederRepository extends CrudRepository<Breeder,String>  {
	
    public Breeder findByIdDog(int idDog);

}
