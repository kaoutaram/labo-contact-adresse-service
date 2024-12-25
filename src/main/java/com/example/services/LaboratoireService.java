package com.example.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.entities.Laboratoire;



public interface LaboratoireService {
	    List<Laboratoire> getAllLaboratoires();
	    Laboratoire saveLaboratoire(Laboratoire labo);
	    Laboratoire getLaboratoireById(Long id);
	    Long countLaboratoires();
	    void deleteLaboratoireById(Long id);
	    Laboratoire editLaboratoire(Long id,Laboratoire labo);
		List<Laboratoire> getActiveLaboratoires();
		void desactiverLaboratoire(Long id);
	
	
}
