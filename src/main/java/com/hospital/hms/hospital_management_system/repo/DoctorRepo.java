package com.hospital.hms.hospital_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hms.hospital_management_system.model.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Integer> {

}
