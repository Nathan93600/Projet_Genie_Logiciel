package com.model.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class ScanTermineTest {

    private ScanTermine scanTermine;
    private ScanContext context;

    @BeforeEach
    void setUp() {
        scanTermine = new ScanTermine();
        context = mock(ScanContext.class);
    }

    @Test
    void demarrerScan_shouldPrintMessage() {
        // Arrange

        // Act
        scanTermine.demarrerScan(context);

        // Assert
        verify(context, never()).setEtatScan(any());
    }

    @Test
    void terminerScan_shouldPrintMessage() {
        // Arrange

        // Act
        scanTermine.terminerScan(context);

        // Assert
        verify(context, never()).setEtatScan(any());
    }

    @Test
    void erreurScan_shouldPrintMessage() {
        // Arrange

        // Act
        scanTermine.erreurScan(context);

        // Assert
        verify(context, never()).setEtatScan(any());
    }
}
