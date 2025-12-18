package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.PatternDetectionService;

import java.time.LocalDate;
import java.util.List;

public class PatternDetectionServiceImpl implements PatternDetectionService {

    private final HotspotZoneRepository zoneRepository;
    private final CrimeReportRepository crimeRepository;
    private final PatternDetectionResultRepository resultRepository;
    private final AnalysisLogRepository logRepository;

    public PatternDetectionServiceImpl(
            HotspotZoneRepository zoneRepository,
            CrimeReportRepository crimeRepository,
            PatternDetectionResultRepository resultRepository,
            AnalysisLogRepository logRepository) {
        this.zoneRepository = zoneRepository;
        this.crimeRepository = crimeRepository;
        this.resultRepository = resultRepository;
        this.logRepository = logRepository;
    }

    @Override
    public PatternDetectionResult detectPattern(Long zoneId) {
        HotspotZone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        double lat = zone.getCenterLat();
        double lng = zone.getCenterLong();

        List<CrimeReport> crimes = crimeRepository.findByLatLongRange(
                lat - 0.1, lat + 0.1, lng - 0.1, lng + 0.1
        );

        int count = crimes.size();
        String pattern;

        if (count > 5) {
            pattern = "High crime activity detected";
            zone.setSeverityLevel("HIGH");
        } else if (count > 0) {
            pattern = "Medium crime activity detected";
            zone.setSeverityLevel("MEDIUM");
        } else {
            pattern = "No crime detected";
            zone.setSeverityLevel("LOW");
        }

        PatternDetectionResult result = new PatternDetectionResult();
        result.setZone(zone);
        result.setCrimeCount(count);
        result.setDetectedPattern(pattern);
        result.setAnalysisDate(LocalDate.now());

        zoneRepository.save(zone);
        resultRepository.save(result);

        AnalysisLog log = new AnalysisLog();
        log.setZone(zone);
        log.setMessage("Pattern detection executed");
        logRepository.save(log);

        return result;
    }

    @Override
    public List<PatternDetectionResult> getResultsByZone(Long zoneId) {
        return resultRepository.findByZone_Id(zoneId);
    }
}
