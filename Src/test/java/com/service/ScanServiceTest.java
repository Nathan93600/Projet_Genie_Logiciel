import com.model.Scan;
import com.repository.ScanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de tests unitaires pour la classe ScanService.
 */
class ScanServiceTest {

    @Mock
    private ScanRepository scanRepository;

    private ScanService scanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        scanService = new ScanService(scanRepository);
    }

    /**
     * Teste la méthode createScan de la classe ScanService.
     */
    @Test
    void testCreateScan() {
        // Given
        Scan scan = new Scan();
        
        // When
        when(scanRepository.save(any(Scan.class))).thenReturn(scan);
        Scan createdScan = scanService.createScan(scan);
    
        // Then
        assertNotNull(createdScan);
        assertNotNull(createdScan.getScanDate());
    }

    /**
     * Teste la méthode duplicateScan de la classe ScanService.
     */
    @Test
    void testDuplicateScan() {
        // Given
        Long scanId = 1L;
        Scan originalScan = new Scan();
        originalScan.setId(scanId);
        LocalDateTime before = LocalDateTime.now();

        // When
        when(scanRepository.findById(scanId)).thenReturn(Optional.of(originalScan));
        when(scanRepository.save(any(Scan.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Scan duplicatedScan = scanService.duplicateScan(scanId);

        // Then
        assertNotNull(duplicatedScan);
        assertNull(duplicatedScan.getId()); // Vérifie que l'ID est null pour le nouveau scan
        assertNotNull(duplicatedScan.getScanDate());
        assertTrue(duplicatedScan.getScanDate().isAfter(before));
        assertEquals(originalScan.getMaxFiles(), duplicatedScan.getMaxFiles());
        assertEquals(originalScan.getMaxDepth(), duplicatedScan.getMaxDepth());
        assertEquals(originalScan.getFileNameFilter(), duplicatedScan.getFileNameFilter());
        assertEquals(originalScan.getFileTypeFilter(), duplicatedScan.getFileTypeFilter());
        assertNull(duplicatedScan.getExecutionTime());
    }

    // Ajoutez d'autres tests pour les autres méthodes de la classe ScanService
}
