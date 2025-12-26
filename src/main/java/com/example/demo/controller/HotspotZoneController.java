package com.example.demo.controller;

import com.example.demo.service.HotspotZoneService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotspots")
public class HotspotZoneController {

    private final HotspotZoneService service;

    public HotspotZoneController(HotspotZoneService service) {
        this.service = service;
    }

    @GetMapping
    public String getHotspots() {
        return "hotspots";
    }
}
