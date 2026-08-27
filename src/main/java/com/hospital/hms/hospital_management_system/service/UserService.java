package com.hospital.hms.hospital_management_system.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hospital.hms.hospital_management_system.model.Doctor;
import com.hospital.hms.hospital_management_system.model.DoctorRegisterRequest;
import com.hospital.hms.hospital_management_system.model.Patient;
import com.hospital.hms.hospital_management_system.model.PatientRegisterRequest;
import com.hospital.hms.hospital_management_system.model.Users;
import com.hospital.hms.hospital_management_system.repo.DoctorRepo;
import com.hospital.hms.hospital_management_system.repo.PatientRepo;
import com.hospital.hms.hospital_management_system.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;


    // =========================
    // Doctor Registration
    // =========================

    public Users registerDoctor(
            DoctorRegisterRequest request,
            MultipartFile imageFile) throws IOException {

        // 1. Create doctor profile

        Doctor doctor = new Doctor();

        doctor.setName(request.getName());
        doctor.setAge(request.getAge());
        doctor.setGender(request.getGender());
        doctor.setPhone(request.getPhone());
        doctor.setSpecialization(
                request.getSpecialization()
        );


        // 2. Save doctor image

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


        // 3. Save doctor profile

        Doctor savedDoctor =
                doctorRepo.save(doctor);


        // 4. Create user account

        Users user = new Users();

        user.setUsername(
                request.getUsername()
        );

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole("DOCTOR");


        // 5. Connect user account with doctor

        user.setDoctor(savedDoctor);


        // 6. Save user account

        return repo.save(user);
    }


    // =========================
    // Patient Registration
    // =========================

    public Users registerPatient(
            PatientRegisterRequest request,
            MultipartFile imageFile)
            throws IOException {

        // 1. Create patient profile

        Patient patient = new Patient();

        patient.setName(request.getName());
        patient.setEmail(request.getEmail());
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setPhone(request.getPhone());


        // 2. Save patient image

        if (imageFile != null &&
                !imageFile.isEmpty()) {

            patient.setImageName(
                    imageFile.getOriginalFilename()
            );

            patient.setImageType(
                    imageFile.getContentType()
            );

            patient.setImageData(
                    imageFile.getBytes()
            );
        }


        // 3. Save patient

        Patient savedPatient =
                patientRepo.save(patient);


        // 4. Create user account

        Users user = new Users();

        user.setUsername(
                request.getUsername()
        );

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole("PATIENT");


        // 5. Connect user account with patient

        user.setPatient(savedPatient);


        // 6. Save user

        return repo.save(user);
    }


    // =========================
    // Login
    // =========================

    public String verify(Users user) {

        Authentication authentication =
                authenticationManager.authenticate(

                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword()
                        )
                );


        if (authentication.isAuthenticated()) {

            UserDetails userDetails =
                    (UserDetails)
                    authentication.getPrincipal();

            return jwtService.generateToken(
                    userDetails
            );
        }


        return "Login Failed";
    }
}