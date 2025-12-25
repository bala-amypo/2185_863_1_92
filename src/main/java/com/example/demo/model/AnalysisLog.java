package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "analysis_logs")
public class AnalysisLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 500)
    private String message;

    private LocalDateTime loggedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zone_id")
    private HotspotZone zone;

    public AnalysisLog() {
        this.loggedAt = LocalDateTime.now();
    }

    public AnalysisLog(String message, LocalDateTime loggedAt, HotspotZone zone) {
        this.message = message;
        this.loggedAt = loggedAt != null ? loggedAt : LocalDateTime.now();
        this.zone = zone;
    }

    @PrePersist
    protected void onCreate() {
        if (loggedAt == null) {
            loggedAt = LocalDateTime.now();
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }

    public HotspotZone getZone() { return zone; }
    public void setZone(HotspotZone zone) { this.zone = zone; }
}