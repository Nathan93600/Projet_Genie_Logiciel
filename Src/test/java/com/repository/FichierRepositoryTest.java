package com.repository;

import com.model.Fichier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class FichierRepositoryTest {

    @Autowired
    private FichierRepository fichierRepository;

    @Test
    public void testSaveFichier() {
        Fichier fichier = new Fichier();
        fichier = fichierRepository.save(fichier);
        assertNotNull(fichier.getId());
        // Ajouter plus de tests pour les méthodes de repository
    }
}
