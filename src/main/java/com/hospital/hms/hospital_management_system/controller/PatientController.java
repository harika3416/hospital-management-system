package com.hospital.hms.hospital_management_system.controller;

import com.hospital.hms.hospital_management_system.model.Patient;
import com.hospital.hms.hospital_management_system.service.PatientService;

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

@RestController
public class PatientController {

    @Autowired
    private PatientService service;


    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {

        return new ResponseEntity<>(
                service.getAllPatients(),
                HttpStatus.OK
        );
    }


    @GetMapping("/patients/me")
    public ResponseEntity<Patient> getMyProfile(
            Authentication authentication) {

        Patient patient =
                service.getPatientForLoggedInUser(authentication);

        if (patient != null) {

            return new ResponseEntity<>(
                    patient,
                    HttpStatus.OK
            );

        } else {

            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }


    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatient(
            @PathVariable int id) {

        Patient patient =
                service.getPatientById(id);

        if (patient != null) {

            return new ResponseEntity<>(
                    patient,
                    HttpStatus.OK
            );

        } else {

            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }


    @PostMapping("/patients")
    public ResponseEntity<?> addPatient(
            @RequestPart Patient patient,
            @RequestPart MultipartFile imageFile) {

        try {

            Patient patient1 =
                    service.addPatient(
                            patient,
                            imageFile
                    );

            return new ResponseEntity<>(
                    patient1,
                    HttpStatus.CREATED
            );

        } catch (Exception e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @GetMapping("/patients/{patientId}/image")
    public ResponseEntity<byte[]> getImageByPatientId(
            @PathVariable int patientId) {

        Patient patient =
                service.getPatientById(patientId);

        if (patient == null ||
                patient.getImageData() == null) {

            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }

        byte[] imageFile =
                patient.getImageData();

        return ResponseEntity
                .ok()
                .contentType(
                        MediaType.valueOf(
                                patient.getImageType()
                        )
                )
                .body(imageFile);
    }


    @PutMapping("/patients/{id}")
    public ResponseEntity<String> updatePatient(
            @PathVariable int id,
            @RequestPart Patient patient)
            throws IOException {

        Patient patient1 = null;

        try {

            patient1 =
                    service.updatePatient(
                            id,
                            patient
                    );

        } catch (Exception e) {

            return new ResponseEntity<>(
                    "failed to update",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (patient1 != null) {

            return new ResponseEntity<>(
                    "updated",
                    HttpStatus.OK
            );

        } else {

            return new ResponseEntity<>(
                    "failed to update",
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    @DeleteMapping("/patients/{id}")
    public ResponseEntity<String> deletePatient(
            @PathVariable int id) {

        Patient patient =
                service.getPatientById(id);

        if (patient != null) {

            service.deletePatient(id);

            return new ResponseEntity<>(
                    "deleted",
                    HttpStatus.OK
            );

        } else {

            return new ResponseEntity<>(
                    "patient not found",
                    HttpStatus.NOT_FOUND
            );
        }
    }
}