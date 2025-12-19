package com.example.demo.service.impl;

import com.example.demo.model.HotspotZone;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.HotspotZoneService;
import com.example.demo.util.CoordinateValidator;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HotspotZoneServiceImpl implements HotspotZoneService {

    private final HotspotZoneRepository hotspotZoneRepository;

    public HotspotZoneServiceImpl(HotspotZoneRepository hotspotZoneRepository) {
        this.hotspotZoneRepository = hotspotZoneRepository;
    }

    @Override
    public HotspotZone addZone(HotspotZone zone) {
        if (hotspotZoneRepository.findByZoneName(zone.getZoneName()).isPresent()) {
            throw new RuntimeException("Zone name exists");
        }
        if (!CoordinateValidator.isValidLatitude(zone.getCenterLat())) {
            throw new RuntimeException("Invalid latitude");
        }
        if (!CoordinateValidator.isValidLongitude(zone.getCenterLong())) {
            throw new RuntimeException("Invalid longitude");
        }
        zone.setSeverityLevel("LOW");
        return hotspotZoneRepository.save(zone);
    }

    @Override
    public List<HotspotZone> getAllZones() {
        return hotspotZoneRepository.findAll();
    }
}
