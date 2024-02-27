import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Scan;
import com.controller.ScanController;
import com.service.ScanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.hasSize;


import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Annotations pour configurer le test d'intégration
@ExtendWith(SpringExtension.class) // Intègre le support de Spring dans les tests JUnit 5
@SpringBootTest // Indique que c'est un test d'intégration qui charge le contexte Spring complet
@AutoConfigureMockMvc // Configure automatiquement MockMvc pour les tests web
class ScanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // Injecte un objet MockMvc pour simuler des requêtes HTTP

    @Mock
    private ScanService scanService; // Crée un mock du service utilisé par le contrôleur

    @InjectMocks
    private ScanController scanController; // Injecte les mocks dans le contrôleur testé
}
