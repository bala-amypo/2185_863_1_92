package com.example.demo.controller;

import com.example.demo.model.PatternDetectionResult;
import com.example.demo.service.PatternDetectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patterns")
@Tag(name = "Pattern Detection")
public class PatternDetectionController {

    private final PatternDetectionService service;

    public PatternDetectionController(PatternDetectionService service) {
        this.service = service;
    }

    @PostMapping("/detect/{zoneId}")
    @Operation(summary = "Detect crime pattern")
    public PatternDetectionResult detect(@PathVariable Long zoneId) {
        return service.detectPattern(zoneId);
    }

    @GetMapping("/zone/{zoneId}")
    @Operation(summary = "Get pattern results for zone")
    public List<PatternDetectionResult> results(@PathVariable Long zoneId) {
        return service.getResultsByZone(zoneId);
    }
}