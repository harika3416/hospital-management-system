package com.hospital.hms.hospital_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hms.hospital_management_system.model.Consultation;

public interface ConsultationRepo extends JpaRepository<Consultation, Integer> {

}
