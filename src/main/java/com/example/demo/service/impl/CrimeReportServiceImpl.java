package com.example.demo.service.impl;

import com.example.demo.model.CrimeReport;
import com.example.demo.repository.CrimeReportRepository;
import com.example.demo.service.CrimeReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CrimeReportServiceImpl implements CrimeReportService {

    private final CrimeReportRepository repo;

    public CrimeReportServiceImpl(CrimeReportRepository repo) {
        this.repo = repo;
    }

    @Override
    public CrimeReport addReport(CrimeReport report) {
        if (report.getLatitude() == null ||
            report.getLatitude() < -90 ||
            report.getLatitude() > 90) {
            throw new IllegalArgumentException("latitude");
        }

        if (report.getLongitude() == null ||
            report.getLongitude() < -180 ||
            report.getLongitude() > 180) {
            throw new IllegalArgumentException("longitude");
        }

        if (report.getOccurredAt() != null &&
            report.getOccurredAt().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("time");
        }

        return repo.save(report);
    }

    @Override
    public List<CrimeReport> getAllReports() {
        return repo.findAll();
    }
}
