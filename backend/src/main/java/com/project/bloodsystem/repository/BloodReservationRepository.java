package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.blood.BloodReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodReservationRepository extends JpaRepository<BloodReservation, Long> {
    List<BloodReservation> findByHospitalId(Long hospitalId);
    List<BloodReservation> findByStatus(String status);
}
