package com.service;

import com.repository.ScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScanService {

    @Autowired
    private ScanRepository scanRepository;

    // Créer un nouveau scan
    public Scan createScan(Scan scan) {
        scan.setScanDate(LocalDateTime.now()); // Définir la date du scan à maintenant
        return scanRepository.save(scan);
    }

    // Dupliquer un scan existant
    public Scan duplicateScan(Long scanId) {
        Optional<Scan> originalScanOpt = scanRepository.findById(scanId);
        if (originalScanOpt.isPresent()) {
            Scan originalScan = originalScanOpt.get();
            Scan duplicatedScan = new Scan();

            // Copier les propriétés de l'original (sauf l'ID et les fichiers associés)
            duplicatedScan.setMaxFiles(originalScan.getMaxFiles());
            duplicatedScan.setMaxDepth(originalScan.getMaxDepth());
            duplicatedScan.setFileNameFilter(originalScan.getFileNameFilter());
            duplicatedScan.setFileTypeFilter(originalScan.getFileTypeFilter());
            duplicatedScan.setScanDate(LocalDateTime.now()); // Mettre la date actuelle pour le nouveau scan
            duplicatedScan.setExecutionTime(null); // L'exécution n'a pas encore eu lieu

            return scanRepository.save(duplicatedScan);
        } else {
            throw new RuntimeException("Scan not found with id " + scanId);
        }
    }

    // Supprimer un scan
    public void deleteScan(Long scanId) {
        scanRepository.deleteById(scanId);
    }

    // Mettre à jour un scan
    public Scan updateScan(Long scanId, Scan scanDetails) {
        Scan scan = scanRepository.findById(scanId)
                .orElseThrow(() -> new RuntimeException("Scan not found with id " + scanId));

        scan.setMaxFiles(scanDetails.getMaxFiles());
        scan.setMaxDepth(scanDetails.getMaxDepth());
        scan.setFileNameFilter(scanDetails.getFileNameFilter());
        scan.setFileTypeFilter(scanDetails.getFileTypeFilter());
        // Ne pas mettre à jour la date du scan et les fichiers associés ici

        return scanRepository.save(scan);
    }

    // Récupérer tous les scans
    public List<Scan> getAllScans() {
        return scanRepository.findAll();
    }

    // Récupérer un scan par son ID
    public Scan getScanById(Long scanId) {
        return scanRepository.findById(scanId)
                .orElseThrow(() -> new RuntimeException("Scan not found with id " + scanId));
    }
}
