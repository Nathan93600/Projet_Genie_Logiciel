package com.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileScanServiceTest {

    @Autowired
    private FileScanService fileScanService;

    @Test
    void testScanDirectory() throws Exception {
        // Assurez-vous que le chemin fourni existe et est accessible pour les tests
        Scan scan = fileScanService.scanDirectory(Paths.get("path/to/directory"));
        assertTrue(scan.getFichiers().size() > 0);
    }
}
