package com.example.servicesimpl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.entities.Adresse;
import com.example.entities.ContactLaboratoire;
import com.example.repositories.AdresseRepository;
import com.example.repositories.ContactLaboratoireRepository;
import com.example.services.AdresseService;

@Service
public class AdresseServiceImpl implements AdresseService {
	private AdresseRepository   adresserep;
	private ContactLaboratoireRepository contactrep;

	public AdresseServiceImpl(AdresseRepository adresserep) {
		super();
		this.adresserep = adresserep;
	}

	@Override
	public List<Adresse> getAllAdresses() {
		
		return adresserep.findAll();
	}

	@Override
	public Adresse saveAdresse(Adresse adresse) {
		
		return adresserep.save(adresse);
	}

	@Override
	public Adresse getAdresseById(Long id) {

		return adresserep.getById(id);
	}

	@Override
	public Long countAdresses() {
		
		return adresserep.count();
	}

	@Override
	public void desactivateAdresseById(Long id) {
	    Adresse adresse = adresserep.getById(id); // Récupère l'adresse par ID
	    adresse.setIsActive(false); // Rendre l'adresse inactive
	    adresserep.save(adresse); // Sauvegarde la mise à jour
	}

		
	

	@Override
	public Adresse editAdresse(Long id, Adresse updatedAdresse) {
	    // Rechercher l'adresse existante par son ID
	    Adresse existingAdresse = adresserep.findById(id)
	            .orElseThrow(() -> new RuntimeException("Adresse non trouvée avec l'ID : " + id));

	    // Mettre à jour les champs de l'adresse existante avec les nouvelles valeurs
	    existingAdresse.setNumVoie(updatedAdresse.getNumVoie());
	    existingAdresse.setNomVoie(updatedAdresse.getNomVoie());
	    existingAdresse.setCodePostal(updatedAdresse.getCodePostal());
	    existingAdresse.setVille(updatedAdresse.getVille());
	    existingAdresse.setCommune(updatedAdresse.getCommune());

	    // Sauvegarder l'adresse mise à jour dans la base de données
	    return adresserep.save(existingAdresse);
	}
	@Override
	public List<Adresse> getActiveAdresses() {
        return adresserep.findByIsActiveTrue();
    }
	
	@Override
	public void deleteAdressesByIds(List<Long> adresseIds) {
	    if (adresseIds != null && !adresseIds.isEmpty()) {
	        adresserep.updateIsActiveFalseByIds(adresseIds);
	    } else {
	        throw new IllegalArgumentException("La liste des IDs d'adresses ne peut pas être vide ou nulle.");
	    }
	}

	@Override
	public List<Long> getAdresseIdsByLaboratoireId(Long laboratoireId) {
	    // Récupérer les contacts liés au laboratoire
	    List<ContactLaboratoire> contacts = contactrep.findByLaboratoireId(laboratoireId);

	    // Vérifier que la liste des contacts n'est pas nulle ou vide
	    if (contacts == null || contacts.isEmpty()) {
	        return Collections.emptyList(); // Retourner une liste vide si aucun contact n'est trouvé
	    }

	    // Extraire les IDs des adresses associées à ces contacts
	    return contacts.stream()
	                   .map(contact -> contact.getAdresse())  // Récupérer l'adresse de chaque contact
	                   .filter(Objects::nonNull)  // Filtrer les adresses nulles
	                   .map(Adresse::getId) // Extraire l'ID de chaque adresse
	                   .collect(Collectors.toList()); // Collecter les IDs dans une liste
	}

	


}
