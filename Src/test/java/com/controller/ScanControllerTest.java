package com.controller;

import com.service.ScanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScanController.class)
public class ScanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScanService scanService;

    @Test
    public void testGetScans() throws Exception {
        mockMvc.perform(get("/scans"))
                .andExpect(status().isOk());
        // Ajouter plus de tests selon les méthodes dans ScanController
    }
}
