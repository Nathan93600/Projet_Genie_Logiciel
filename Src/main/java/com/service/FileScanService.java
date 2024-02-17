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
                Fichier fichier = new Fichier();
                fichier.setNom(file.getFileName().toString());
                fichier.setDateModification(LocalDateTime.ofInstant(attrs.lastModifiedTime().toInstant(), ZoneId.systemDefault()));
                fichier.setPoids(attrs.size());
                try {
                    fichier.setType(Files.probeContentType(file));
                } catch (IOException e) {
                    fichier.setType("unknown");
                }
                fichier.setRepertoire(file.getParent().toString());
                fichiers.add(fichier); // Ajoute le fichier à l'ensemble des fichiers du scan
                return FileVisitResult.CONTINUE;
            }
        });

        scan.setFichiers(fichiers);
        fichiers.forEach(fichier -> fichier.setScan(scan)); // Associe chaque fichier au scan avant de sauvegarder

        return scanRepository.save(scan); // Sauvegarde le scan et automatiquement les fichiers grâce à la relation cascade
    }

    public double calculerMoyenneTempsExecutionParFichier() {
        List<Fichier> fichiers = fichierRepository.findAll();
        OptionalDouble moyenne = fichiers.stream()
            .mapToDouble(Fichier::getTempsExecution)
            .average();

        return moyenne.isPresent() ? moyenne.getAsDouble() : 0;
    }

    public Scan replayScan(Long scanId) throws IOException {
        // Récupérer le scan existant à rejouer
        Scan originalScan = scanRepository.findById(scanId)
            .orElseThrow(() -> new RuntimeException("Scan not found with id: " + scanId));

        // Supposons que vous ayez une manière de récupérer le chemin du répertoire original du scan
        Path startPath = Paths.get(originalScan.getScanPath());

        // Créer un nouveau Scan pour enregistrer les résultats du scan rejoué
        Scan newScan = new Scan();
        newScan.setScanDate(LocalDateTime.now());
        // Ici, copiez d'autres configurations nécessaires du scan original au nouveau scan
        // Par exemple, si vous avez des filtres ou des configurations spécifiques, assurez-vous de les copier

        Set<Fichier> fichiers = new HashSet<>();
        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Fichier fichier = new Fichier();
                fichier.setNom(file.getFileName().toString());
                fichier.setDateModification(LocalDateTime.ofInstant(attrs.lastModifiedTime().toInstant(), ZoneId.systemDefault()));
                fichier.setPoids(attrs.size());
                try {
                    fichier.setType(Files.probeContentType(file));
                } catch (IOException e) {
                    fichier.setType("unknown");
                }
                fichier.setRepertoire(file.getParent().toString());
                fichier.setScan(newScan); // Associe chaque fichier au nouveau scan
                fichiers.add(fichier);
                return FileVisitResult.CONTINUE;
            }
        });

        newScan.setFichiers(fichiers);
        // Sauvegarder le nouveau scan dans la base de données
        return scanRepository.save(newScan);
    }
}
