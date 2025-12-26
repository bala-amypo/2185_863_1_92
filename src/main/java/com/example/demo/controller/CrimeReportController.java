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

    private final CrimeReportService service;

    public CrimeReportController(CrimeReportService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Add crime report")
    public CrimeReport add(@RequestBody CrimeReport report) {
        return service.addReport(report);
    }

    @GetMapping
    @Operation(summary = "Get all crime reports")
    public List<CrimeReport> getAll() {
        return service.getAllReports();
    }
}