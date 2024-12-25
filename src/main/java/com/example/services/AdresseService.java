package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entities.Adresse;
@Service
public interface AdresseService {
	    List<Adresse> getAllAdresses();
	    Adresse saveAdresse(Adresse adresse);
	    Adresse getAdresseById(Long id);
	    Long countAdresses();
	    void desactivateAdresseById(Long id);
	    Adresse editAdresse(Long id,Adresse adresse);
		List<Adresse> getActiveAdresses();
		void deleteAdressesByIds(List<Long> adresseIds);
		List<Long> getAdresseIdsByLaboratoireId(Long laboratoireId);
}
