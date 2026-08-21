package com.hospital.hms.hospital_management_system.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class AppoinmentResponse {
	   private int id;
	    private int patientId;
	    private int doctorId;
	    private LocalDate appointmentDate;
	    private LocalTime appointmentTime;
	    private String reasons;
	    private String status;
	}


