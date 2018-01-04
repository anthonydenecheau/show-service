package com.scc.onlinedogshow.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.onlinedogshow.model.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner,String>  {
	
    public List<Owner> findByIdDog(int idDog);

}
