package com.example.controllers;

import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.*;
import com.example.services.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/CrudContactLabo/")

public class ContactLaboController {
	private final ContactLaboratoireService contactlaboservice;
	public ContactLaboController(ContactLaboratoireService contactlaboservice) {
		super();
		this.contactlaboservice = contactlaboservice;
	}
	@PostMapping("/Contactslaboratoire")
	public ContactLaboratoire createContactLaboratoire(@RequestBody ContactLaboratoire Clabo) {
	    if (Clabo.getLaboratoire() == null || Clabo.getLaboratoire().getId() == null) {
	        throw new IllegalArgumentException("Le laboratoire associé est manquant ou invalide.");
	    }
	    return contactlaboservice.saveContactLaboratoire(Clabo);
	}

    
    @PutMapping("/Contactslaboratoire/{id}")
    public ContactLaboratoire updateContactLaboratoire(@PathVariable Long id, @RequestBody ContactLaboratoire labo) {
        return contactlaboservice.editContactLaboratoire(id,labo);
    }
	 @GetMapping("/Contactslaboratoire")
	    public List<ContactLaboratoire> getLaboratoires() {
	        return contactlaboservice.getActiveContactLaboratoires();
	        
	    }
	 @DeleteMapping("/Contactslaboratoire/{id}")
	 public ResponseEntity<Void> deleteContactLaboratoire(@PathVariable Long id) {
		 contactlaboservice.deleteContactLaboratoireById(id);
	     return ResponseEntity.noContent().build();
	 }
	   @GetMapping("/Contactslaboratoire/{laboratoireId}")
	    public ResponseEntity<List<ContactLaboratoire>> getContactsByLaboratoireId(@PathVariable Long laboratoireId) {
	        List<ContactLaboratoire> contacts = contactlaboservice.getContactsByLaboratoire(laboratoireId);
	        return ResponseEntity.ok(contacts);
	    }
	   @PostMapping("/Contactslaboratoire/delete")
	   public ResponseEntity<Void> deleteContact(@RequestBody Long id) {
	       try {
	           contactlaboservice.desactiverContactById(id); // Appel à la méthode du service
	           return ResponseEntity.noContent().build(); // 204 No Content
	       } catch (RuntimeException e) {
	           return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(null); // 404 si ID non trouvé
	       } catch (Exception e) {
	           return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build(); // 500 en cas d'autre erreur
	       }
	   }
	   
	   @GetMapping("/Contactslaboratoire/adresse/{id}")
	   public ResponseEntity<Adresse> getAdresseByContactId(@PathVariable Long id) {
	       ContactLaboratoire contact = contactlaboservice.getContactLaboratoireById(id);
	       if (contact != null && contact.getAdresse() != null) {
	           return ResponseEntity.ok(contact.getAdresse());
	       } else {
	           return ResponseEntity.notFound().build();
	       }
	   }
	   @GetMapping("/Contactslaboratoire/contact/{id}")
	    public ResponseEntity<ContactLaboratoire> getContactById(@PathVariable Long id) {
	        ContactLaboratoire contact = contactlaboservice.getContactLaboratoireById(id);
	        if (contact != null) {
	            return ResponseEntity.ok(contact); // Retourne le contact si trouvé
	        } else {
	            return ResponseEntity.notFound().build(); // Retourne 404 si contact non trouvé
	        }
	    }

	   
	   

}
