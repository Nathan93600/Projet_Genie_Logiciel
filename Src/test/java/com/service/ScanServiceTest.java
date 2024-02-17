package com.service;

import com.model.Scan;
import com.repository.ScanRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

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
