import com.model.Scan;
import com.repository.ScanRepository;
import com.service.ScanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ScanServiceIntegrationTests {

    @Autowired
    private ScanRepository scanRepository;

    @Autowired
    private ScanService scanService;

    @Test
    void testGetAllScans() {
        // Ajout de scans de test à la base de données
        Scan scan1 = new Scan();
        scan1.setMaxFiles(10);
        scan1.setMaxDepth(5);
        scanRepository.save(scan1);

        Scan scan2 = new Scan();
        scan2.setMaxFiles(20);
        scan2.setMaxDepth(10);
        scanRepository.save(scan2);

        // Récupération de tous les scans via le service
        List<Scan> scans = scanService.getAllScans();

        // Vérification
        assertEquals(2, scans.size());
    }

    // Ajoutez d'autres tests d'intégration pour les autres méthodes de ScanService
}
