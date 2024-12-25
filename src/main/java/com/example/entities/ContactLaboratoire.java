package com.example.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
public class ContactLaboratoire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numTel;
    private String fax;
    private String email;
    private Boolean isActive=true;

    
    @ManyToOne
    @JsonIgnoreProperties("contactsLaboratoire")
    @JoinColumn(name = "fkIdLaboratoire")
    @JsonBackReference
    private Laboratoire laboratoire;

    @OneToOne
    @JoinColumn(name = "fkIdAdresse")
    private Adresse adresse;
    
    
  //Constructeurs
  	public ContactLaboratoire(Long id, String numTel, String fax, String email, Boolean isActive,
  			Laboratoire laboratoire, Adresse adresse) {
  		super();
  		this.id = id;
  		this.numTel = numTel;
  		this.fax = fax;
  		this.email = email;
  		this.isActive = isActive;
  		this.laboratoire = laboratoire;
  		this.adresse = adresse;
  	}

  	public ContactLaboratoire(Long id, String numTel, String fax, String email, Boolean isActive) {
  		super();
  		this.id = id;
  		this.numTel = numTel;
  		this.fax = fax;
  		this.email = email;
  		this.isActive = isActive;
  	}

  	public ContactLaboratoire() {
  		super();
  	}
  	
    //Getters+Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Laboratoire getLaboratoire() {
		return laboratoire;
	}

	public void setLaboratoire(Laboratoire laboratoire) {
		this.laboratoire = laboratoire;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
    
    
    
    
}