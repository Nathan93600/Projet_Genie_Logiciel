package com.service;
import com.example.demo.Application;
import com.model.Fichier;
import com.model.Scan;
import com.repository.FichierRepository;
import com.repository.ScanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class FileScanServiceIntegrationTest {

    @Autowired
    private FileScanService fileScanService;

    @MockBean
    private ScanRepository scanRepository;

    @MockBean
    private FichierRepository fichierRepository;

    @Test
    void testScanDirectory() throws IOException {
        // Setup
        Path testDirectory = Paths.get("src/test/resources");
        Set<Fichier> expectedFichiers = new HashSet<>();
        Fichier fichier1 = new Fichier();
        fichier1.setNom("test_file_1.txt");
        fichier1.setDateModification(LocalDateTime.now());
        fichier1.setPoids(1024L);
        fichier1.setType("text/plain");
        fichier1.setRepertoire(testDirectory.toString());
        expectedFichiers.add(fichier1);

        // Mocking
        Scan mockScan = new Scan();
        mockScan.setScanDate(LocalDateTime.now());
        mockScan.setFichiers(expectedFichiers);
        when(scanRepository.save(mockScan)).thenReturn(mockScan);

        // Test
        Scan scanned = fileScanService.scanDirectory(testDirectory);

        // Assertions
        assertEquals(mockScan.getScanDate(), scanned.getScanDate());
        assertEquals(mockScan.getFichiers().size(), scanned.getFichiers().size());
        assertEquals(expectedFichiers.iterator().next().getNom(), scanned.getFichiers().iterator().next().getNom());
        assertEquals(expectedFichiers.iterator().next().getPoids(), scanned.getFichiers().iterator().next().getPoids());
    }

    // Add more test methods for other functionalities of FileScanService as needed.
}
