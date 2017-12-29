package com.scc.onlinedogshow.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.onlinedogshow.model.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner,String>  {
	
    public Owner findByIdDog(int idDog);

}
