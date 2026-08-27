package com.hospital.hms.hospital_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hospital.hms.hospital_management_system.model.DoctorRegisterRequest;
import com.hospital.hms.hospital_management_system.model.PatientRegisterRequest;
import com.hospital.hms.hospital_management_system.model.Users;
import com.hospital.hms.hospital_management_system.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    // Doctor registration

    @PostMapping("/register/doctor")
    public ResponseEntity<?> registerDoctor(
            @RequestPart("doctor") DoctorRegisterRequest request,
            @RequestPart("imageFile") MultipartFile imageFile) {

        try {

            return new ResponseEntity<>(
                    service.registerDoctor(request, imageFile),
                    HttpStatus.CREATED
            );

        } catch (Exception e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    // Patient registration

    @PostMapping("/register/patient")
    public ResponseEntity<?> registerPatient(
            @RequestPart("patient") PatientRegisterRequest request,
            @RequestPart("imageFile") MultipartFile imageFile) {

        try {

            return new ResponseEntity<>(
                    service.registerPatient(request, imageFile),
                    HttpStatus.CREATED
            );

        } catch (Exception e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    // Login

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Users user) {

        return new ResponseEntity<>(
                service.verify(user),
                HttpStatus.OK
        );
    }
}