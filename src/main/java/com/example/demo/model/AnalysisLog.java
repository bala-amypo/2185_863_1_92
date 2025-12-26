package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "analysis_logs")
public class AnalysisLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private LocalDateTime loggedAt;

    @ManyToOne
    private HotspotZone zone;

    // FIX: Add timestamp initialization in default constructor
    public AnalysisLog() {
        this.loggedAt = LocalDateTime.now();
    }

    public AnalysisLog(String message, HotspotZone zone) {
        this.message = message;
        this.zone = zone;
        this.loggedAt = LocalDateTime.now();
    }

    @PrePersist
    public void onCreate() {
        if (this.loggedAt == null) {
            this.loggedAt = LocalDateTime.now();
        }
    }

    // getters & setters

    public Long getId() { return id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getLoggedAt() { return loggedAt; }

    public HotspotZone getZone() { return zone; }
    public void setZone(HotspotZone zone) { this.zone = zone; }
}