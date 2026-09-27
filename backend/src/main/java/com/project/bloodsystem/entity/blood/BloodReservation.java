package com.project.bloodsystem.entity.blood;

import com.project.bloodsystem.entity.access.User;
import com.project.bloodsystem.entity.institution.Hospital;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "blood_reservation")
public class BloodReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blood_request_id", nullable = false)
    private BloodRequest bloodRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blood_unit_id", nullable = false)
    private BloodUnit bloodUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserved_by")
    private User reservedBy;

    @Column(name = "reserved_at", updatable = false)
    private LocalDateTime reservedAt;

    @Column(length = 50)
    private String status = "RESERVED";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "released_by")
    private User releasedBy;

    private LocalDateTime releasedAt;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @PrePersist
    protected void onCreate() {
        reservedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BloodRequest getBloodRequest() { return bloodRequest; }
    public void setBloodRequest(BloodRequest bloodRequest) { this.bloodRequest = bloodRequest; }

    public BloodUnit getBloodUnit() { return bloodUnit; }
    public void setBloodUnit(BloodUnit bloodUnit) { this.bloodUnit = bloodUnit; }

    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) { this.hospital = hospital; }

    public User getReservedBy() { return reservedBy; }
    public void setReservedBy(User reservedBy) { this.reservedBy = reservedBy; }

    public LocalDateTime getReservedAt() { return reservedAt; }
    public void setReservedAt(LocalDateTime reservedAt) { this.reservedAt = reservedAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User getReleasedBy() { return releasedBy; }
    public void setReleasedBy(User releasedBy) { this.releasedBy = releasedBy; }

    public LocalDateTime getReleasedAt() { return releasedAt; }
    public void setReleasedAt(LocalDateTime releasedAt) { this.releasedAt = releasedAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
