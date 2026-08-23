package com.hospital.hms.hospital_management_system.model;

import lombok.Data;

@Data
public class PrescriptionResponse {
	
	 private int id;
	    private int consultationId;
	    private String medicineName;
	    private String dosage;
	    private String frequency;
	    private String duration;
	    private String instructions;

}
