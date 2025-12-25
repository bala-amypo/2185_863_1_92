package com.example.demo.service.impl;

import com.example.demo.model.CrimeReport;
import com.example.demo.repository.CrimeReportRepository;
import com.example.demo.service.CrimeReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CrimeReportServiceImpl implements CrimeReportService {
    private final CrimeReportRepository reportRepository;

    public CrimeReportServiceImpl(CrimeReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public CrimeReport addReport(CrimeReport report) throws Exception {
        // Validate coordinates
        if (report.getLatitude() == null || report.getLatitude() < -90 || report.getLatitude() > 90) {
            throw new IllegalArgumentException("Invalid latitude - must be between -90 and 90");
        }
        if (report.getLongitude() == null || report.getLongitude() < -180 || report.getLongitude() > 180) {
            throw new IllegalArgumentException("Invalid longitude - must be between -180 and 180");
        }
        
        // Validate time
        if (report.getOccurredAt() != null && report.getOccurredAt().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Crime occurrence time cannot be in the future");
        }
        
        return reportRepository.save(report);
    }

    @Override
    public List<CrimeReport> getAllReports() {
        return reportRepository.findAll();
    }
}