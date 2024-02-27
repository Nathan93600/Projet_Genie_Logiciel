package com.controller;

import com.model.Scan;
import com.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des scans.
 * Fournit des endpoints pour créer, récupérer, mettre à jour et supprimer des scans.
 */
@RestController
@RequestMapping("/api/scans")
public class ScanController {
    private final ScanService scanService;

    /**
     * Constructeur pour l'injection de dépendance du service de scan.
     *
     * @param scanService Le service de scan à injecter.
     */

    @Autowired
    public ScanController(ScanService scanService) {
        this.scanService = scanService;
    }

    /**
     * Crée un nouveau scan.
     *
     * @param scan L'entité scan à créer.
     * @return ResponseEntity contenant le scan créé et le statut HTTP CREATED.
     */

    @PostMapping
    public ResponseEntity<Scan> createScan(@RequestBody Scan scan) {
        Scan createdScan = scanService.createScan(scan);
        return new ResponseEntity<>(createdScan, HttpStatus.CREATED);
    }

    /**
     * Récupère tous les scans existants.
     *
     * @return ResponseEntity contenant la liste des scans et le statut HTTP OK.
     */

    @GetMapping
    public ResponseEntity<List<Scan>> getAllScans() {
        List<Scan> scans = scanService.getAllScans();
        return new ResponseEntity<>(scans, HttpStatus.OK);
    }

    /**
     * Récupère un scan par son identifiant.
     *
     * @param id L'identifiant du scan à récupérer.
     * @return ResponseEntity contenant le scan récupéré et le statut HTTP OK.
     */

    @GetMapping("/{id}")
    public ResponseEntity<Scan> getScanById(@PathVariable("id") Long id) {
        Scan scan = scanService.getScanById(id);
        return new ResponseEntity<>(scan, HttpStatus.OK);
    }

    /**
     * Met à jour un scan existant.
     *
     * @param id L'identifiant du scan à mettre à jour.
     * @param scanDetails Les détails du scan à mettre à jour.
     * @return ResponseEntity contenant le scan mis à jour et le statut HTTP OK.
     */

    @PutMapping("/{id}")
    public ResponseEntity<Scan> updateScan(@PathVariable("id") Long id, @RequestBody Scan scanDetails) {
        Scan updatedScan = scanService.updateScan(id, scanDetails);
        return new ResponseEntity<>(updatedScan, HttpStatus.OK);
    }

    /**
     * Supprime un scan par son identifiant.
     *
     * @param id L'identifiant du scan à supprimer.
     * @return ResponseEntity avec le statut HTTP NO_CONTENT.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScan(@PathVariable("id") Long id) {
        scanService.deleteScan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
