package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.blood.BloodUsageRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodUsageRecordRepository extends JpaRepository<BloodUsageRecord, Long> {
    List<BloodUsageRecord> findByHospitalId(Long hospitalId);
}
