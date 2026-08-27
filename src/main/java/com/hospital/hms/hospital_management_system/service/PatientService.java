package com.hospital.hms.hospital_management_system.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hospital.hms.hospital_management_system.model.Patient;
import com.hospital.hms.hospital_management_system.model.Users;
import com.hospital.hms.hospital_management_system.repo.PatientRepo;
import com.hospital.hms.hospital_management_system.repo.UserRepo;

@Service
public class PatientService {

    @Autowired
    private PatientRepo repo;

    @Autowired
    private UserRepo userRepo;


    public List<Patient> getAllPatients() {

        return repo.findAll();
    }


    public Patient getPatientById(int id) {

        return repo.findById(id).orElse(null);
    }


    /*
     * Get the patient belonging to
     * the currently logged-in user.
     */
    public Patient getPatientForLoggedInUser(
            Authentication authentication) {

        String username =
                authentication.getName();


        Users user =
                userRepo.findByUsername(username);


        if (user == null) {
            return null;
        }


        if (!"PATIENT".equals(user.getRole())) {
            return null;
        }


        return user.getPatient();
    }


    public Patient addPatient(
            Patient patient,
            MultipartFile imageFile)
            throws IOException {

        patient.setImageName(
                imageFile.getOriginalFilename()
        );

        patient.setImageType(
                imageFile.getContentType()
        );

        patient.setImageData(
                imageFile.getBytes()
        );

        return repo.save(patient);
    }


    public Patient updatePatient(
            int id,
            Patient patient) {

        Patient existingPatient =
                repo.findById(id).orElse(null);


        if (existingPatient != null) {

            existingPatient.setName(
                    patient.getName()
            );

            existingPatient.setAge(
                    patient.getAge()
            );

            existingPatient.setGender(
                    patient.getGender()
            );

            existingPatient.setPhone(
                    patient.getPhone()
            );

            existingPatient.setDisease(
                    patient.getDisease()
            );

            return repo.save(existingPatient);
        }


        return null;
    }


    public void deletePatient(int id) {

        repo.deleteById(id);
    }
}