package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.audit.WastageRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WastageRecordRepository extends JpaRepository<WastageRecord, Long> {
    List<WastageRecord> findByHospitalId(Long hospitalId);
}
