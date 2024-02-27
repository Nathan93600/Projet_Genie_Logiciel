package com.service;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import com.model.Fichier;
import com.model.Scan;
import com.repository.FichierRepository;
import com.repository.ScanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class FileScanServiceTest {

    @Mock
    private ScanRepository scanRepository;

    @Mock
    private FichierRepository fichierRepository;

    private FileScanService fileScanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        fileScanService = new FileScanService(scanRepository, fichierRepository);
    }

    private Path createTemporaryDirectory() throws IOException {
        return Files.createTempDirectory("test-directory");
    }

    @Test
    void testScanDirectory() throws IOException {
        // Given
        Path startPath = Files.createTempDirectory("test-dir");
        Scan scan = new Scan();
        scan.setScanDate(LocalDateTime.now());
        Set<Fichier> fichiers = new HashSet<>();
        Fichier fichier1 = new Fichier();
        // Initialiser votre objet Fichier selon vos besoins
        fichiers.add(fichier1);
        scan.setFichiers(fichiers);

        // When
        when(scanRepository.save(any(Scan.class))).thenReturn(scan);

        // Then
        Scan result = fileScanService.scanDirectory(startPath);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals(scan.getScanDate(), result.getScanDate());
        assertEquals(scan.getFichiers().size(), result.getFichiers().size());
        // Ajouter d'autres assertions en fonction de votre logique métier
    }

    // Add more test methods as needed to cover other methods in FileScanService
}
