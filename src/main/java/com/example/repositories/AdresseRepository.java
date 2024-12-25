package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.entities.Adresse;
@RepositoryRestResource
public interface AdresseRepository extends JpaRepository<Adresse,Long>{
	List<Adresse> findByIsActiveTrue();
	  void deleteAllByIdIn(List<Long> ids);
	  @Modifying
	    @Query("UPDATE Adresse a SET a.isActive = false WHERE a.id IN :ids")
	    void updateIsActiveFalseByIds(@Param("ids") List<Long> ids);
}