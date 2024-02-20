import com.model.Scan;
import com.repository.ScanRepository;
import com.service.ScanService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ScanServiceUnitTests {

    @Mock
    private ScanRepository scanRepository;

    @InjectMocks
    private ScanService scanService;

    @Test
    void testCreateScan() {
        Scan scan = new Scan();
        scan.setScanDate(LocalDateTime.now());

        when(scanRepository.save(scan)).thenReturn(scan);

        Scan createdScan = scanService.createScan(scan);
        assertEquals(scan.getScanDate(), createdScan.getScanDate());
    }

    @Test
    void testDuplicateScan() {
        Long scanId = 1L;
        Scan originalScan = new Scan();
        originalScan.setMaxFiles(10);
        originalScan.setMaxDepth(5);
        originalScan.setFileNameFilter("*.txt");
        originalScan.setFileTypeFilter("text/plain");

        when(scanRepository.findById(scanId)).thenReturn(Optional.of(originalScan));

        Scan duplicatedScan = scanService.duplicateScan(scanId);
        assertEquals(originalScan.getMaxFiles(), duplicatedScan.getMaxFiles());
        assertEquals(originalScan.getMaxDepth(), duplicatedScan.getMaxDepth());
        assertEquals(originalScan.getFileNameFilter(), duplicatedScan.getFileNameFilter());
        assertEquals(originalScan.getFileTypeFilter(), duplicatedScan.getFileTypeFilter());
        assertEquals(LocalDateTime.now().getDayOfYear(), duplicatedScan.getScanDate().getDayOfYear()); // Vérification de la date
        assertEquals(null, duplicatedScan.getExecutionTime());
    }

    // Ajoutez d'autres tests unitaires pour les autres méthodes de ScanService
}
