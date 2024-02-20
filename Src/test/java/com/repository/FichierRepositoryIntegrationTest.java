import com.model.Fichier;
import com.repository.FichierRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FichierRepositoryIntegrationTest {

    @Autowired
    private FichierRepository fichierRepository;

    @Test
    void testFindByTypeFichier() {
        Fichier fichier1 = new Fichier();
        fichier1.setType("txt");
        fichierRepository.save(fichier1);

        Fichier fichier2 = new Fichier();
        fichier2.setType("pdf");
        fichierRepository.save(fichier2);

        List<Fichier> found = fichierRepository.findByTypeFichier("txt");

        assertEquals(1, found.size());
        assertEquals("txt", found.get(0).getType());
    }

    // Ajoutez des tests pour les autres méthodes de recherche définies dans FichierRepository
}
