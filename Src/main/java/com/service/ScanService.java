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
 * Service pour la gestion des scans, incluant la création, duplication, suppression,
 * mise à jour, récupération des scans et le calcul de la moyenne du temps d'exécution par répertoire.
 */
@Service
@Transactional
public class ScanService {

    @Autowired
    private ScanRepository scanRepository;

    /**
     * Constructeur pour l'injection de dépendance du ScanRepository.
     * @param scanRepository Le repository pour les opérations CRUD sur les entités Scan.
     */
    public ScanService(ScanRepository scanRepository) {
        this.scanRepository = scanRepository;
    }

    /**
     * Crée et sauvegarde un nouveau scan avec la date actuelle.
     * @param scan L'entité Scan à créer.
     * @return L'entité Scan sauvegardée.
     */
    public Scan createScan(Scan scan) {
        scan.setScanDate(LocalDateTime.now()); // Définir la date du scan à maintenant
        return scanRepository.save(scan);
    }

    /**
     * Duplique un scan existant, excluant l'ID et les fichiers associés, avec une nouvelle date de scan.
     * @param scanId L'ID du scan à dupliquer.
     * @return Le scan dupliqué sauvegardé.
     * @throws RuntimeException Si le scan original n'est pas trouvé.
     */
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

    /**
     * Supprime un scan par son identifiant.
     * @param scanId L'ID du scan à supprimer.
     */
    public void deleteScan(Long scanId) {
        scanRepository.deleteById(scanId);
    }

    /**
     * Met à jour un scan existant avec les détails fournis.
     * @param scanId L'ID du scan à mettre à jour.
     * @param scanDetails Les nouveaux détails du scan.
     * @return Le scan mis à jour et sauvegardé.
     * @throws RuntimeException Si le scan à mettre à jour n'est pas trouvé.
     */
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

    /**
     * Récupère tous les scans existants.
     * @return Une liste de tous les scans.
     */
    public List<Scan> getAllScans() {
        return scanRepository.findAll();
    }

    /**
     * Récupère un scan par son identifiant.
     * @param scanId L'ID du scan à récupérer.
     * @return L'entité Scan trouvée.
     * @throws RuntimeException Si le scan n'est pas trouvé.
     */
    public Scan getScanById(Long scanId) {
        return scanRepository.findById(scanId)
                .orElseThrow(() -> new RuntimeException("Scan not found with id " + scanId));
    }

    /**
     * Calcule la moyenne du temps d'exécution par fichier pour tous les répertoires scannés.
     * @return La moyenne du temps d'exécution.
     */
    @Transactional(readOnly = true)
    public double calculerMoyenneTempsExecutionParRepertoire() {
        List<Scan> scans = scanRepository.findAll();
        OptionalDouble moyenne = scans.stream()
                .filter(scan -> scan.getFichiers() != null && !scan.getFichiers().isEmpty())
                .mapToDouble(scan -> scan.getTempsExecutionTotal() / scan.getFichiers().size())
                .average();

        return moyenne.isPresent() ? moyenne.getAsDouble() : 0;
    }
}
