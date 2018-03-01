package com.scc.enci.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scc.enci.model.Breeder;

@Repository
public interface BreederRepository extends CrudRepository<Breeder,String>  {
	
    public Breeder findById(int id);
    public Breeder findByIdDog(int idDog);

}
