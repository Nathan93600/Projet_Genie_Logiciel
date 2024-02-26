package com.controller;

import com.model.Scan;
import com.service.ScanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


import java.util.Collections;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = ScanController.class)
@AutoConfigureMockMvc
class ScanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScanService scanService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateScan() throws Exception {
        Scan scan = new Scan();

        when(scanService.createScan(any(Scan.class))).thenReturn(scan);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/scans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(scan)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testGetAllScans() throws Exception {
        Scan scan = new Scan();
        scan.setId(1L);

        when(scanService.getAllScans()).thenReturn(Collections.singletonList(scan));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/scans")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L));
    }

    @Test
    void testGetScanById() throws Exception {
        Long id = 1L;
        Scan scan = new Scan();
        scan.setId(id);

        when(scanService.getScanById(id)).thenReturn(scan);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/scans/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    void testUpdateScan() throws Exception {
        Long id = 1L;
        Scan scan = new Scan();

        when(scanService.updateScan(eq(id), any(Scan.class))).thenReturn(scan);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/scans/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(scan)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteScan() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/scans/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(scanService, times(1)).deleteScan(id);
    }
}
