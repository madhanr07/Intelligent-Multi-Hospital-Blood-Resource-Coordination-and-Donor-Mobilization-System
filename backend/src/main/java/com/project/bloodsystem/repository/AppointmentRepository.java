package com.project.bloodsystem.repository;

import com.project.bloodsystem.entity.donor.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDonorId(Long donorId);
    List<Appointment> findByHospitalId(Long hospitalId);
    List<Appointment> findByStatus(String status);
}
