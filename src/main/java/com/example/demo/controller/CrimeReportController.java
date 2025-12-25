package com.example.demo.controller;

import com.example.demo.model.CrimeReport;
import com.example.demo.service.CrimeReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "*")
@Tag(name = "Crime Reports")
public class CrimeReportController {
    private final CrimeReportService reportService;

    public CrimeReportController(CrimeReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    @Operation(summary = "Submit crime report")
    public ResponseEntity<?> addReport(@RequestBody CrimeReport report) {
        try {
            CrimeReport savedReport = reportService.addReport(report);
            return ResponseEntity.status(201).body(savedReport);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Get all crime reports")
    public ResponseEntity<List<CrimeReport>> getAllReports() {
        List<CrimeReport> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }
}