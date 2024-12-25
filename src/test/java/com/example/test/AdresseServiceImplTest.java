package com.example.test;

import com.example.entities.Adresse;
import com.example.repositories.AdresseRepository;
import com.example.services.AdresseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdresseServiceImplTest {
    @Autowired
    private AdresseService adresseService;
    @Autowired
    private AdresseRepository adresseRepository;
    // Préparation des données avant chaque test
    @BeforeEach
    public void setUp() {
        adresseRepository.deleteAll(); // Nettoyage des données existantes
        Adresse adresse = new Adresse();
        adresse.setNumVoie("123");
        adresse.setNomVoie("Rue Test");
        adresse.setCodePostal("12345");
        adresse.setVille("TestVille");
        adresse.setCommune("TestCommune");
        adresse.setIsActive(true);
        adresseRepository.save(adresse); // Ajout d'une adresse initiale
    }

    // Test : Récupérer toutes les adresses
    @Test
    public void testGetAllAdresses() {
        List<Adresse> adresses = adresseService.getAllAdresses();
        assertFalse(adresses.isEmpty(), "La liste des adresses ne doit pas être vide");
        assertEquals(1, adresses.size(), "Il devrait y avoir exactement une adresse");
    }

    // Test : Sauvegarder une nouvelle adresse
    @Test
    @Transactional  // Transactionnelle car on modifie la base de données
    public void testSaveAdresse() {
        Adresse adresse = new Adresse();
        adresse.setNumVoie("456");
        adresse.setNomVoie("Avenue Exemple");
        adresse.setCodePostal("67890");
        adresse.setVille("VilleEx");
        adresse.setCommune("CommuneEx");
        adresse.setIsActive(true);

        Adresse savedAdresse = adresseService.saveAdresse(adresse);

        assertNotNull(savedAdresse.getId(), "L'adresse sauvegardée devrait avoir un ID");
        assertEquals("456", savedAdresse.getNumVoie(), "Le numéro de voie devrait être '456'");
    }

    // Test : Récupérer une adresse par ID
    @Test
    @Transactional  // Transactionnelle car on modifie la base de données
    public void testGetAdresseById() {
        Adresse adresse = new Adresse();
        adresse.setNumVoie("789");
        adresse.setNomVoie("Boulevard Test");
        adresse.setCodePostal("11223");
        adresse.setVille("TestVille");
        adresse.setCommune("TestCommune");
        adresse.setIsActive(true);

        Adresse savedAdresse = adresseService.saveAdresse(adresse);
        Adresse foundAdresse = adresseService.getAdresseById(savedAdresse.getId());

        assertNotNull(foundAdresse, "L'adresse trouvée ne doit pas être nulle");
        assertEquals(savedAdresse.getId(), foundAdresse.getId(), "L'ID de l'adresse devrait correspondre");
    }

    // Test : Compter les adresses
    @Test
    @Transactional  // Transactionnelle car on modifie la base de données
    public void testCountAdresses() {
        long count = adresseService.countAdresses();
        assertEquals(1, count, "Le nombre d'adresses devrait être égal à 1");
    }

    // Test : Désactiver une adresse par ID
    @Test
    @Transactional  // Transactionnelle car on modifie la base de données
    public void testDesactivateAdresseById() {
        Adresse adresse = new Adresse();
        adresse.setNumVoie("555");
        adresse.setNomVoie("Rue Inactive");
        adresse.setCodePostal("55555");
        adresse.setVille("InactiveVille");
        adresse.setCommune("InactiveCommune");
        adresse.setIsActive(true);

        Adresse savedAdresse = adresseService.saveAdresse(adresse);
        adresseService.desactivateAdresseById(savedAdresse.getId());
        Adresse desactivatedAdresse = adresseService.getAdresseById(savedAdresse.getId());

        assertFalse(desactivatedAdresse.getIsActive(), "L'adresse devrait être désactivée");
    }

    // Test : Modifier une adresse
    @Test
    @Transactional  // Transactionnelle car on modifie la base de données
    public void testEditAdresse() {
        Adresse adresse = new Adresse();
        adresse.setNumVoie("333");
        adresse.setNomVoie("Rue Originale");
        adresse.setCodePostal("33333");
        adresse.setVille("OriginalVille");
        adresse.setCommune("OriginalCommune");
        adresse.setIsActive(true);

        Adresse savedAdresse = adresseService.saveAdresse(adresse);
        savedAdresse.setNumVoie("444");
        Adresse updatedAdresse = adresseService.editAdresse(savedAdresse.getId(), savedAdresse);

        assertEquals("444", updatedAdresse.getNumVoie(), "Le numéro de voie modifié devrait être '444'");
    }

    // Test : Récupérer les adresses actives
    @Test
    @Transactional  // Transactionnelle car on modifie la base de données
    public void testGetActiveAdresses() {
        adresseRepository.deleteAll(); // Assurer que le repository est propre pour ce test spécifique

        Adresse adresseActive = new Adresse();
        adresseActive.setNumVoie("888");
        adresseActive.setNomVoie("Boulevard Actif");
        adresseActive.setCodePostal("88888");
        adresseActive.setVille("ActiveVille");
        adresseActive.setCommune("ActiveCommune");
        adresseActive.setIsActive(true);

        Adresse adresseInactive = new Adresse();
        adresseInactive.setNumVoie("999");
        adresseInactive.setNomVoie("Boulevard Inactif");
        adresseInactive.setCodePostal("99999");
        adresseInactive.setVille("InactiveVille");
        adresseInactive.setCommune("InactiveCommune");
        adresseInactive.setIsActive(false);

        adresseService.saveAdresse(adresseActive);
        adresseService.saveAdresse(adresseInactive);

        List<Adresse> activeAdresses = adresseService.getActiveAdresses();

        assertEquals(1, activeAdresses.size(), "Il devrait y avoir une seule adresse active");
        assertTrue(activeAdresses.get(0).getIsActive(), "L'adresse active devrait être marquée comme active");
    }

}
