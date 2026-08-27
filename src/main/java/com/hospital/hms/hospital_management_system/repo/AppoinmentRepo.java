package com.hospital.hms.hospital_management_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hms.hospital_management_system.model.Appoinment;
import com.hospital.hms.hospital_management_system.model.Doctor;
import com.hospital.hms.hospital_management_system.model.Patient;

public interface AppoinmentRepo
        extends JpaRepository<Appoinment, Integer> {

    // Get appointments of a particular patient
    List<Appoinment> findByPatient(Patient patient);

    // Get appointments of a particular doctor
    List<Appoinment> findByDoctor(Doctor doctor);
}