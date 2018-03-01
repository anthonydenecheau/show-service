package com.scc.enci.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scc.enci.model.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner,String>  {
	
	public Owner findById(int id);
    public Owner findByIdDog(int idDog);

}
