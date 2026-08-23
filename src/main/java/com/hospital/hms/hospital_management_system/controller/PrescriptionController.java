package com.hospital.hms.hospital_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.hms.hospital_management_system.model.PrescriptionRequest;
import com.hospital.hms.hospital_management_system.model.PrescriptionResponse;
import com.hospital.hms.hospital_management_system.service.PrescriptionService;

@RestController
public class PrescriptionController {
	
	@Autowired
	private PrescriptionService service;
	
	@PostMapping("/prescriptions")
	public ResponseEntity<?> createPrescription(@RequestBody PrescriptionRequest request){
		return new ResponseEntity<>(service.createPrescription(request),HttpStatus.CREATED);
	}
	
	@GetMapping("/prescriptions/consultation/{consultationId}")
	public ResponseEntity<List<PrescriptionResponse>> getPrescriptionsByConsultation(
	        @PathVariable int consultationId) {

	    return new ResponseEntity<>(
	            service.getPrescriptionsByConsultation(consultationId),
	            HttpStatus.OK
	    );
	}

}
