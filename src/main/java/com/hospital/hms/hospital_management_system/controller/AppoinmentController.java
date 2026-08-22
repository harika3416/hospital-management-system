package com.hospital.hms.hospital_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;


import com.hospital.hms.hospital_management_system.model.AppoinmentRequest;
import com.hospital.hms.hospital_management_system.model.AppoinmentResponse;

import com.hospital.hms.hospital_management_system.service.AppoinmentService;

@RestController
public class AppoinmentController {
	
	@Autowired
	private AppoinmentService service;
	
	
	@PostMapping("/appoinments")
	public ResponseEntity<?> bookAppoinments(@RequestBody AppoinmentRequest request){
		
		return new ResponseEntity<>(service.bookAppoinments(request),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/appoinments")
	public ResponseEntity<List<AppoinmentResponse>> getAllAppoinments(){
		
		return new ResponseEntity<>(service.getAllAppoinments(),HttpStatus.OK);
		
	}
	
	@GetMapping("/appoinments/{id}")
	public ResponseEntity<?> getAppoinments(@PathVariable int id){
      AppoinmentResponse appoinment=service.getAppoinmentById(id);
		
		if(appoinment != null)
		    return new ResponseEntity<>(appoinment,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	
	@PutMapping("/appoinments/{id}")
	public ResponseEntity<?> updateAppoinment(@PathVariable int id, @RequestBody AppoinmentRequest request){
		 try {
			 AppoinmentResponse appoinment1=service.updateAppoinment(id,request);
			 return new ResponseEntity<>(appoinment1,HttpStatus.OK);
		 }
		 catch(Exception e) {
			 return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }
	
	@PutMapping("/appoinments/{id}/cancel")
	public ResponseEntity<?> cancelAppoinment(@PathVariable int id) {
       AppoinmentResponse appoinment=service.cancelAppoinment(id);
       if(appoinment != null)
		    return new ResponseEntity<>(appoinment,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
       
	}


