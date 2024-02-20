import com.model.Fichier;
import com.model.Scan;
import com.repository.FichierRepository;
import com.repository.ScanRepository;
import com.service.FileScanService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FileScanServiceUnitTest {

    @Mock
    private ScanRepository scanRepository;

    @Mock
    private FichierRepository fichierRepository;

    @InjectMocks
    private FileScanService fileScanService;

    @Test
    void testCalculerMoyenneTempsExecutionParFichier() {
        // Création de fichiers de test
        Fichier fichier1 = new Fichier();
        fichier1.setExecutionTime(10L);
        Fichier fichier2 = new Fichier();
        fichier2.setExecutionTime(20L);

        // Simulation de la réponse du repository
        when(fichierRepository.findAll()).thenReturn(java.util.Arrays.asList(fichier1, fichier2));

        // Vérification de la moyenne
        assertEquals(15.0, fileScanService.calculerMoyenneTempsExecutionParFichier());
    }

    @Test
    void testReplayScan() throws IOException {
        // Création d'un scan original
        Scan originalScan = new Scan();
        originalScan.setScanDate(LocalDateTime.now());
        Set<Fichier> fichiers = new HashSet<>();
        originalScan.setFichiers(fichiers);
        when(scanRepository.findById(1L)).thenReturn(Optional.of(originalScan));

        // Répétition du scan
        Path testPath = Paths.get("/test/directory");
        Scan replayedScan = fileScanService.replayScan(1L, testPath);

        // Vérification
        assertEquals(originalScan.getScanDate(), replayedScan.getScanDate());
        assertEquals(originalScan.getFichiers(), replayedScan.getFichiers());
    }
}
