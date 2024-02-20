import com.model.Scan;
import com.repository.ScanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ScanRepositoryIntegrationTest {

    @Autowired
    private ScanRepository scanRepository;

    @Test
    void testFindByNombreMaxFichiers() {
        // Création de scans de test
        Scan scan1 = new Scan();
        scan1.setMaxFiles(100);
        Scan scan2 = new Scan();
        scan2.setMaxFiles(200);
        scanRepository.save(scan1);
        scanRepository.save(scan2);

        // Recherche par nombre max de fichiers
        List<Scan> scans = scanRepository.findByNombreMaxFichiers(100);
        assertEquals(1, scans.size());
        assertEquals(100, scans.get(0).getMaxFiles());
    }

    // Ajoutez d'autres tests pour les autres méthodes de recherche
}
