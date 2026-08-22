package com.hospital.hms.hospital_management_system.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Consultation {
	
	@OneToOne
	@JoinColumn(name="appoinment_id")
	private Appoinment appoinment;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
	
	private String diagnosis;
	private String doctorNotes;
	
	private LocalDate consultationDate;

}
