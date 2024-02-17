package com.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScanTest {

    @Test
    public void testScan() {
        Scan scan = new Scan();
        scan.setMaxFiles(10);
        assertEquals(10, scan.getMaxFiles());
        // Ajouter plus de tests pour les autres attributs et méthodes
    }
}
