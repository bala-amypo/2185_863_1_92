package com.example.demo.controller;

import com.example.demo.model.AnalysisLog;
import com.example.demo.service.AnalysisLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@Tag(name = "Analysis Logs")
public class AnalysisLogController {

    private final AnalysisLogService service;

    public AnalysisLogController(AnalysisLogService service) {
        this.service = service;
    }

    @GetMapping("/zone/{zoneId}")
    @Operation(summary = "Get logs for zone")
    public List<AnalysisLog> getLogs(@PathVariable Long zoneId) {
        return service.getLogsByZone(zoneId);
    }
}