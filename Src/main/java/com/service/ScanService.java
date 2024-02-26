package com.service;

import com.model.Scan;
import com.repository.ScanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

/**
 * Service class for managing Scan entities.
 */
@Service
@Transactional
public class ScanService {

    private final ScanRepository scanRepository;

    @Autowired
    public ScanService(ScanRepository scanRepository) {
        this.scanRepository = scanRepository;
    }

    /**
     * Creates a new scan.
     * @param scan The scan object to be created
     * @return Scan - The created scan object
     */
    public Scan createScan(Scan scan) {
        scan.setScanDate(LocalDateTime.now());
        return scanRepository.save(scan);
    }

    /**
     * Duplicates an existing scan.
     * @param scanId The ID of the scan to duplicate
     * @return Scan - The duplicated scan object
     * @throws RuntimeException If the scan with the specified ID is not found
     */
    public Scan duplicateScan(Long scanId) {
        Optional<Scan> originalScanOpt = scanRepository.findById(scanId);
        if (originalScanOpt.isPresent()) {
            Scan originalScan = originalScanOpt.get();
            Scan duplicatedScan = new Scan();

            duplicatedScan.setMaxFiles(originalScan.getMaxFiles());
            duplicatedScan.setMaxDepth(originalScan.getMaxDepth());
            duplicatedScan.setFileNameFilter(originalScan.getFileNameFilter());
            duplicatedScan.setFileTypeFilter(originalScan.getFileTypeFilter());
            duplicatedScan.setScanDate(LocalDateTime.now());
            duplicatedScan.setExecutionTime(null);

            return scanRepository.save(duplicatedScan);
        } else {
            throw new RuntimeException("Scan not found with id " + scanId);
        }
    }

    /**
     * Deletes a scan by its ID.
     * @param scanId The ID of the scan to delete
     */
    public void deleteScan(Long scanId) {
        scanRepository.deleteById(scanId);
    }

    /**
     * Updates a scan.
     * @param scanId The ID of the scan to update
     * @param scanDetails The updated details of the scan
     * @return Scan - The updated scan object
     * @throws RuntimeException If the scan with the specified ID is not found
     */
    public Scan updateScan(Long scanId, Scan scanDetails) {
        Scan scan = scanRepository.findById(scanId)
                .orElseThrow(() -> new RuntimeException("Scan not found with id " + scanId));

        scan.setMaxFiles(scanDetails.getMaxFiles());
        scan.setMaxDepth(scanDetails.getMaxDepth());
        scan.setFileNameFilter(scanDetails.getFileNameFilter());
        scan.setFileTypeFilter(scanDetails.getFileTypeFilter());

        return scanRepository.save(scan);
    }

    /**
     * Retrieves all scans.
     * @return List<Scan> - The list of all scans
     */
    public List<Scan> getAllScans() {
        return scanRepository.findAll();
    }

    /**
     * Retrieves a scan by its ID.
     * @param scanId The ID of the scan to retrieve
     * @return Scan - The retrieved scan object
     * @throws RuntimeException If the scan with the specified ID is not found
     */
    public Scan getScanById(Long scanId) {
        return scanRepository.findById(scanId)
                .orElseThrow(() -> new RuntimeException("Scan not found with id " + scanId));
    }

    /**
     * Calculates the average execution time per file for all scans.
     * @return double - The average execution time per file
     */
    @Transactional(readOnly = true)
    public double calculerMoyenneTempsExecutionParRepertoire() {
        List<Scan> scans = scanRepository.findAll();
        OptionalDouble moyenne = scans.stream()
                .filter(scan -> scan.getFichiers() != null && !scan.getFichiers().isEmpty())
                .mapToDouble(scan -> scan.getTempsExecutionTotal() / scan.getFichiers().size())
                .average();

        return moyenne.orElse(0);
    }
}
