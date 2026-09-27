package com.project.bloodsystem.entity.transfer;

import com.project.bloodsystem.entity.blood.BloodReservation;
import com.project.bloodsystem.entity.blood.BloodUnit;
import com.project.bloodsystem.entity.platelet.PlateletProduct;
import com.project.bloodsystem.entity.platelet.PlateletReservation;
import jakarta.persistence.*;

@Entity
@Table(name = "resource_transfer_item")
public class ResourceTransferItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_transfer_id", nullable = false)
    private ResourceTransfer resourceTransfer;

    @Column(nullable = false, length = 50)
    private String resourceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blood_unit_id")
    private BloodUnit bloodUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platelet_product_id")
    private PlateletProduct plateletProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blood_reservation_id")
    private BloodReservation bloodReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platelet_reservation_id")
    private PlateletReservation plateletReservation;

    private Integer quantity = 1;

    @Column(columnDefinition = "TEXT")
    private String notes;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ResourceTransfer getResourceTransfer() { return resourceTransfer; }
    public void setResourceTransfer(ResourceTransfer resourceTransfer) { this.resourceTransfer = resourceTransfer; }

    public String getResourceType() { return resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }

    public BloodUnit getBloodUnit() { return bloodUnit; }
    public void setBloodUnit(BloodUnit bloodUnit) { this.bloodUnit = bloodUnit; }

    public PlateletProduct getPlateletProduct() { return plateletProduct; }
    public void setPlateletProduct(PlateletProduct plateletProduct) { this.plateletProduct = plateletProduct; }

    public BloodReservation getBloodReservation() { return bloodReservation; }
    public void setBloodReservation(BloodReservation bloodReservation) { this.bloodReservation = bloodReservation; }

    public PlateletReservation getPlateletReservation() { return plateletReservation; }
    public void setPlateletReservation(PlateletReservation plateletReservation) { this.plateletReservation = plateletReservation; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
