package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.intelligence.BloodDemandRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodDemandRecordRepository extends JpaRepository<BloodDemandRecord, Long> {
    List<BloodDemandRecord> findByHospitalId(Long hospitalId);
}
