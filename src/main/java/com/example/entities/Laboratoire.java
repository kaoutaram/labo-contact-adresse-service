package com.example.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Laboratoire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String logo;
    private String nrc; // Numéro de registre de commerce
    private Boolean active=false;
    private LocalDateTime dateActivation;
    private Boolean isActive=true;
    @OneToMany(mappedBy = "laboratoire")
    @JsonManagedReference
    private List<ContactLaboratoire> contactsLaboratoire;
    
    //Consturcteurs
	public Laboratoire() {
		super();
	}
	public Laboratoire(Long id, String nom, String logo, String nrc, Boolean active, LocalDateTime dateActivation,
			Boolean isActive) {
		super();
		this.id = id;
		this.nom = nom;
		this.logo = logo;
		this.nrc = nrc;
		this.active = active;
		this.dateActivation = dateActivation;
		this.isActive = isActive;
	}
	public Laboratoire(Long id, String nom, String logo, String nrc, Boolean active, LocalDateTime dateActivation,
			Boolean isActive, List<ContactLaboratoire> contactsLaboratoire) {
		super();
		this.id = id;
		this.nom = nom;
		this.logo = logo;
		this.nrc = nrc;
		this.active = active;
		this.dateActivation = dateActivation;
		this.isActive = isActive;
		this.contactsLaboratoire = contactsLaboratoire;
	}
	//Getters+Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getNrc() {
		return nrc;
	}
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public LocalDateTime getDateActivation() {
		return dateActivation;
	}
	public void setDateActivation(LocalDateTime parsedDate) {
		this.dateActivation = parsedDate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public List<ContactLaboratoire> getContactsLaboratoire() {
		return contactsLaboratoire;
	}
	public void setContactsLaboratoire(List<ContactLaboratoire> contactsLaboratoire) {
		this.contactsLaboratoire = contactsLaboratoire;
	}
    
    
    
    
}