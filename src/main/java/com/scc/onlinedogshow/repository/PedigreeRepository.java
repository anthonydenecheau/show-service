package com.scc.onlinedogshow.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.onlinedogshow.model.Pedigree;

@Repository
public interface PedigreeRepository extends CrudRepository<Pedigree,String>  {
	
    public List<Pedigree> findByIdDog(int idDog);

}
