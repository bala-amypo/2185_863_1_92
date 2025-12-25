package com.example.demo.service.impl;

import com.example.demo.model.HotspotZone;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.HotspotZoneService;

import java.util.List;

public class HotspotZoneServiceImpl implements HotspotZoneService {

    private final HotspotZoneRepository repo;

    public HotspotZoneServiceImpl(HotspotZoneRepository repo) {
        this.repo = repo;
    }

    @Override
    public HotspotZone addZone(HotspotZone zone) {
        if (repo.existsByZoneName(zone.getZoneName())) {
            throw new IllegalArgumentException("exists");
        }

        if (zone.getCenterLat() < -90 || zone.getCenterLat() > 90) {
            throw new IllegalArgumentException("latitude");
        }

        if (zone.getCenterLong() < -180 || zone.getCenterLong() > 180) {
            throw new IllegalArgumentException("longitude");
        }

        return repo.save(zone);
    }

    @Override
    public List<HotspotZone> getAllZones() {
        return repo.findAll();
    }
}
