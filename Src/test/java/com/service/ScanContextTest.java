package com.service;

import com.model.state.EtatScan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class ScanContextTest {

    private ScanContext scanContext;
    private EtatScan etatScan;

    @BeforeEach
    void setUp() {
        scanContext = new ScanContext();
        etatScan = mock(EtatScan.class);
        scanContext.setEtatScan(etatScan);
    }

    @Test
    void demarrerScan_shouldCallDemarrerScanOnEtatScan() {
        // Arrange

        // Act
        scanContext.demarrerScan();

        // Assert
        verify(etatScan).demarrerScan(scanContext);
    }

    @Test
    void terminerScan_shouldCallTerminerScanOnEtatScan() {
        // Arrange

        // Act
        scanContext.terminerScan();

        // Assert
        verify(etatScan).terminerScan(scanContext);
    }

    @Test
    void erreurScan_shouldCallErreurScanOnEtatScan() {
        // Arrange

        // Act
        scanContext.erreurScan();

        // Assert
        verify(etatScan).erreurScan(scanContext);
    }
}
