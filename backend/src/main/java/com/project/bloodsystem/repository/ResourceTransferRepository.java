package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.transfer.ResourceTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceTransferRepository extends JpaRepository<ResourceTransfer, Long> {
    Optional<ResourceTransfer> findByTransferNumber(String transferNumber);
    List<ResourceTransfer> findBySendingHospitalId(Long hospitalId);
    List<ResourceTransfer> findByReceivingHospitalId(Long hospitalId);
    List<ResourceTransfer> findByStatus(String status);
    boolean existsByTransferNumber(String transferNumber);
}
