package com.hospital.hms.hospital_management_system.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hospital.hms.hospital_management_system.model.Doctor;
import com.hospital.hms.hospital_management_system.model.Users;
import com.hospital.hms.hospital_management_system.repo.DoctorRepo;
import com.hospital.hms.hospital_management_system.repo.UserRepo;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepo repo;

    @Autowired
    private UserRepo userRepo;


    // Get all doctors

    public List<Doctor> getAllDoctors() {

        return repo.findAll();
    }


    // Get doctor by ID

    public Doctor getDoctorById(int id) {

        return repo.findById(id).orElse(null);
    }


    // Get logged-in doctor's profile

    public Doctor getDoctorByUsername(String username) {

        Users user = userRepo.findByUsername(username);

        if (user == null) {
            return null;
        }

        return user.getDoctor();
    }


    // Add doctor

    public Doctor addDoctor(
            Doctor doctor,
            MultipartFile imageFile) throws IOException {

        if (imageFile != null && !imageFile.isEmpty()) {

            doctor.setImageName(
                    imageFile.getOriginalFilename()
            );

            doctor.setImageType(
                    imageFile.getContentType()
            );

            doctor.setImageData(
                    imageFile.getBytes()
            );
        }

        return repo.save(doctor);
    }


    // Update doctor

    public Doctor updateDoctor(
            int id,
            Doctor doctor) {

        Doctor existingDoctor =
                repo.findById(id).orElse(null);

        if (existingDoctor != null) {

            existingDoctor.setName(
                    doctor.getName()
            );

            existingDoctor.setAge(
                    doctor.getAge()
            );

            existingDoctor.setGender(
                    doctor.getGender()
            );

            existingDoctor.setPhone(
                    doctor.getPhone()
            );

            existingDoctor.setSpecialization(
                    doctor.getSpecialization()
            );

            return repo.save(existingDoctor);
        }

        return null;
    }


    // Delete doctor

    public void deleteDoctor(int id) {

        repo.deleteById(id);
    }
}