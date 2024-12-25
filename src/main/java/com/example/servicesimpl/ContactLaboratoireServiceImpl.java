package com.example.servicesimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entities.ContactLaboratoire;
import com.example.entities.Laboratoire;
import com.example.repositories.ContactLaboratoireRepository;
import com.example.services.ContactLaboratoireService;

@Service
public class ContactLaboratoireServiceImpl implements  ContactLaboratoireService{
    private ContactLaboratoireRepository contactlaborep;
    
    
	public ContactLaboratoireServiceImpl(ContactLaboratoireRepository contactlaborep) {
		super();
		this.contactlaborep = contactlaborep;
	}

	@Override
	public List<ContactLaboratoire> getAllContactLaboratoires() {
		// TODO Auto-generated method stub
		return contactlaborep.findAll();
	}

	@Override
	public ContactLaboratoire saveContactLaboratoire(ContactLaboratoire contactLaboratoire) {
		
		return contactlaborep.save(contactLaboratoire);
	}

	@Override
	public ContactLaboratoire getContactLaboratoireById(Long id) {
		// TODO Auto-generated method stub
		return contactlaborep.getById(id);
	}

	@Override
	public Long countContactLaboratoires() {
		
		return contactlaborep.count();
	}

	@Override
	public void deleteContactLaboratoireById(Long id) {
		
		contactlaborep.deleteById(id);
	}

	@Override
	public ContactLaboratoire editContactLaboratoire(Long id,ContactLaboratoire clabo) {
		// Rechercher l'adresse existante par son ID
	    ContactLaboratoire existingcontactlabo = contactlaborep.findById(id)
	            .orElseThrow(() -> new RuntimeException("Contactlabo non trouvée avec l'ID : " + id));
	    // Mettre à jour les champs de l'adresse existante avec les nouvelles valeurs
	    existingcontactlabo.setEmail(clabo.getEmail());
	    existingcontactlabo.setFax(clabo.getFax());
	    existingcontactlabo.setNumTel(clabo.getNumTel());
		return contactlaborep.save(existingcontactlabo);
	}

	@Override
	public List<ContactLaboratoire> getActiveContactLaboratoires() {
		// TODO Auto-generated method stub
		return contactlaborep.findByIsActiveTrue();
	}
	@Override
	public List<ContactLaboratoire> findContactsByLaboratoireId(Long laboratoireId) {
        return contactlaborep.findByLaboratoireId(laboratoireId);
    }
	@Override
	public void desactiverContactById(Long id) {
	    if (id != null) {
	        ContactLaboratoire contactLaboratoire = contactlaborep.findById(id)
	            .orElseThrow(() -> new RuntimeException("ContactLaboratoire non trouvé avec l'ID : " + id));
	        contactLaboratoire.setIsActive(false);
	        contactlaborep.save(contactLaboratoire); // Sauvegarde des modifications
	    } else {
	        throw new IllegalArgumentException("L'ID ne peut pas être nul.");
	    }
	}
	@Override
	 public List<ContactLaboratoire> getContactsByLaboratoire(Long laboratoireId) {
	        return contactlaborep.findContactsByLaboratoireIdAndIsActiveTrue(laboratoireId);
	    }

	}



