import com.model.Fichier;
import com.model.Scan;
import com.repository.FichierRepository;
import com.repository.ScanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de tests unitaires pour la classe FileScanService.
 */
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

    /**
     * Méthode utilitaire pour créer un répertoire temporaire.
     * @return Le chemin du répertoire temporaire créé.
     * @throws IOException Si une erreur d'entrée-sortie survient lors de la création du répertoire temporaire.
     */
    private Path createTemporaryDirectory() throws IOException {
        return Files.createTempDirectory("test-directory");
    }

    /**
     * Teste la méthode scanDirectory de la classe FileScanService.
     * @throws IOException Si une erreur d'entrée-sortie survient lors de la création du répertoire temporaire.
     */
    @Test
    void testScanDirectory() throws IOException {
        // Given
        Path startPath = createTemporaryDirectory();
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

    // Ajoutez d'autres méthodes de test au besoin pour couvrir les autres méthodes de FileScanService
}
