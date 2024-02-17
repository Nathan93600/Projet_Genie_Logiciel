package com.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
class ScanServiceTest {

    @Mock
    private ScanRepository scanRepository;

    @InjectMocks
    private ScanService scanService;

    @Test
    void testCreateScan() {
        Scan scan = new Scan();
        scanService.createScan(scan);
        verify(scanRepository).save(scan);
    }
}
