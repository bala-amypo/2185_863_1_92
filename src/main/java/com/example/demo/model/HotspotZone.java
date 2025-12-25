package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "hotspot_zones")
public class HotspotZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(unique = true)
    private String zoneName;

    @NotNull
    private Double centerLat;

    @NotNull
    private Double centerLong;

    private String severityLevel = "LOW";

    private Double radiusMeters = 0.1;

    public HotspotZone() {}

    public HotspotZone(String zoneName, Double centerLat, Double centerLong, String severityLevel) {
        this.zoneName = zoneName;
        this.centerLat = centerLat;
        this.centerLong = centerLong;
        this.severityLevel = severityLevel != null ? severityLevel : "LOW";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getZoneName() { return zoneName; }
    public void setZoneName(String zoneName) { this.zoneName = zoneName; }

    public Double getCenterLat() { return centerLat; }
    public void setCenterLat(Double centerLat) { this.centerLat = centerLat; }

    public Double getCenterLong() { return centerLong; }
    public void setCenterLong(Double centerLong) { this.centerLong = centerLong; }

    public String getSeverityLevel() { return severityLevel; }
    public void setSeverityLevel(String severityLevel) { this.severityLevel = severityLevel; }

    public Double getRadiusMeters() { return radiusMeters; }
    public void setRadiusMeters(Double radiusMeters) { this.radiusMeters = radiusMeters; }
}