package com.example.demo.service.impl;

import com.example.demo.model.HotspotZone;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.HotspotZoneService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotspotZoneServiceImpl implements HotspotZoneService {
    private final HotspotZoneRepository hotspotZoneRepository;

    public HotspotZoneServiceImpl(HotspotZoneRepository hotspotZoneRepository) {
        this.hotspotZoneRepository = hotspotZoneRepository;
    }

    @Override
    public HotspotZone addZone(HotspotZone zone) throws Exception {
        if (hotspotZoneRepository.existsByZoneName(zone.getZoneName())) {
            throw new Exception("Zone name already exists");
        }
        return hotspotZoneRepository.save(zone);
    }

    @Override
    public List<HotspotZone> getAllZones() {
        return hotspotZoneRepository.findAll();
    }
}