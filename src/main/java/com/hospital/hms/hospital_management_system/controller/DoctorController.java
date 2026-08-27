package com.hospital.hms.hospital_management_system.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hospital.hms.hospital_management_system.model.Doctor;
import com.hospital.hms.hospital_management_system.service.DoctorService;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService service;


    // =========================
    // Get all doctors
    // =========================

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {

        return new ResponseEntity<>(
                service.getAllDoctors(),
                HttpStatus.OK
        );
    }


    // =========================
    // Get logged-in doctor
    // =========================

    @GetMapping("/doctors/me")
    public ResponseEntity<?> getMyProfile(
            Authentication authentication) {

        if (authentication == null) {

            return new ResponseEntity<>(
                    "Please login first",
                    HttpStatus.UNAUTHORIZED
            );
        }


        String username =
                authentication.getName();


        Doctor doctor =
                service.getDoctorByUsername(username);


        if (doctor != null) {

            return new ResponseEntity<>(
                    doctor,
                    HttpStatus.OK
            );
        }


        return new ResponseEntity<>(
                "Doctor profile not found",
                HttpStatus.NOT_FOUND
        );
    }


    // =========================
    // Get doctor by ID
    // =========================

    @GetMapping("/doctors/{id}")
    public ResponseEntity<Doctor> getDoctor(
            @PathVariable int id) {

        Doctor doctor =
                service.getDoctorById(id);

        if (doctor != null) {

            return new ResponseEntity<>(
                    doctor,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }


    // =========================
    // Add doctor
    // =========================

    @PostMapping("/doctors")
    public ResponseEntity<?> addDoctor(
            @RequestPart Doctor doctor,
            @RequestPart MultipartFile imageFile) {

        try {

            Doctor doctor1 =
                    service.addDoctor(
                            doctor,
                            imageFile
                    );

            return new ResponseEntity<>(
                    doctor1,
                    HttpStatus.CREATED
            );

        } catch (Exception e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    // =========================
    // Doctor image
    // =========================

    @GetMapping("/doctors/{doctorId}/image")
    public ResponseEntity<byte[]> getImageByDoctorId(
            @PathVariable int doctorId) {

        Doctor doctor =
                service.getDoctorById(doctorId);


        if (doctor == null ||
                doctor.getImageData() == null) {

            return ResponseEntity
                    .notFound()
                    .build();
        }


        return ResponseEntity.ok()
                .contentType(
                        MediaType.valueOf(
                                doctor.getImageType()
                        )
                )
                .body(
                        doctor.getImageData()
                );
    }


    // =========================
    // Update doctor
    // =========================

    @PutMapping("/doctors/{id}")
    public ResponseEntity<String> updateDoctor(
            @PathVariable int id,
            @RequestPart Doctor doctor)
            throws IOException {

        Doctor doctor1 = null;

        try {

            doctor1 =
                    service.updateDoctor(
                            id,
                            doctor
                    );

        } catch (Exception e) {

            return new ResponseEntity<>(
                    "failed to update",
                    HttpStatus.BAD_REQUEST
            );
        }


        if (doctor1 != null) {

            return new ResponseEntity<>(
                    "updated",
                    HttpStatus.OK
            );
        }


        return new ResponseEntity<>(
                "failed to update",
                HttpStatus.BAD_REQUEST
        );
    }


    // =========================
    // Delete doctor
    // =========================

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<String> deleteDoctor(
            @PathVariable int id) {

        Doctor doctor =
                service.getDoctorById(id);


        if (doctor != null) {

            service.deleteDoctor(id);

            return new ResponseEntity<>(
                    "deleted",
                    HttpStatus.OK
            );
        }


        return new ResponseEntity<>(
                "doctor not found",
                HttpStatus.NOT_FOUND
        );
    }
}