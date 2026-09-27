package com.project.bloodsystem.entity.intelligence;

import com.project.bloodsystem.entity.institution.Hospital;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "prediction")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @Column(nullable = false, length = 50)
    private String predictionType;

    @Column(length = 10)
    private String bloodGroup;

    @Column(length = 10)
    private String rhFactor;

    @Column(length = 50)
    private String component;

    @Column(nullable = false)
    private LocalDate predictionPeriodStart;

    @Column(nullable = false)
    private LocalDate predictionPeriodEnd;

    @Column(nullable = false)
    private Integer predictedDemand;

    private Integer actualDemand;

    @Column(length = 50)
    private String modelVersion;

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

    public String getPredictionType() { return predictionType; }
    public void setPredictionType(String predictionType) { this.predictionType = predictionType; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getRhFactor() { return rhFactor; }
    public void setRhFactor(String rhFactor) { this.rhFactor = rhFactor; }

    public String getComponent() { return component; }
    public void setComponent(String component) { this.component = component; }

    public LocalDate getPredictionPeriodStart() { return predictionPeriodStart; }
    public void setPredictionPeriodStart(LocalDate predictionPeriodStart) { this.predictionPeriodStart = predictionPeriodStart; }

    public LocalDate getPredictionPeriodEnd() { return predictionPeriodEnd; }
    public void setPredictionPeriodEnd(LocalDate predictionPeriodEnd) { this.predictionPeriodEnd = predictionPeriodEnd; }

    public Integer getPredictedDemand() { return predictedDemand; }
    public void setPredictedDemand(Integer predictedDemand) { this.predictedDemand = predictedDemand; }

    public Integer getActualDemand() { return actualDemand; }
    public void setActualDemand(Integer actualDemand) { this.actualDemand = actualDemand; }

    public String getModelVersion() { return modelVersion; }
    public void setModelVersion(String modelVersion) { this.modelVersion = modelVersion; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
