package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.blood.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloodRequestRepository extends JpaRepository<BloodRequest, Long> {
    Optional<BloodRequest> findByRequestNumber(String requestNumber);
    List<BloodRequest> findByHospitalId(Long hospitalId);
    List<BloodRequest> findByStatus(String status);
    boolean existsByRequestNumber(String requestNumber);
}
