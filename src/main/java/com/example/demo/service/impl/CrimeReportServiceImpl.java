package com.example.demo.service.impl;

import com.example.demo.model.CrimeReport;
import com.example.demo.repository.CrimeReportRepository;
import com.example.demo.service.CrimeReportService;
import com.example.demo.util.CoordinateValidator;
import com.example.demo.util.DateValidator;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CrimeReportServiceImpl implements CrimeReportService {
    private final CrimeReportRepository crimeReportRepository;

    public CrimeReportServiceImpl(CrimeReportRepository crimeReportRepository) {
        this.crimeReportRepository = crimeReportRepository;
    }

    @Override
    public CrimeReport addReport(CrimeReport report) throws Exception {
        if (!CoordinateValidator.isValidLatitude(report.getLatitude())) {
            throw new Exception("Invalid latitude");
        }
        if (!CoordinateValidator.isValidLongitude(report.getLongitude())) {
            throw new Exception("Invalid longitude");
        }
        if (!DateValidator.isNotFuture(report.getOccurredAt())) {
            throw new Exception("Date cannot be in future");
        }
        return crimeReportRepository.save(report);
    }

    @Override
    public List<CrimeReport> getAllReports() {
        return crimeReportRepository.findAll();
    }
}