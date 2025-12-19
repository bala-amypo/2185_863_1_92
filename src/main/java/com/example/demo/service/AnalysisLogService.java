package com.example.demo.service;

import com.example.demo.model.AnalysisLog;
import com.example.demo.repository.AnalysisLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service   
// public class AnalysisLogService {

//     private final AnalysisLogRepository repository;

//     public AnalysisLogService(AnalysisLogRepository repository) {
//         this.repository = repository;
//     }

//     public AnalysisLog addLog(Long zoneId, String message) {
//         AnalysisLog log = new AnalysisLog();
//         log.setZoneId(zoneId);
//         log.setMessage(message);
//         return repository.save(log);
//     }

//     public List<AnalysisLog> getLogsByZone(Long zoneId) {
//         return repository.findByZoneId(zoneId);
//     }
// }

public interface AnalysisLogService {
    public AnalysisLog addLog(Long zoneId, String message);
    public List<AnalysisLog> getLogsByZone(Long zoneId);
}
