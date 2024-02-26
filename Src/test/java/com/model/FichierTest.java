import com.model.Fichier;
import com.model.Scan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Classe de tests unitaires pour la classe Fichier.
 */
public class FichierTest {

    private Fichier fichier;

    @BeforeEach
    void setUp() {
        fichier = new Fichier();
    }

    /**
     * Teste la création d'une instance de Fichier.
     */
    @Test
    void testConstructor() {
        assertNotNull(fichier);
    }

    /**
     * Teste les getters et setters de la classe Fichier.
     */
    @Test
    void testGettersAndSetters() {
        Long id = 1L;
        String nom = "example.txt";
        String type = "text/plain";
        Long poids = 100L;
        LocalDateTime dateModification = LocalDateTime.now();
        String repertoire = "/path/to/file";
        Long executionTime = 200L;

        fichier.setId(id);
        fichier.setNom(nom);
        fichier.setType(type);
        fichier.setPoids(poids);
        fichier.setDateModification(dateModification);
        fichier.setRepertoire(repertoire);
        fichier.setExecutionTime(executionTime);

        assertEquals(id, fichier.getId());
        assertEquals(nom, fichier.getNom());
        assertEquals(type, fichier.getType());
        assertEquals(poids, fichier.getPoids());
        assertEquals(dateModification, fichier.getDateModification());
        assertEquals(repertoire, fichier.getRepertoire());
        assertEquals(executionTime, fichier.getExecutionTime());
    }

    /**
     * Teste l'association avec un objet Scan.
     */
    @Test
    void testScanAssociation() {
        Scan scan = new Scan();
        fichier.setScan(scan);
        assertEquals(scan, fichier.getScan());
    }
}
