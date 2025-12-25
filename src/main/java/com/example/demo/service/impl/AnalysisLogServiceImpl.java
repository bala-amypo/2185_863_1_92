package com.example.demo.service.impl;

import com.example.demo.model.AnalysisLog;
import com.example.demo.model.HotspotZone;
import com.example.demo.repository.AnalysisLogRepository;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.AnalysisLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisLogServiceImpl implements AnalysisLogService {
    private final AnalysisLogRepository logRepo;
    private final HotspotZoneRepository zoneRepo;

    public AnalysisLogServiceImpl(AnalysisLogRepository logRepo, HotspotZoneRepository zoneRepo) {
        this.logRepo = logRepo;
        this.zoneRepo = zoneRepo;
    }

    @Override
    public AnalysisLog addLog(Long zoneId, String message) throws Exception {
        if (zoneId == null) {
            throw new Exception("Zone ID cannot be null");
        }
        
        HotspotZone zone = zoneRepo.findById(zoneId)
                .orElseThrow(() -> new Exception("Zone not found"));

        AnalysisLog log = new AnalysisLog();
        log.setMessage(message);
        log.setZone(zone);
        
        return logRepo.save(log);
    }

    @Override
    public List<AnalysisLog> getLogsByZone(Long zoneId) {
        if (zoneId == null) {
            return List.of();
        }
        return logRepo.findByZone_Id(zoneId);
    }
}