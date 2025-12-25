package com.example.demo.controller;

import com.example.demo.model.AnalysisLog;
import com.example.demo.service.AnalysisLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
@CrossOrigin(origins = "*")
@Tag(name = "Analysis Logs")
public class AnalysisLogController {
    private final AnalysisLogService logService;

    public AnalysisLogController(AnalysisLogService logService) {
        this.logService = logService;
    }

    @PostMapping("/{zoneId}")
    @Operation(summary = "Create analysis log")
    public ResponseEntity<?> addLog(@PathVariable Long zoneId, @RequestBody Map<String, String> request) {
        try {
            String message = request.get("message");
            AnalysisLog log = logService.addLog(zoneId, message);
            return ResponseEntity.status(201).body(log);
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().contains("zone")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/zone/{zoneId}")
    @Operation(summary = "Get logs for zone")
    public ResponseEntity<List<AnalysisLog>> getLogsByZone(@PathVariable Long zoneId) {
        List<AnalysisLog> logs = logService.getLogsByZone(zoneId);
        return ResponseEntity.ok(logs);
    }
}