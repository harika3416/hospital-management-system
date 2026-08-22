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

import com.hospital.hms.hospital_management_system.model.ConsultationRequest;
import com.hospital.hms.hospital_management_system.model.ConsultationResponse;
import com.hospital.hms.hospital_management_system.service.ConsultationService;

@RestController
public class ConsultationController {
	
	@Autowired
	private ConsultationService service;
	
	@PostMapping("/consultations")
	public ResponseEntity<?> createConsultation(@RequestBody ConsultationRequest request){
		return new ResponseEntity<>(service.createConsulation(request),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/consultations")
	public ResponseEntity<List<ConsultationResponse>> getAllConsultations() {

	    return new ResponseEntity<>(
	            service.getAllConsultations(),
	            HttpStatus.OK
	    );
	}
	
	@GetMapping("/consultations/{id}")
	public ResponseEntity<?> getConsultationById(@PathVariable int id) {

	    ConsultationResponse response = service.getConsultationById(id);

	    if (response != null)
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    else
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
  
}
