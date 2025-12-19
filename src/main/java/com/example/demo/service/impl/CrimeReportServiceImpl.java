package com.example.demo.service.impl;

import com.example.demo.model.CrimeReport;
import com.example.demo.repository.CrimeReportRepository;
import com.example.demo.service.CrimeReportService;
import com.example.demo.util.CoordinateValidator;
import com.example.demo.util.DateValidator;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CrimeReportServiceImpl implements CrimeReportService {

    private final CrimeReportRepository crimeReportRepository;

    public CrimeReportServiceImpl(CrimeReportRepository crimeReportRepository) {
        this.crimeReportRepository = crimeReportRepository;
    }

    @Override
    public CrimeReport addReport(CrimeReport report) {
        if (!CoordinateValidator.isValidLatitude(report.getLatitude())) {
            throw new RuntimeException("Invalid latitude");
        }
        if (!CoordinateValidator.isValidLongitude(report.getLongitude())) {
            throw new RuntimeException("Invalid longitude");
        }
        if (!DateValidator.isPastOrPresent(report.getOccurredAt())) {
            throw new RuntimeException("Date not valid");
        }
        return crimeReportRepository.save(report);
    }

    @Override
    public List<CrimeReport> getAllReports() {
        return crimeReportRepository.findAll();
    }
}
