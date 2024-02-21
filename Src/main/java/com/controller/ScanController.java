package com.controller;

import com.model.Scan;
import com.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scans")
public class ScanController {
    private final ScanService scanService;

    @Autowired
    public ScanController(ScanService scanService) {
        this.scanService = scanService;
    }

    @PostMapping
    public ResponseEntity<Scan> createScan(@RequestBody Scan scan) {
        Scan createdScan = scanService.createScan(scan);
        return new ResponseEntity<>(createdScan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Scan>> getAllScans() {
        List<Scan> scans = scanService.getAllScans();
        return new ResponseEntity<>(scans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scan> getScanById(@PathVariable("id") Long id) {
        Scan scan = scanService.getScanById(id);
        return new ResponseEntity<>(scan, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Scan> updateScan(@PathVariable("id") Long id, @RequestBody Scan scanDetails) {
        Scan updatedScan = scanService.updateScan(id, scanDetails);
        return new ResponseEntity<>(updatedScan, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScan(@PathVariable("id") Long id) {
        scanService.deleteScan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
