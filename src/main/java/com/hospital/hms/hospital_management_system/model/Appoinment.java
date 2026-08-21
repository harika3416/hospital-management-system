package com.hospital.hms.hospital_management_system.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class Appoinment {
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate appointmentDate;
	private LocalTime appointmentTime;
	private String reasons;
	private String status;

}
