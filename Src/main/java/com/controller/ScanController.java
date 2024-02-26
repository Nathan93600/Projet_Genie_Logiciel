package com.controller;

import com.model.Scan;
import com.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe contrôleur pour gérer les opérations CRUD sur les entités Scan.
 */
@RestController
@RequestMapping("/api/scans")
public class ScanController {
    private final ScanService scanService;

    @Autowired
    public ScanController(ScanService scanService) {
        this.scanService = scanService;
    }

    /**
     * Point de terminaison pour créer un nouveau scan.
     * @param scan Le scan à créer
     * @return ResponseEntity<Scan> - L'objet scan créé avec le code de statut HTTP correspondant
     */
    @PostMapping
    public ResponseEntity<Scan> createScan(@RequestBody Scan scan) {
        Scan createdScan = scanService.createScan(scan);
        return new ResponseEntity<>(createdScan, HttpStatus.CREATED);
    }

    /**
     * Point de terminaison pour récupérer tous les scans.
     * @return ResponseEntity<List<Scan>> - Liste de tous les scans avec le code de statut HTTP correspondant
     */
    @GetMapping
    public ResponseEntity<List<Scan>> getAllScans() {
        List<Scan> scans = scanService.getAllScans();
        return new ResponseEntity<>(scans, HttpStatus.OK);
    }

    /**
     * Point de terminaison pour récupérer un scan par son ID.
     * @param id L'ID du scan à récupérer
     * @return ResponseEntity<Scan> - L'objet scan récupéré avec le code de statut HTTP correspondant
     */
    @GetMapping("/{id}")
    public ResponseEntity<Scan> getScanById(@PathVariable("id") Long id) {
        Scan scan = scanService.getScanById(id);
        return new ResponseEntity<>(scan, HttpStatus.OK);
    }

    /**
     * Point de terminaison pour mettre à jour un scan.
     * @param id L'ID du scan à mettre à jour
     * @param scanDetails Les détails mis à jour du scan
     * @return ResponseEntity<Scan> - L'objet scan mis à jour avec le code de statut HTTP correspondant
     */
    @PutMapping("/{id}")
    public ResponseEntity<Scan> updateScan(@PathVariable("id") Long id, @RequestBody Scan scanDetails) {
        Scan updatedScan = scanService.updateScan(id, scanDetails);
        return new ResponseEntity<>(updatedScan, HttpStatus.OK);
    }

    /**
     * Point de terminaison pour supprimer un scan par son ID.
     * @param id L'ID du scan à supprimer
     * @return ResponseEntity<Void> - Code de statut HTTP indiquant le succès ou l'échec
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScan(@PathVariable("id") Long id) {
        scanService.deleteScan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
