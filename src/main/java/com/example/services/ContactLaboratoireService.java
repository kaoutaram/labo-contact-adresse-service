package com.example.services;

import java.util.List;

import com.example.entities.ContactLaboratoire;
import com.example.entities.Laboratoire;

public interface ContactLaboratoireService {


	    List<ContactLaboratoire> getAllContactLaboratoires();
	    ContactLaboratoire saveContactLaboratoire(ContactLaboratoire contactLaboratoire);
	    ContactLaboratoire getContactLaboratoireById(Long id);
	    Long countContactLaboratoires();
	    void deleteContactLaboratoireById(Long id);
	    ContactLaboratoire editContactLaboratoire(Long id,ContactLaboratoire contactLaboratoire);
		List<ContactLaboratoire> getActiveContactLaboratoires();
		List<ContactLaboratoire> findContactsByLaboratoireId(Long laboratoireId);
		//void deleteContactsByIds(List<Long> contactIds);
		void desactiverContactById(Long id);
		List<ContactLaboratoire> getContactsByLaboratoire(Long laboratoireId);
		
}
