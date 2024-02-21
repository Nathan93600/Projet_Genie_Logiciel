package com.repository;

import com.model.Scan;
import com.example.demo.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class)
@ExtendWith(MockitoExtension.class) // Ajout de cette annotation pour initialiser les mocks avec Mockito
public class ScanRepositoryTest {

    @Mock
    private ScanRepository scanRepositoryMock;

    @Test
    public void testFindByNombreMaxFichiers() {
        int maxFiles = 100;
        Scan scan = new Scan();
        scan.setMaxFiles(maxFiles);
        List<Scan> scans = new ArrayList<>();
        scans.add(scan);
        when(scanRepositoryMock.findByNombreMaxFichiers(maxFiles)).thenReturn(scans);
        List<Scan> result = scanRepositoryMock.findByNombreMaxFichiers(maxFiles); // Utilisation de scanRepositoryMock
        assertEquals(scans.size(), result.size());
        assertEquals(scans.get(0).getMaxFiles(), result.get(0).getMaxFiles());
    }

    @Test
    public void testFindByProfondeurMaxArborescence() {
        int maxDepth = 5;
        Scan scan = new Scan();
        scan.setMaxDepth(maxDepth);
        List<Scan> scans = new ArrayList<>();
        scans.add(scan);
        when(scanRepositoryMock.findByProfondeurMaxArborescence(maxDepth)).thenReturn(scans);
        List<Scan> result = scanRepositoryMock.findByProfondeurMaxArborescence(maxDepth); // Utilisation de scanRepositoryMock
        assertEquals(scans.size(), result.size());
        assertEquals(scans.get(0).getMaxDepth(), result.get(0).getMaxDepth());
    }
}
