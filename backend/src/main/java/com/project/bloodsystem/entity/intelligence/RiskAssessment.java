package com.project.bloodsystem.entity.intelligence;

import com.project.bloodsystem.entity.institution.Hospital;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "risk_assessment")
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @Column(nullable = false, length = 50)
    private String resourceType;

    @Column(length = 10)
    private String bloodGroup;

    @Column(length = 10)
    private String rhFactor;

    @Column(nullable = false)
    private LocalDate assessmentPeriodStart;

    @Column(nullable = false)
    private LocalDate assessmentPeriodEnd;

    @Column(nullable = false, length = 50)
    private String riskLevel;

    @Column(precision = 10, scale = 2)
    private BigDecimal riskScore;

    private Long predictionId;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) { this.hospital = hospital; }

    public String getResourceType() { return resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getRhFactor() { return rhFactor; }
    public void setRhFactor(String rhFactor) { this.rhFactor = rhFactor; }

    public LocalDate getAssessmentPeriodStart() { return assessmentPeriodStart; }
    public void setAssessmentPeriodStart(LocalDate assessmentPeriodStart) { this.assessmentPeriodStart = assessmentPeriodStart; }

    public LocalDate getAssessmentPeriodEnd() { return assessmentPeriodEnd; }
    public void setAssessmentPeriodEnd(LocalDate assessmentPeriodEnd) { this.assessmentPeriodEnd = assessmentPeriodEnd; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

    public BigDecimal getRiskScore() { return riskScore; }
    public void setRiskScore(BigDecimal riskScore) { this.riskScore = riskScore; }

    public Long getPredictionId() { return predictionId; }
    public void setPredictionId(Long predictionId) { this.predictionId = predictionId; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
