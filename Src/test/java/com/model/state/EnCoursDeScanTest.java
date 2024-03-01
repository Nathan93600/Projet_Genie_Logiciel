package com.model.state;

package com.model.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EnCoursDeScanTest {
    
    private EnCoursDeScan enCoursDeScan;
    private ScanContext context;

    @BeforeEach
    void setUp() {
        enCoursDeScan = new EnCoursDeScan();
        context = mock(ScanContext.class);
    }

    @Test
    void demarrerScan_shouldPrintMessage() {
        // Arrange

        // Act
        enCoursDeScan.demarrerScan(context);

        // Assert
        verify(context, never()).setEtatScan(any());
    }

    @Test
    void terminerScan_shouldSetScanTermineState() {
        // Arrange

        // Act
        enCoursDeScan.terminerScan(context);

        // Assert
        verify(context).setEtatScan(any(ScanTermine.class));
    }

    @Test
    void erreurScan_shouldSetErreurScanState() {
        // Arrange

        // Act
        enCoursDeScan.erreurScan(context);

        // Assert
        verify(context).setEtatScan(any(ErreurScan.class));
    }
}

