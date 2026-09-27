package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.blood.BloodUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloodUnitRepository extends JpaRepository<BloodUnit, Long> {
    Optional<BloodUnit> findByUnitNumber(String unitNumber);
    List<BloodUnit> findByHospitalId(Long hospitalId);
    List<BloodUnit> findByBloodGroupAndRhFactorAndStatus(String bloodGroup, String rhFactor, String status);
    boolean existsByUnitNumber(String unitNumber);
}
