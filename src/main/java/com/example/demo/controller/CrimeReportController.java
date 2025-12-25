package com.example.demo.controller;

import com.example.demo.model.CrimeReport;
import com.example.demo.service.CrimeReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class CrimeReportController {

    private final CrimeReportService service;

    public CrimeReportController(CrimeReportService service) {
        this.service = service;
    }

    @PostMapping
    public CrimeReport addReport(@RequestBody CrimeReport report) {
        return service.addReport(report);
    }

    @GetMapping
    public List<CrimeReport> getAll() {
        return service.getAllReports();
    }
}
