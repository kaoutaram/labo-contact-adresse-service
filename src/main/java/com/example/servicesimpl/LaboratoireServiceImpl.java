package com.example.servicesimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entities.*;
import com.example.repositories.*;
import com.example.services.LaboratoireService;
@Service
public class LaboratoireServiceImpl implements LaboratoireService {
	private LaboratoireRepository   laborep;
	

	public LaboratoireServiceImpl(LaboratoireRepository laborep) {
		super();
		this.laborep = laborep;
	}


	@Override
	public List<Laboratoire> getAllLaboratoires() {
		
		return laborep.findAll();
	}


	@Override
	public Laboratoire saveLaboratoire(Laboratoire labo) {
		
		return laborep.save(labo);
	}


	@Override
	public Laboratoire getLaboratoireById(Long id) {
		// TODO Auto-generated method stub
		return laborep.getById(id);
	}


	@Override
	public Long countLaboratoires() {
		// TODO Auto-generated method stub
		return laborep.count();
	}


	@Override
	public void deleteLaboratoireById(Long id) {
		// TODO Auto-generated method stub
		laborep.deleteById(id);
	}


	@Override
	public Laboratoire editLaboratoire(Long id, Laboratoire labo) {
	    // Recherche du laboratoire existant
	    Laboratoire existingLabo = laborep.findById(id)
	            .orElseThrow(() -> new RuntimeException("Labo non trouvé avec l'ID : " + id));

	    // Mise à jour des champs
	    existingLabo.setNom(labo.getNom());
	    existingLabo.setNrc(labo.getNrc());
	    existingLabo.setDateActivation(labo.getDateActivation());
	    
	    // Si le logo a été modifié, mettre à jour le champ logo
	    if (labo.getLogo() != null && !labo.getLogo().isEmpty()) {
	        existingLabo.setLogo(labo.getLogo());
	    }

	    return laborep.save(existingLabo);  // Sauvegarde dans la base de données
	}


	@Override
	public List<Laboratoire> getActiveLaboratoires() {
		return laborep.findByIsActiveTrue();
	}
	@Override
	 public void desactiverLaboratoire(Long id) {
	        Laboratoire laboratoire = laborep.findById(id)
	            .orElseThrow(() -> new RuntimeException("Laboratoire non trouvé avec l'ID : " + id));
	        laboratoire.setIsActive(false);
	        laborep.save(laboratoire);
	    }

}
