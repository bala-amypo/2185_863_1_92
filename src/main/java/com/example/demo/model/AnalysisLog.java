package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AnalysisLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime loggedAt;

    @ManyToOne
    private HotspotZone zone;

    public AnalysisLog() {
        this.loggedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getLoggedAt() { return loggedAt; }

    public HotspotZone getZone() { return zone; }
    public void setZone(HotspotZone zone) { this.zone = zone; }
}
