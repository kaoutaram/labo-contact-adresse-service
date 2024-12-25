package com.example.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Adresse;
import com.example.services.AdresseService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/CrudAdresse/")
public class AdresseController {

    private final AdresseService adresseService;
    

    public AdresseController(AdresseService adresseService) {
		super();
		this.adresseService = adresseService;
	}

	
    @GetMapping("/adresses")
    public List<Adresse> getAdresses() {
        return adresseService.getActiveAdresses();
        
    }

    @PostMapping("/adresses")
    public Adresse createAdresse(@RequestBody Adresse adresse) {
        return adresseService.saveAdresse(adresse);
    }
    @PutMapping("/adresses/{id}")
    public Adresse updateAdresse(@PathVariable Long id, @RequestBody Adresse adresse) {
        return adresseService.editAdresse( id,adresse);
    }
    @PutMapping("/adresses/desactiver/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        adresseService.desactivateAdresseById(id); // Appel à la méthode du service
        return ResponseEntity.noContent().build(); // Réponse 204 No Content
    }
    @PostMapping("/adresses/delete")
    public ResponseEntity<Void> deleteAdresses(@RequestBody List<Long> adresseIds) {
        adresseService.deleteAdressesByIds(adresseIds);
        return ResponseEntity.ok().build();
    }

    // Nouvelle méthode pour récupérer les adresses associées à un laboratoire
    @GetMapping("/adresses/laboratoire/{laboratoireId}")
    public ResponseEntity<List<Long>> getAdresseIdsByLaboratoireId(@PathVariable Long laboratoireId) {
        List<Long> adresseIds = adresseService.getAdresseIdsByLaboratoireId(laboratoireId);
        return ResponseEntity.ok(adresseIds);
    }

}