package com.example.demo.controller;

import com.example.demo.model.HotspotZone;
import com.example.demo.service.HotspotZoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zones")
@Tag(name = "Hotspot Zones")
public class HotspotZoneController {

    private final HotspotZoneService hotspotZoneService;

    public HotspotZoneController(HotspotZoneService hotspotZoneService) {
        this.hotspotZoneService = hotspotZoneService;
    }

    @PostMapping
    @Operation(summary = "Create hotspot zone")
    public HotspotZone createZone(@RequestBody HotspotZone zone) {
        return hotspotZoneService.addZone(zone);
    }

    @GetMapping
    @Operation(summary = "List all hotspot zones")
    public List<HotspotZone> getAllZones() {
        return hotspotZoneService.getAllZones();
    }
}
