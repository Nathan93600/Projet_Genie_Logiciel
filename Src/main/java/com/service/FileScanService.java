package com.service;

import com.repository.FichierRepository;
import com.repository.ScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Service
public class FileScanService {

    @Autowired
    private ScanRepository scanRepository;

    @Autowired
    private FichierRepository fichierRepository;

    public Scan scanDirectory(Path startPath) throws IOException {
        final Scan scan = new Scan();
        scan.setScanDate(LocalDateTime.now());
        Set<Fichier> fichiers = new HashSet<>();

        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Fichier fichier = new Fichier();
                fichier.setNom(file.getFileName().toString());
                fichier.setDateModification(LocalDateTime.ofInstant(attrs.lastModifiedTime().toInstant(), ZoneId.systemDefault()));
                fichier.setPoids(attrs.size());
                fichier.setType(Files.probeContentType(file));
                fichier.setRepertoire(file.getParent().toString());
                fichiers.add(fichier); // Ajoute le fichier à l'ensemble des fichiers du scan
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                // Optionnel : Logique spécifique pour la visite des répertoires, si nécessaire
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.err.println("Erreur lors de la visite du fichier: " + file.toString());
                return FileVisitResult.CONTINUE;
            }
        });

        scan.setFichiers(fichiers);
        fichiers.forEach(fichier -> fichier.setScan(scan)); // Associe chaque fichier au scan avant de sauvegarder

        return scanRepository.save(scan); // Sauvegarde le scan et automatiquement les fichiers grâce à la relation cascade
    }
}
