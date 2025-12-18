package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "zoneName")
})
public class HotspotZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zoneName;

    private Double centerLat;

    private Double centerLong;

    private String severityLevel;

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
}
