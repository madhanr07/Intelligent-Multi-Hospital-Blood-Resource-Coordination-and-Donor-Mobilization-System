package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.audit.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUserId(Long userId);
    List<AuditLog> findByHospitalId(Long hospitalId);
    List<AuditLog> findByEntityType(String entityType);
}
