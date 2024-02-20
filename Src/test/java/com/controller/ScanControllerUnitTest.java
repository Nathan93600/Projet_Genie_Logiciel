import com.service.ScanService;
import com.controller.ScanController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ScanControllerUnitTest {

    @Mock
    private ScanService scanService;

    @InjectMocks
    private ScanController scanController;

    @Test
    void contextLoads() {
        assertNotNull(scanController);
    }

    // Ajoutez d'autres tests unitaires pour les méthodes du contrôleur si nécessaire
}
