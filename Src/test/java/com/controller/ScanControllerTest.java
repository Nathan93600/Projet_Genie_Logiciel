package com.controller;

import com.model.Scan;
import com.service.ScanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour ScanController.
 * Utilise Mockito pour simuler les interactions avec ScanService et tester uniquement la logique du contrôleur.
 */
class ScanControllerTest {

    @Mock
    private ScanService scanService; // Le service à mocker pour simuler les interactions

    @InjectMocks
    private ScanController scanController; // Le contrôleur à tester, avec les dépendances mockées injectées

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks avant chaque test
    }

    /**
     * Teste la création d'un scan.
     * Simule la réponse du service et vérifie que le contrôleur retourne le bon statut HTTP et corps de réponse.
     */
    @Test
    void testCreateScan() {
        Scan scan = new Scan();
        when(scanService.createScan(scan)).thenReturn(scan);

        ResponseEntity<Scan> response = scanController.createScan(scan);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(scan, response.getBody());
    }
    
    /**
     * Teste la récupération de tous les scans.
     * Vérifie que le contrôleur retourne correctement la liste des scans et le bon statut HTTP.
     */
    @Test
    void testGetAllScans() {
        List<Scan> scans = new ArrayList<>();
        when(scanService.getAllScans()).thenReturn(scans);

        ResponseEntity<List<Scan>> response = scanController.getAllScans();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(scans, response.getBody());
    }

    /**
     * Teste la récupération d'un scan par son ID.
     * Simule la réponse du service et vérifie que le contrôleur retourne le bon scan et statut HTTP.
     */
    @Test
    void testGetScanById() {
        Long id = 1L;
        Scan scan = new Scan();
        when(scanService.getScanById(id)).thenReturn(scan);

        ResponseEntity<Scan> response = scanController.getScanById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(scan, response.getBody());
    }

    /**
     * Teste la mise à jour d'un scan.
     * Simule la réponse du service et vérifie que le contrôleur retourne le scan mis à jour et le bon statut HTTP.
     */
    @Test
    void testUpdateScan() {
        Long id = 1L;
        Scan scan = new Scan();
        when(scanService.updateScan(id, scan)).thenReturn(scan);

        ResponseEntity<Scan> response = scanController.updateScan(id, scan);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(scan, response.getBody());
    }
    
    /**
     * Teste la suppression d'un scan.
     * Vérifie que le contrôleur appelle le service de suppression et retourne le bon statut HTTP.
     */
    @Test
    void testDeleteScan() {
        Long id = 1L;

        ResponseEntity<Void> response = scanController.deleteScan(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(scanService, times(1)).deleteScan(id);
    }
}
