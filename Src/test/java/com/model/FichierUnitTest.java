import com.model.Fichier;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FichierUnitTest {

    @Test
    void testFichierCreation() {
        Fichier fichier = new Fichier();
        assertNotNull(fichier);
    }

    @Test
    void testFichierSettersAndGetters() {
        Fichier fichier = new Fichier();
        fichier.setId(1L);
        fichier.setNom("test.txt");
        fichier.setType("text/plain");
        fichier.setPoids(1024L);
        LocalDateTime dateModification = LocalDateTime.now();
        fichier.setDateModification(dateModification);
        fichier.setRepertoire("/path/to/directory");
        fichier.setExecutionTime(100L);

        assertEquals(1L, fichier.getId());
        assertEquals("test.txt", fichier.getNom());
        assertEquals("text/plain", fichier.getType());
        assertEquals(1024L, fichier.getPoids());
        assertEquals(dateModification, fichier.getDateModification());
        assertEquals("/path/to/directory", fichier.getRepertoire());
        assertEquals(100L, fichier.getExecutionTime());
    }
}
