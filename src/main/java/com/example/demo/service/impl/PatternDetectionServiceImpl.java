package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.PatternDetectionService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PatternDetectionServiceImpl implements PatternDetectionService {
    private final HotspotZoneRepository zoneRepository;
    private final CrimeReportRepository crimeRepository;
    private final PatternDetectionResultRepository resultRepository;
    private final AnalysisLogRepository logRepository;

    public PatternDetectionServiceImpl(HotspotZoneRepository zoneRepository,
                                      CrimeReportRepository crimeRepository,
                                      PatternDetectionResultRepository resultRepository,
                                      AnalysisLogRepository logRepository) {
        this.zoneRepository = zoneRepository;
        this.crimeRepository = crimeRepository;
        this.resultRepository = resultRepository;
        this.logRepository = logRepository;
    }

    @Override
    public PatternDetectionResult detectPattern(Long zoneId) throws Exception {
        HotspotZone zone = zoneRepository.findById(zoneId)
            .orElseThrow(() -> new Exception("Zone not found"));

        double minLat = zone.getCenterLat() - 0.1;
        double maxLat = zone.getCenterLat() + 0.1;
        double minLong = zone.getCenterLong() - 0.1;
        double maxLong = zone.getCenterLong() + 0.1;

        List<CrimeReport> crimes = crimeRepository.findByLatLongRange(minLat, maxLat, minLong, maxLong);
        int count = crimes.size();

        String pattern;
        if (count > 5) {
            pattern = "High Risk Pattern Detected";
        } else if (count > 0) {
            pattern = "Medium Risk Pattern Detected";
        } else {
            pattern = "No Pattern Detected";
        }

        PatternDetectionResult result = new PatternDetectionResult(zone, LocalDate.now(), count, pattern);
        result = resultRepository.save(result);

        AnalysisLog log = new AnalysisLog("Pattern detection completed", null, zone);
        logRepository.save(log);

        return result;
    }

    @Override
    public List<PatternDetectionResult> getResultsByZone(Long zoneId) {
        return resultRepository.findByZone_Id(zoneId);
    }
}