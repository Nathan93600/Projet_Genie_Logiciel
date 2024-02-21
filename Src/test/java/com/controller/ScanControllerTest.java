package com.controller;

import com.model.Scan;
import com.service.ScanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ScanControllerTest {

    @Mock
    private ScanService scanService;

    @InjectMocks
    private ScanController scanController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateScan() {
        Scan scan = new Scan();
        when(scanService.createScan(scan)).thenReturn(scan);

        ResponseEntity<Scan> response = scanController.createScan(scan);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(scan, response.getBody());
    }

    @Test
    void testGetAllScans() {
        List<Scan> scans = new ArrayList<>();
        when(scanService.getAllScans()).thenReturn(scans);

        ResponseEntity<List<Scan>> response = scanController.getAllScans();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(scans, response.getBody());
    }

    @Test
    void testGetScanById() {
        Long id = 1L;
        Scan scan = new Scan();
        when(scanService.getScanById(id)).thenReturn(scan);

        ResponseEntity<Scan> response = scanController.getScanById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(scan, response.getBody());
    }

    @Test
    void testUpdateScan() {
        Long id = 1L;
        Scan scan = new Scan();
        when(scanService.updateScan(id, scan)).thenReturn(scan);

        ResponseEntity<Scan> response = scanController.updateScan(id, scan);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(scan, response.getBody());
    }

    @Test
    void testDeleteScan() {
        Long id = 1L;

        ResponseEntity<Void> response = scanController.deleteScan(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(scanService, times(1)).deleteScan(id);
    }
}
