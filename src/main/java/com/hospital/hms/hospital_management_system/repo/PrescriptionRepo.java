package com.hospital.hms.hospital_management_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hms.hospital_management_system.model.Prescription;

public interface PrescriptionRepo extends JpaRepository<Prescription, Integer>{
	List<Prescription> findByConsultationId(int consultationId);

}
