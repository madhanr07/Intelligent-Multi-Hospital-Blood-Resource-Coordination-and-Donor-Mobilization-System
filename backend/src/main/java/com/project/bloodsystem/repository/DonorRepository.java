package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.donor.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    Optional<Donor> findByRegistrationNumber(String registrationNumber);
    List<Donor> findByBloodGroupAndRhFactor(String bloodGroup, String rhFactor);
    List<Donor> findByPreferredHospitalId(Long hospitalId);
    boolean existsByRegistrationNumber(String registrationNumber);
}
