package com.controller;

import com.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scans")
public class ScanController {
    private final ScanService scanService;

    public ScanController(ScanService scanService) {
        this.scanService = scanService;
    }

    // API endpoints here
}
