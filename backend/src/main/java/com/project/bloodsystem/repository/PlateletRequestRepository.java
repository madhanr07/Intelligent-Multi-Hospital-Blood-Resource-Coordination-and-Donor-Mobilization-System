package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.platelet.PlateletRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlateletRequestRepository extends JpaRepository<PlateletRequest, Long> {
    Optional<PlateletRequest> findByRequestNumber(String requestNumber);
    List<PlateletRequest> findByHospitalId(Long hospitalId);
    List<PlateletRequest> findByStatus(String status);
    boolean existsByRequestNumber(String requestNumber);
}
