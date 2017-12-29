package com.scc.onlinedogshow.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.onlinedogshow.model.Title;

@Repository
public interface TitleRepository extends CrudRepository<Title,String>  {
	
    public List<Title> findByIdDog(int idDog);

}
