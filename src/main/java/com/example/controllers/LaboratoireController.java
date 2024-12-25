package com.example.controllers;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.entities.*;
import com.example.services.LaboratoireService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;


import java.io.FileNotFoundException;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/CrudLabo/")
public class LaboratoireController {
	private final LaboratoireService laboservice;
	  @Value("${uploads.logos.directory}")
	    private String uploadDir;

	public LaboratoireController(LaboratoireService laboservice) {
		super();
		this.laboservice = laboservice;
	}
	@PutMapping("/laboratoires/{id}")
	public Laboratoire updateLaboratoire(@PathVariable Long id, 
	                                     @RequestParam(value = "nom", required = false) String nom,
	                                     @RequestParam(value = "nrc", required = false) String nrc,
	                                     @RequestParam(value = "dateActivation", required = false) 
	                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateActivation,
	                                     @RequestParam(value = "logo", required = false) MultipartFile logo) throws IOException {
	    // Rechercher le laboratoire existant
	    Laboratoire existingLabo = laboservice.getLaboratoireById(id);

	    // Mettre à jour les champs de base (nom, nrc, dateActivation)
	    if (nom != null) {
	        existingLabo.setNom(nom);
	    }
	    if (nrc != null) {
	        existingLabo.setNrc(nrc);
	    }
	    if (dateActivation != null) {
	        existingLabo.setDateActivation(dateActivation);
	    }

	    // Si un nouveau logo est fourni, l'enregistrer et mettre à jour le champ logo
	    if (logo != null && !logo.isEmpty()) {
	        // Générer un nom unique pour le fichier
	        String fileName = UUID.randomUUID() + "_" + logo.getOriginalFilename();
	        
	        // Construire le chemin complet du fichier
	        Path uploadPath = Paths.get(uploadDir);
	        
	        // Créer le répertoire si nécessaire
	        Files.createDirectories(uploadPath);
	        
	        // Copier le fichier dans le répertoire de stockage
	        Files.copy(logo.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
	        
	        // Enregistrer le chemin ou le nom du fichier dans l'entité
	        existingLabo.setLogo(fileName);
	    }

	    // Sauvegarder le laboratoire mis à jour
	    return laboservice.saveLaboratoire(existingLabo);
	}

	
    @GetMapping("/laboratoires")
    public List<Laboratoire> getLaboratoires() {
        List<Laboratoire> laboratoires = laboservice.getActiveLaboratoires();
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        // Ajouter une URL complète pour le logo
        laboratoires.forEach(labo -> {
            if (labo.getLogo() != null) {
                labo.setLogo(baseUrl + "/api/CrudLabo/laboratoires/logo/" + labo.getLogo());
            }
        });

        return laboratoires;
    }

	
	 @DeleteMapping("/laboratoires/{id}")
	 public ResponseEntity<Void> deleteLaboratoire(@PathVariable Long id) {
	     laboservice.deleteLaboratoireById(id);
	     return ResponseEntity.noContent().build();
	 }
	 @PutMapping("/laboratoires/desactiver/{id}")
	    public ResponseEntity<Void> desactiverLaboratoire(@PathVariable Long id) {
		 laboservice.desactiverLaboratoire(id); // Appel à la méthode du service
	        return ResponseEntity.noContent().build(); // Réponse 204 No Content
	    }
	 @GetMapping("/laboratoires/{id}")
	 public ResponseEntity<Laboratoire> getLaboratoireById(@PathVariable Long id) {
	     Laboratoire laboratoire = laboservice.getLaboratoireById(id);
	     if (laboratoire != null) {
	         return ResponseEntity.ok(laboratoire); // Retourne 200 OK avec le laboratoire
	     } else {
	         return ResponseEntity.notFound().build(); // Retourne 404 Not Found si non trouvé
	     }
	 }
	 @PostMapping("/laboratoires")
	 public Laboratoire createLaboratoire(
		        @RequestParam("nom") String nom, 
		        @RequestParam("nrc") String nrc,
		        @RequestParam(value = "dateActivation", required = false) 
		            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateActivation,
		        @RequestParam(value = "logo", required = false) MultipartFile logo
		    ) throws IOException {
		        Laboratoire labo = new Laboratoire();
		        labo.setNom(nom);
		        labo.setNrc(nrc);
		        labo.setDateActivation(dateActivation);

		        if (logo != null && !logo.isEmpty()) {
		            // Générer un nom unique pour le fichier
		            String fileName = UUID.randomUUID() + "_" + logo.getOriginalFilename();
		            
		            // Construire le chemin complet du fichier
		            Path uploadPath = Paths.get(uploadDir);
		            
		            // Créer le répertoire si nécessaire
		            Files.createDirectories(uploadPath);
		            
		            // Copier le fichier dans le répertoire de stockage
		            Files.copy(logo.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		            
		            // Enregistrer le chemin ou le nom du fichier dans l'entité
		            labo.setLogo(fileName);
		        }

		        return laboservice.saveLaboratoire(labo);
		    }
	 @GetMapping("/laboratoires/logo/{fileName}")
	 public ResponseEntity<Resource> getLogo(@PathVariable String fileName) throws IOException {
	     Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
	     Resource resource = new UrlResource(filePath.toUri());

	     if (!resource.exists()) {
	         throw new FileNotFoundException("Fichier non trouvé : " + fileName);
	     }

	     return ResponseEntity.ok()
	             .contentType(MediaType.IMAGE_JPEG)
	             .body(resource);
	 }

	 
	 
}
