package com.hospital.hms.hospital_management_system.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConsultationRequest {
	
	private int appoinmentId;
	
	private String diagnosis;
	private String doctorNotes;
	private LocalDate consultationDate;
	

}
