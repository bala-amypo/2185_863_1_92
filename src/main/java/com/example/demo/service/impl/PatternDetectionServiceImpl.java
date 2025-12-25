package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.PatternDetectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatternDetectionServiceImpl implements PatternDetectionService {
    private final HotspotZoneRepository zoneRepo;
    private final CrimeReportRepository reportRepo;
    private final PatternDetectionResultRepository resultRepo;
    private final AnalysisLogRepository logRepo;

    public PatternDetectionServiceImpl(HotspotZoneRepository zoneRepo, 
                                     CrimeReportRepository reportRepo,
                                     PatternDetectionResultRepository resultRepo,
                                     AnalysisLogRepository logRepo) {
        this.zoneRepo = zoneRepo;
        this.reportRepo = reportRepo;
        this.resultRepo = resultRepo;
        this.logRepo = logRepo;
    }

    @Override
    public PatternDetectionResult detectPattern(Long zoneId) throws Exception {
        if (zoneId == null) {
            throw new IllegalArgumentException("Zone ID cannot be null");
        }
        
        // Fetch zone
        HotspotZone zone = zoneRepo.findById(zoneId)
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        // Calculate search bounds (±0.1 degrees)
        double minLat = zone.getCenterLat() - 0.1;
        double maxLat = zone.getCenterLat() + 0.1;
        double minLong = zone.getCenterLong() - 0.1;
        double maxLong = zone.getCenterLong() + 0.1;

        // Find crimes in range
        List<CrimeReport> crimes = reportRepo.findByLatLongRange(minLat, maxLat, minLong, maxLong);
        int crimeCount = crimes.size();

        // Determine pattern based on count
        String detectedPattern;
        if (crimeCount > 5) {
            detectedPattern = "High Risk Pattern Detected";
        } else if (crimeCount > 0) {
            detectedPattern = "Medium Risk Pattern Detected";
        } else {
            detectedPattern = "No Pattern Detected";
        }

        // Create and save result
        PatternDetectionResult result = new PatternDetectionResult();
        result.setZone(zone);
        result.setAnalysisDate(LocalDate.now());
        result.setCrimeCount(crimeCount);
        result.setDetectedPattern(detectedPattern);
        result = resultRepo.save(result);

        // Create analysis log
        AnalysisLog log = new AnalysisLog();
        log.setMessage("Pattern detection completed for zone: " + zone.getZoneName());
        log.setZone(zone);
        logRepo.save(log);

        // Update zone severity if needed
        if (crimeCount > 5) {
            zone.setSeverityLevel("HIGH");
        } else if (crimeCount > 0) {
            zone.setSeverityLevel("MEDIUM");
        } else {
            zone.setSeverityLevel("LOW");
        }
        zoneRepo.save(zone);

        return result;
    }

    @Override
    public List<PatternDetectionResult> getResultsByZone(Long zoneId) {
        return resultRepo.findByZone_Id(zoneId);
    }
}