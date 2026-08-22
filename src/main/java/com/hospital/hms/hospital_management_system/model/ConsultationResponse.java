package com.hospital.hms.hospital_management_system.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConsultationResponse {
	
	

	    private int id;
	    private int appointmentId;
	    private String diagnosis;
	    private String doctorNotes;
	    private LocalDate consultationDate;
	}


