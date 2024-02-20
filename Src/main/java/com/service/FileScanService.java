package com.service;

import com.model.Fichier;
import com.model.Scan;
import com.repository.FichierRepository;
import com.repository.ScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;

@Service
public class FileScanService {

    private final ScanRepository scanRepository;
    private final FichierRepository fichierRepository;

    @Autowired
    public FileScanService(ScanRepository scanRepository, FichierRepository fichierRepository) {
        this.scanRepository = scanRepository;
        this.fichierRepository = fichierRepository;
    }

    public Scan scanDirectory(Path startPath) throws IOException {
        final Scan scan = new Scan();
        scan.setScanDate(LocalDateTime.now());
        Set<Fichier> fichiers = new HashSet<>();

        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Fichier fichier = createFichierFromFile(file, attrs);
                fichiers.add(fichier);
                return FileVisitResult.CONTINUE;
            }
        });

        scan.setFichiers(fichiers);
        fichiers.forEach(fichier -> fichier.setScan(scan));

        return scanRepository.save(scan);
    }

    private Fichier createFichierFromFile(Path file, BasicFileAttributes attrs) {
        Fichier fichier = new Fichier();
        fichier.setNom(file.getFileName().toString());
        fichier.setDateModification(LocalDateTime.ofInstant(attrs.lastModifiedTime().toInstant(), ZoneId.systemDefault()));
        fichier.setPoids(attrs.size());
        try {
            fichier.setType(Files.probeContentType(file));
        } catch (IOException e) {
            // Gérer l'exception de manière appropriée, par exemple en journalisant l'erreur
            fichier.setType("unknown");
        }
        fichier.setRepertoire(file.getParent().toString());
        return fichier;
    }

    public double calculerMoyenneTempsExecutionParFichier() {
        List<Fichier> fichiers = fichierRepository.findAll();
        OptionalDouble moyenne = fichiers.stream()
            .mapToDouble(Fichier::getExecutionTime) // Utilisation de la méthode getExecutionTime()
            .average();
    
        return moyenne.orElse(0);
    }

    public Scan replayScan(Long scanId, Path startPath) throws IOException {
        Scan originalScan = scanRepository.findById(scanId)
            .orElseThrow(() -> new RuntimeException("Scan not found with id: " + scanId));

        Scan newScan = new Scan();
        newScan.setScanDate(LocalDateTime.now());

        Set<Fichier> fichiers = new HashSet<>();
        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Fichier fichier = createFichierFromFile(file, attrs);
                fichier.setScan(newScan);
                fichiers.add(fichier);
                return FileVisitResult.CONTINUE;
            }
        });

        newScan.setFichiers(fichiers);
        return scanRepository.save(newScan);
    }
}
