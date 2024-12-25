package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.entities.*;

@RepositoryRestResource
public interface LaboratoireRepository extends JpaRepository<Laboratoire,Long> {
	List<Laboratoire> findByIsActiveTrue();
}
