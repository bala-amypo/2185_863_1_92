package com.example.demo.service.impl;

import com.example.demo.model.HotspotZone;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.HotspotZoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔥 REQUIRED
public class HotspotZoneServiceImpl implements HotspotZoneService {

    private final HotspotZoneRepository zoneRepo;

    public HotspotZoneServiceImpl(HotspotZoneRepository zoneRepo) {
        this.zoneRepo = zoneRepo;
    }

    @Override
    public HotspotZone addZone(HotspotZone zone) {

        if (zoneRepo.existsByZoneName(zone.getZoneName())) {
            throw new IllegalArgumentException("zone exists");
        }

        if (zone.getCenterLat() == null || zone.getCenterLat() < -90 || zone.getCenterLat() > 90) {
            throw new IllegalArgumentException("latitude invalid");
        }

        if (zone.getCenterLong() == null || zone.getCenterLong() < -180 || zone.getCenterLong() > 180) {
            throw new IllegalArgumentException("longitude invalid");
        }

        if (zone.getSeverityLevel() == null) {
            zone.setSeverityLevel("LOW");
        }

        return zoneRepo.save(zone);
    }

    @Override
    public List<HotspotZone> getAllZones() {
        return zoneRepo.findAll();
    }
}
