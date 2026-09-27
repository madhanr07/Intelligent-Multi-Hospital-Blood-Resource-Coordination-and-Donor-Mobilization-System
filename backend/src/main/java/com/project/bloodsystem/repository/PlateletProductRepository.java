package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.platelet.PlateletProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlateletProductRepository extends JpaRepository<PlateletProduct, Long> {
    Optional<PlateletProduct> findByProductNumber(String productNumber);
    List<PlateletProduct> findByHospitalId(Long hospitalId);
    List<PlateletProduct> findByBloodGroupAndRhFactorAndStatus(String bloodGroup, String rhFactor, String status);
    boolean existsByProductNumber(String productNumber);
}
