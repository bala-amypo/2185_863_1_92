package com.example.demo.controller;

import com.example.demo.model.AnalysisLog;
import com.example.demo.service.AnalysisLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
@Tag(name = "Analysis Logs")
public class AnalysisLogController {

    private final AnalysisLogService analysisLogService;

    public AnalysisLogController(AnalysisLogService analysisLogService) {
        this.analysisLogService = analysisLogService;
    }

    @PostMapping("/{zoneId}")
    @Operation(summary = "Add analysis log")
    public AnalysisLog addLog(@PathVariable Long zoneId,
                              @RequestBody Map<String, String> body) {
        return analysisLogService.addLog(zoneId, body.get("message"));
    }

    @GetMapping("/zone/{zoneId}")
    @Operation(summary = "Fetch logs for zone")
    public List<AnalysisLog> getLogs(@PathVariable Long zoneId) {
        return analysisLogService.getLogsByZone(zoneId);
    }
}
