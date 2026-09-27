package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.donor.DonorDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorDocumentRepository extends JpaRepository<DonorDocument, Long> {
    List<DonorDocument> findByDonorId(Long donorId);
}
