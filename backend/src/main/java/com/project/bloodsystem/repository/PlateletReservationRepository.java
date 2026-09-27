package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.platelet.PlateletReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlateletReservationRepository extends JpaRepository<PlateletReservation, Long> {
    List<PlateletReservation> findByHospitalId(Long hospitalId);
    List<PlateletReservation> findByStatus(String status);
}
