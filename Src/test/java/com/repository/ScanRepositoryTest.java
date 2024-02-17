package com.repository;

import com.model.Scan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class ScanRepositoryTest {

    @Autowired
    private ScanRepository scanRepository;

    @Test
    void testSaveScan() {
        Scan scan = new Scan();
        Scan savedScan = scanRepository.save(scan);
        assertNotNull(savedScan.getId());
    }
}
