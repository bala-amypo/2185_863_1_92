package com.example.demo.controller;

import com.example.demo.service.PatternDetectionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patterns")
public class PatternDetectionController {

    private final PatternDetectionService service;

    public PatternDetectionController(PatternDetectionService service) {
        this.service = service;
    }

    @GetMapping
    public String getPatterns() {
        return "patterns";
    }
}
