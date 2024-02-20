import com.model.Fichier;
import com.repository.FichierRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class FichierRepositoryUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FichierRepository fichierRepository;

    @Test
    void testFindByNomContaining() {
        Fichier fichier = new Fichier();
        fichier.setNom("example.txt");
        entityManager.persist(fichier);
        entityManager.flush();

        List<Fichier> found = fichierRepository.findByNomContaining("example");

        assertEquals(1, found.size());
        assertEquals("example.txt", found.get(0).getNom());
    }

    // Ajoutez des tests pour les autres méthodes de recherche définies dans FichierRepository
}
