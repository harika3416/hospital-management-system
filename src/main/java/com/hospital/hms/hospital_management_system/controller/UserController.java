package com.hospital.hms.hospital_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.hms.hospital_management_system.model.Users;
import com.hospital.hms.hospital_management_system.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Users user){
		return new ResponseEntity<>(service.register(user),HttpStatus.CREATED);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Users user) {
		return new ResponseEntity<>(service.verify(user),HttpStatus.OK);
	}

}
