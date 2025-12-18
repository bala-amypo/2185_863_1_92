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
public class PatternDetectionResultController {

    private final PatternDetectionService patternDetectionService;

    public PatternDetectionResultController(PatternDetectionService patternDetectionService) {
        this.patternDetectionService = patternDetectionService;
    }

    @PostMapping("/detect/{zoneId}")
    @Operation(summary = "Run pattern detection for a hotspot zone")
    public PatternDetectionResult detectPattern(@PathVariable Long zoneId) {
        return patternDetectionService.detectPattern(zoneId);
    }

    @GetMapping("/zone/{zoneId}")
    @Operation(summary = "Get pattern detection results for a zone")
    public List<PatternDetectionResult> getResultsByZone(@PathVariable Long zoneId) {
        return patternDetectionService.getResultsByZone(zoneId);
    }
}
