package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.donor.DonorResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorResponseRepository extends JpaRepository<DonorResponse, Long> {
    List<DonorResponse> findByDonorId(Long donorId);
}
