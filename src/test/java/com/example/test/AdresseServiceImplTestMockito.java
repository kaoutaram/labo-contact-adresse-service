package com.example.test;

import com.example.entities.Adresse;
import com.example.repositories.AdresseRepository;
import com.example.services.AdresseService;
import com.example.servicesimpl.AdresseServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdresseServiceImplTestMockito {

    @Mock
    private AdresseRepository adresseRepository;

    @InjectMocks
    private AdresseServiceImpl adresseService; // Utilisez l'implémentation directe

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
    }

    @Test
    public void testGetAllAdresses() {
        // Préparez les données
        Adresse adresse = new Adresse();
        adresse.setId(1L);
        adresse.setNumVoie("123");
        adresse.setNomVoie("Rue Test");
        adresse.setIsActive(true);

        when(adresseRepository.findAll()).thenReturn(Arrays.asList(adresse));

        // Appelez la méthode
        List<Adresse> adresses = adresseService.getAllAdresses();

        // Vérifiez les résultats
        assertFalse(adresses.isEmpty(), "La liste des adresses ne doit pas être vide");
        assertEquals(1, adresses.size(), "Il devrait y avoir exactement une adresse");
        assertEquals("123", adresses.get(0).getNumVoie(), "Le numéro de voie devrait être '123'");
    }

    @Test
    public void testSaveAdresse() {
        // Préparez les données
        Adresse adresse = new Adresse();
        adresse.setNumVoie("456");

        when(adresseRepository.save(any(Adresse.class))).thenAnswer(invocation -> {
            Adresse saved = invocation.getArgument(0);
            saved.setId(1L); // Simulez l'ID généré
            return saved;
        });

        // Appelez la méthode
        Adresse savedAdresse = adresseService.saveAdresse(adresse);

        // Vérifiez les résultats
        assertNotNull(savedAdresse.getId(), "L'adresse sauvegardée devrait avoir un ID");
        assertEquals("456", savedAdresse.getNumVoie(), "Le numéro de voie devrait être '456'");
        verify(adresseRepository, times(1)).save(adresse);
    }

    @Test
    public void testGetAdresseById() {
        // Préparez les données
        Adresse adresse = new Adresse();
        adresse.setId(1L);
        adresse.setNumVoie("789");

        when(adresseRepository.findById(1L)).thenReturn(Optional.of(adresse));

        // Appelez la méthode
        Adresse foundAdresse = adresseService.getAdresseById(1L);

        // Vérifiez les résultats
        assertNotNull(foundAdresse, "L'adresse trouvée ne doit pas être nulle");
        assertEquals(1L, foundAdresse.getId(), "L'ID de l'adresse devrait correspondre");
        verify(adresseRepository, times(1)).findById(1L);
    }

    @Test
    public void testDesactivateAdresseById() {
        // Préparez les données
        Adresse adresse = new Adresse();
        adresse.setId(1L);
        adresse.setIsActive(true);

        when(adresseRepository.findById(1L)).thenReturn(Optional.of(adresse));
        when(adresseRepository.save(any(Adresse.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Appelez la méthode
        adresseService.desactivateAdresseById(1L);

        // Vérifiez les résultats
        assertFalse(adresse.getIsActive(), "L'adresse devrait être désactivée");
        verify(adresseRepository, times(1)).findById(1L);
        verify(adresseRepository, times(1)).save(adresse);
    }

    @Test
    public void testGetActiveAdresses() {
        // Préparez les données
        Adresse adresseActive = new Adresse();
        adresseActive.setIsActive(true);

        Adresse adresseInactive = new Adresse();
        adresseInactive.setIsActive(false);

        when(adresseRepository.findAll()).thenReturn(Arrays.asList(adresseActive, adresseInactive));

        // Appelez la méthode
        List<Adresse> activeAdresses = adresseService.getActiveAdresses();

        // Vérifiez les résultats
        assertEquals(1, activeAdresses.size(), "Il devrait y avoir une seule adresse active");
        assertTrue(activeAdresses.get(0).getIsActive(), "L'adresse active devrait être marquée comme active");
    }
}
