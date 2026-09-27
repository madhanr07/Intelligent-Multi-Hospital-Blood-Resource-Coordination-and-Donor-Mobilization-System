package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.intelligence.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    List<Prediction> findByHospitalId(Long hospitalId);
}
