package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.intelligence.RiskAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, Long> {
    List<RiskAssessment> findByHospitalId(Long hospitalId);
}
