package com.project.bloodsystem.entity.platelet;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "platelet_request_item")
public class PlateletRequestItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platelet_request_id", nullable = false)
    private PlateletRequest plateletRequest;

    @Column(nullable = false, length = 10)
    private String bloodGroup;

    @Column(nullable = false, length = 10)
    private String rhFactor;

    @Column(nullable = false)
    private Integer quantityRequested;

    private Integer quantityFulfilled = 0;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PlateletRequest getPlateletRequest() { return plateletRequest; }
    public void setPlateletRequest(PlateletRequest plateletRequest) { this.plateletRequest = plateletRequest; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getRhFactor() { return rhFactor; }
    public void setRhFactor(String rhFactor) { this.rhFactor = rhFactor; }

    public Integer getQuantityRequested() { return quantityRequested; }
    public void setQuantityRequested(Integer quantityRequested) { this.quantityRequested = quantityRequested; }

    public Integer getQuantityFulfilled() { return quantityFulfilled; }
    public void setQuantityFulfilled(Integer quantityFulfilled) { this.quantityFulfilled = quantityFulfilled; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
