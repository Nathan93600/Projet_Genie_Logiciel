import com.service.ScanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.mockito.Mock;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScanControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ScanService scanService;

    @Test
    public void contextLoads() {
        assertThat(restTemplate).isNotNull();
        assertThat(scanService).isNotNull();
    }

    // Ajoutez d'autres tests d'intégration pour les endpoints du contrôleur si nécessaire
}
