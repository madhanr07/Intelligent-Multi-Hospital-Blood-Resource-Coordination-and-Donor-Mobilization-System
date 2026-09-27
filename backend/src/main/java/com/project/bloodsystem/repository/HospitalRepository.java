package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.institution.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Optional<Hospital> findByCode(String code);
    List<Hospital> findByInstitutionId(Long institutionId);
    boolean existsByCode(String code);
}
