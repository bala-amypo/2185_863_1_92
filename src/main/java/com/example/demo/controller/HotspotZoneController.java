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

    private final HotspotZoneService service;

    public HotspotZoneController(HotspotZoneService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create hotspot zone")
    public HotspotZone add(@RequestBody HotspotZone zone) {
        return service.addZone(zone);
    }

    @GetMapping
    @Operation(summary = "List hotspot zones")
    public List<HotspotZone> getAll() {
        return service.getAllZones();
    }
}