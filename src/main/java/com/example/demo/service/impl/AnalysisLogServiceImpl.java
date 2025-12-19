package com.example.demo.service.impl;

import com.example.demo.model.AnalysisLog;
import com.example.demo.model.HotspotZone;
import com.example.demo.repository.AnalysisLogRepository;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.AnalysisLogService;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AnalysisLogServiceImpl implements AnalysisLogService {

    private final AnalysisLogRepository logRepository;
    private final HotspotZoneRepository zoneRepository;

    public AnalysisLogServiceImpl(AnalysisLogRepository logRepository,
                                  HotspotZoneRepository zoneRepository) {
        this.logRepository = logRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public AnalysisLog addLog(Long zoneId, String message) {
        HotspotZone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        AnalysisLog log = new AnalysisLog();
        log.setZone(zone);
        log.setMessage(message);

        return logRepository.save(log);
    }

    @Override
    public List<AnalysisLog> getLogsByZone(Long zoneId) {
        return logRepository.findByZone_Id(zoneId);
    }
}
