package com.project.bloodsystem.entity.audit;

import com.project.bloodsystem.entity.access.User;
import com.project.bloodsystem.entity.blood.BloodUnit;
import com.project.bloodsystem.entity.institution.Hospital;
import com.project.bloodsystem.entity.platelet.PlateletProduct;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "wastage_record")
public class WastageRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String resourceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blood_unit_id")
    private BloodUnit bloodUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platelet_product_id")
    private PlateletProduct plateletProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @Column(nullable = false)
    private LocalDate wastageDate;

    @Column(nullable = false, length = 100)
    private String reason;

    private Integer quantity = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorized_by")
    private User authorizedBy;

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

    public String getResourceType() { return resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }

    public BloodUnit getBloodUnit() { return bloodUnit; }
    public void setBloodUnit(BloodUnit bloodUnit) { this.bloodUnit = bloodUnit; }

    public PlateletProduct getPlateletProduct() { return plateletProduct; }
    public void setPlateletProduct(PlateletProduct plateletProduct) { this.plateletProduct = plateletProduct; }

    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) { this.hospital = hospital; }

    public LocalDate getWastageDate() { return wastageDate; }
    public void setWastageDate(LocalDate wastageDate) { this.wastageDate = wastageDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public User getAuthorizedBy() { return authorizedBy; }
    public void setAuthorizedBy(User authorizedBy) { this.authorizedBy = authorizedBy; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
