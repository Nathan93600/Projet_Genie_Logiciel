package controller;

import org.springframework.web.bind.annotation.*;
import service.ScanService;

@RestController
@RequestMapping("/api/scans")
public class ScanController {
    private final ScanService scanService;

    public ScanController(ScanService scanService) {
        this.scanService = scanService;
    }

    // API endpoints here
}
