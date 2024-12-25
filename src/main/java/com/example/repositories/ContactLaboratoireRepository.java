package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.entities.*;
@RepositoryRestResource
public interface ContactLaboratoireRepository extends JpaRepository<ContactLaboratoire,Long> {
	List<ContactLaboratoire> findByIsActiveTrue();
	List<ContactLaboratoire>findByLaboratoireId(Long laboratoireId);
	void deleteAllByIdIn(List<Long> ids);
	List<ContactLaboratoire> findContactsByLaboratoireIdAndIsActiveTrue(Long laboratoireId);
	
}
