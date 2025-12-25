package com.example.demo.controller;

import com.example.demo.model.PatternDetectionResult;
import com.example.demo.service.PatternDetectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patterns")
@CrossOrigin(origins = "*")
@Tag(name = "Pattern Detection")
public class PatternDetectionController {
    private final PatternDetectionService detectionService;

    public PatternDetectionController(PatternDetectionService detectionService) {
        this.detectionService = detectionService;
    }

    @PostMapping("/detect/{zoneId}")
    @Operation(summary = "Run pattern detection for zone")
    public ResponseEntity<?> detectPattern(@PathVariable Long zoneId, @RequestBody(required = false) Object body) {
        try {
            PatternDetectionResult result = detectionService.detectPattern(zoneId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().contains("zone")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/zone/{zoneId}")
    @Operation(summary = "Get detection results for zone")
    public ResponseEntity<List<PatternDetectionResult>> getResultsByZone(@PathVariable Long zoneId) {
        List<PatternDetectionResult> results = detectionService.getResultsByZone(zoneId);
        return ResponseEntity.ok(results);
    }
}