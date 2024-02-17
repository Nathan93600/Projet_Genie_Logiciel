package com.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FichierTest {

    @Test
    public void testFichier() {
        Fichier fichier = new Fichier();
        fichier.setNom("example.txt");
        assertEquals("example.txt", fichier.getNom());
        // Ajouter plus de tests pour les autres attributs et méthodes
    }
}
