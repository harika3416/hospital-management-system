package com.hospital.hms.hospital_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.hms.hospital_management_system.model.AppoinmentRequest;
import com.hospital.hms.hospital_management_system.model.AppoinmentResponse;
import com.hospital.hms.hospital_management_system.model.DoctorPatientResponse;
import com.hospital.hms.hospital_management_system.service.AppoinmentService;

@RestController
public class AppoinmentController {

    @Autowired
    private AppoinmentService service;


    // =====================================================
    // Book Appointment
    // =====================================================

    @PostMapping("/appoinments")
    public ResponseEntity<?> bookAppoinments(
            @RequestBody AppoinmentRequest request) {

        AppoinmentResponse response =
                service.bookAppoinments(request);


        if (response == null) {

            return new ResponseEntity<>(
                    "Invalid patient or doctor",
                    HttpStatus.BAD_REQUEST
            );
        }


        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }


    // =====================================================
    // Get Appointments Of Logged-In User
    // =====================================================

    @GetMapping("/appoinments")
    public ResponseEntity<List<AppoinmentResponse>>
    getAllAppoinments(
            Authentication authentication) {

        return new ResponseEntity<>(
                service.getAppoinmentsForLoggedInUser(
                        authentication
                ),
                HttpStatus.OK
        );
    }


    // =====================================================
    // Get Appointment By ID
    // =====================================================

    @GetMapping("/appoinments/{id}")
    public ResponseEntity<?> getAppoinments(
            @PathVariable int id) {

        AppoinmentResponse appointment =
                service.getAppoinmentById(id);


        if (appointment != null) {

            return new ResponseEntity<>(
                    appointment,
                    HttpStatus.OK
            );
        }


        return new ResponseEntity<>(
                "Appointment not found",
                HttpStatus.NOT_FOUND
        );
    }


    // =====================================================
    // Update Appointment
    // =====================================================

    @PutMapping("/appoinments/{id}")
    public ResponseEntity<?> updateAppoinment(
            @PathVariable int id,
            @RequestBody AppoinmentRequest request) {

        try {

            AppoinmentResponse appointment =
                    service.updateAppoinment(
                            id,
                            request
                    );


            if (appointment == null) {

                return new ResponseEntity<>(
                        "Appointment not found",
                        HttpStatus.NOT_FOUND
                );
            }


            return new ResponseEntity<>(
                    appointment,
                    HttpStatus.OK
            );

        } catch (Exception e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    // =====================================================
    // Cancel Appointment
    // =====================================================

    @PutMapping("/appoinments/{id}/cancel")
    public ResponseEntity<?> cancelAppoinment(
            @PathVariable int id) {

        AppoinmentResponse appointment =
                service.cancelAppoinment(id);


        if (appointment != null) {

            return new ResponseEntity<>(
                    appointment,
                    HttpStatus.OK
            );
        }


        return new ResponseEntity<>(
                "Appointment not found",
                HttpStatus.NOT_FOUND
        );
    }
    
 // =====================================================
 // Get Patients For Logged-In Doctor
 // =====================================================

 @GetMapping("/doctor/patients")
 public ResponseEntity<List<DoctorPatientResponse>>
 getPatientsForLoggedInDoctor(
         Authentication authentication) {

     return new ResponseEntity<>(
             service.getPatientsForLoggedInDoctor(
                     authentication
             ),
             HttpStatus.OK
     );
 }
}