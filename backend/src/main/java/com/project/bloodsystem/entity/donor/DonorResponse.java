package com.project.bloodsystem.entity.donor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "donor_response")
public class DonorResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    @Column(nullable = false, length = 50)
    private String requestType;

    private Long requestReferenceId;

    @Column(nullable = false, length = 50)
    private String response;

    @Column(name = "response_date", updatable = false)
    private LocalDateTime responseDate;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @PrePersist
    protected void onCreate() {
        responseDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Donor getDonor() { return donor; }
    public void setDonor(Donor donor) { this.donor = donor; }

    public String getRequestType() { return requestType; }
    public void setRequestType(String requestType) { this.requestType = requestType; }

    public Long getRequestReferenceId() { return requestReferenceId; }
    public void setRequestReferenceId(Long requestReferenceId) { this.requestReferenceId = requestReferenceId; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    public LocalDateTime getResponseDate() { return responseDate; }
    public void setResponseDate(LocalDateTime responseDate) { this.responseDate = responseDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
