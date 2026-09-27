package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.donor.DonorVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorVerificationRepository extends JpaRepository<DonorVerification, Long> {
    List<DonorVerification> findByDonorId(Long donorId);
}
