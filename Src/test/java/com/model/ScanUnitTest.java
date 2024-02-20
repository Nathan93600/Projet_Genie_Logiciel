import com.model.Scan;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import com.model.Fichier;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScanUnitTest {

    @Test
    void testScanCreation() {
        Scan scan = new Scan();
        assertNotNull(scan);
    }

    @Test
    void testScanSettersAndGetters() {
        Scan scan = new Scan();
        scan.setId(1L);
        scan.setMaxFiles(100);
        scan.setMaxDepth(5);
        scan.setFileNameFilter("*.txt");
        scan.setFileTypeFilter("text/plain");
        LocalDateTime scanDate = LocalDateTime.now();
        scan.setScanDate(scanDate);
        scan.setExecutionTime(500L);
        scan.setScanPath("/path/to/scan");

        assertEquals(1L, scan.getId());
        assertEquals(100, scan.getMaxFiles());
        assertEquals(5, scan.getMaxDepth());
        assertEquals("*.txt", scan.getFileNameFilter());
        assertEquals("text/plain", scan.getFileTypeFilter());
        assertEquals(scanDate, scan.getScanDate());
        assertEquals(500L, scan.getExecutionTime());
        assertEquals("/path/to/scan", scan.getScanPath());
    }

    @Test
    void testGetTempsExecutionTotal() {
        Scan scan = new Scan();
        Set<Fichier> fichiers = new HashSet<>();
        Fichier fichier1 = new Fichier();
        fichier1.setExecutionTime(100L);
        Fichier fichier2 = new Fichier();
        fichier2.setExecutionTime(200L);
        fichiers.add(fichier1);
        fichiers.add(fichier2);
        scan.setFichiers(fichiers);

        assertEquals(300.0, scan.getTempsExecutionTotal());
    }
}
