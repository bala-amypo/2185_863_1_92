package com.example.demo.controller;

import com.example.demo.service.AnalysisLogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analysis-logs")
public class AnalysisLogController {

    private final AnalysisLogService service;

    public AnalysisLogController(AnalysisLogService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllLogs() {
        return "analysis logs";
    }
}
