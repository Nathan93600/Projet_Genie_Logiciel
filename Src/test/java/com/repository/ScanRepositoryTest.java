package com.repository;

import com.example.demo.Application;
import com.model.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = com.example.demo.Application.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
class ScanRepositoryTest {

    @Autowired
    private ScanRepository scanRepository;

    @Test
    void testFindByNombreMaxFichiers() {
        // Given
        int nombreMaxFichiers = 10;
        Scan scan1 = new Scan();
        scan1.setMaxFiles(nombreMaxFichiers);
        Scan scan2 = new Scan();
        scan2.setMaxFiles(nombreMaxFichiers);
        scanRepository.save(scan1);
        scanRepository.save(scan2);

        // When
        List<Scan> foundScans = scanRepository.findByNombreMaxFichiers(nombreMaxFichiers);

        // Then
        assertEquals(2, foundScans.size());
    }

    @Test
    void testFindByProfondeurMaxArborescence() {
        // Given
        int profondeurMaxArborescence = 5;
        Scan scan1 = new Scan();
        scan1.setMaxDepth(profondeurMaxArborescence);
        Scan scan2 = new Scan();
        scan2.setMaxDepth(profondeurMaxArborescence);
        scanRepository.save(scan1);
        scanRepository.save(scan2);

        // When
        List<Scan> foundScans = scanRepository.findByProfondeurMaxArborescence(profondeurMaxArborescence);

        // Then
        assertEquals(2, foundScans.size());
    }
}
