package com.example.demo.controller;

import com.example.demo.model.CrimeReport;
import com.example.demo.service.CrimeReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@Tag(name = "Crime Reports")
public class CrimeReportController {

    private final CrimeReportService crimeReportService;

    public CrimeReportController(CrimeReportService crimeReportService) {
        this.crimeReportService = crimeReportService;
    }

    @PostMapping
    @Operation(summary = "Add new crime report")
    public CrimeReport addReport(@RequestBody CrimeReport report) {
        return crimeReportService.addReport(report);
    }

    @GetMapping
    @Operation(summary = "Fetch all crime reports")
    public List<CrimeReport> getAllReports() {
        return crimeReportService.getAllReports();
    }
}
