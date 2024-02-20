import com.model.Fichier;
import com.model.Scan;
import com.repository.FichierRepository;
import com.repository.ScanRepository;
import com.service.FileScanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.InjectMocks;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FileScanServiceIntegrationTest {

    @Autowired
    private ScanRepository scanRepository;

    @Autowired
    private FichierRepository fichierRepository;

    @Autowired
    private FileScanService fileScanService;

    @Test
    void testReplayScan() throws IOException {
        // Création d'un scan original
        Scan originalScan = new Scan();
        originalScan.setScanDate(LocalDateTime.now());
        Set<Fichier> fichiers = new HashSet<>();
        originalScan.setFichiers(fichiers);
        Scan savedOriginalScan = scanRepository.save(originalScan);

        // Répétition du scan
        Path testPath = Paths.get("/test/directory");
        Scan replayedScan = fileScanService.replayScan(savedOriginalScan.getId(), testPath);

        // Vérification
        assertEquals(originalScan.getScanDate(), replayedScan.getScanDate());
        assertEquals(originalScan.getFichiers(), replayedScan.getFichiers());
    }
}
