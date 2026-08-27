package com.hospital.hms.hospital_management_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.hospital.hms.hospital_management_system.model.Appoinment;
import com.hospital.hms.hospital_management_system.model.AppoinmentRequest;
import com.hospital.hms.hospital_management_system.model.AppoinmentResponse;
import com.hospital.hms.hospital_management_system.model.Doctor;
import com.hospital.hms.hospital_management_system.model.DoctorPatientResponse;
import com.hospital.hms.hospital_management_system.model.Patient;
import com.hospital.hms.hospital_management_system.model.Users;
import com.hospital.hms.hospital_management_system.repo.AppoinmentRepo;
import com.hospital.hms.hospital_management_system.repo.DoctorRepo;
import com.hospital.hms.hospital_management_system.repo.PatientRepo;
import com.hospital.hms.hospital_management_system.repo.UserRepo;

@Service
public class AppoinmentService {

    @Autowired
    private AppoinmentRepo repo;

    @Autowired
    private PatientRepo patient;

    @Autowired
    private DoctorRepo doctor;

    @Autowired
    private UserRepo userRepo;


    // =====================================================
    // Book Appointment
    // =====================================================

    public AppoinmentResponse bookAppoinments(
            AppoinmentRequest request) {

        Patient p =
                patient.findById(request.getPatientId())
                        .orElse(null);

        Doctor d =
                doctor.findById(request.getDoctorId())
                        .orElse(null);

        if (p == null || d == null) {
            return null;
        }


        Appoinment appointment =
                new Appoinment();

        appointment.setPatient(p);

        appointment.setDoctor(d);

        appointment.setAppointmentDate(
                request.getAppointmentDate()
        );

        appointment.setAppointmentTime(
                request.getAppointmentTime()
        );

        appointment.setReasons(
                request.getReasons()
        );

        appointment.setStatus(
                request.getStatus()
        );


        Appoinment savedAppointment =
                repo.save(appointment);


        return convertToResponse(
                savedAppointment
        );
    }


    // =====================================================
    // Get Appointments For Logged-In User
    // =====================================================

    public List<AppoinmentResponse>
    getAppoinmentsForLoggedInUser(
            Authentication authentication) {

        List<AppoinmentResponse> responses =
                new ArrayList<>();


        if (authentication == null) {
            return responses;
        }


        // Get username from JWT

        String username =
                authentication.getName();


        // Find user using username

        Users user =
                userRepo.findByUsername(username);


        if (user == null) {
            return responses;
        }


        // =================================================
        // PATIENT
        // =================================================

        if ("PATIENT".equals(user.getRole())) {

            Patient loggedInPatient =
                    user.getPatient();


            if (loggedInPatient == null) {
                return responses;
            }


            List<Appoinment> appointments =
                    repo.findByPatient(
                            loggedInPatient
                    );


            for (Appoinment appointment :
                    appointments) {

                responses.add(
                        convertToResponse(
                                appointment
                        )
                );
            }
        }


        // =================================================
        // DOCTOR
        // =================================================

        else if ("DOCTOR".equals(user.getRole())) {

            Doctor loggedInDoctor =
                    user.getDoctor();


            if (loggedInDoctor == null) {
                return responses;
            }


            List<Appoinment> appointments =
                    repo.findByDoctor(
                            loggedInDoctor
                    );


            for (Appoinment appointment :
                    appointments) {

                responses.add(
                        convertToResponse(
                                appointment
                        )
                );
            }
        }


        return responses;
    }


    // =====================================================
    // Get Appointment By ID
    // =====================================================

    public AppoinmentResponse
    getAppoinmentById(int id) {

        Appoinment appointment =
                repo.findById(id)
                        .orElse(null);


        if (appointment == null) {
            return null;
        }


        return convertToResponse(
                appointment
        );
    }


    // =====================================================
    // Update Appointment
    // =====================================================

    public AppoinmentResponse updateAppoinment(
            int id,
            AppoinmentRequest request) {

        Appoinment appointment =
                repo.findById(id)
                        .orElse(null);


        if (appointment == null) {
            return null;
        }


        appointment.setAppointmentDate(
                request.getAppointmentDate()
        );

        appointment.setAppointmentTime(
                request.getAppointmentTime()
        );

        appointment.setReasons(
                request.getReasons()
        );

        appointment.setStatus(
                request.getStatus()
        );


        Appoinment savedAppointment =
                repo.save(appointment);


        return convertToResponse(
                savedAppointment
        );
    }


    // =====================================================
    // Cancel Appointment
    // =====================================================

    public AppoinmentResponse
    cancelAppoinment(int id) {

        Appoinment appointment =
                repo.findById(id)
                        .orElse(null);


        if (appointment == null) {
            return null;
        }


        appointment.setStatus(
                "Cancelled"
        );


        Appoinment savedAppointment =
                repo.save(appointment);


        return convertToResponse(
                savedAppointment
        );
    }

    
 // =====================================================
 // Get Patients For Logged-In Doctor
 // =====================================================

 public List<DoctorPatientResponse> getPatientsForLoggedInDoctor(
         Authentication authentication) {

     List<DoctorPatientResponse> responses =
             new ArrayList<>();


     if (authentication == null) {
         return responses;
     }


     // Get username from JWT

     String username =
             authentication.getName();


     // Find logged-in user

     Users user =
             userRepo.findByUsername(username);


     if (user == null) {
         return responses;
     }


     // Make sure the logged-in user is a doctor

     if (!"DOCTOR".equals(user.getRole())) {
         return responses;
     }


     Doctor loggedInDoctor =
             user.getDoctor();


     if (loggedInDoctor == null) {
         return responses;
     }


     // Get only this doctor's appointments

     List<Appoinment> appointments =
             repo.findByDoctor(loggedInDoctor);


     // Avoid showing the same patient multiple times

     List<Integer> patientIds =
             new ArrayList<>();


     for (Appoinment appointment : appointments) {

         Patient patient =
                 appointment.getPatient();


         if (patient == null) {
             continue;
         }


         if (patientIds.contains(patient.getId())) {
             continue;
         }


         patientIds.add(patient.getId());


         DoctorPatientResponse response =
                 new DoctorPatientResponse();


         response.setPatientId(
                 patient.getId()
         );

         response.setName(
                 patient.getName()
         );

         response.setEmail(
                 patient.getEmail()
         );

         response.setAge(
                 patient.getAge()
         );

         response.setGender(
                 patient.getGender()
         );

         response.setPhone(
                 patient.getPhone()
         );


         responses.add(response);
     }


     return responses;
 }
    
    
    // =====================================================
    // Convert Entity To Response
    // =====================================================

    private AppoinmentResponse
    convertToResponse(Appoinment appointment) {

        AppoinmentResponse response =
                new AppoinmentResponse();


        response.setId(
                appointment.getId()
        );


        // =========================
        // Patient Details
        // =========================

        if (appointment.getPatient() != null) {

            Patient patient =
                    appointment.getPatient();


            response.setPatientId(
                    patient.getId()
            );


            response.setPatientName(
                    patient.getName()
            );


            response.setPatientAge(
                    patient.getAge()
            );


            response.setPatientGender(
                    patient.getGender()
            );


            response.setPatientPhone(
                    patient.getPhone()
            );
        }


        // =========================
        // Doctor Details
        // =========================

        if (appointment.getDoctor() != null) {

            Doctor doctor =
                    appointment.getDoctor();


            response.setDoctorId(
                    doctor.getId()
            );


            response.setDoctorName(
                    doctor.getName()
            );


            response.setDoctorSpecialization(
                    doctor.getSpecialization()
            );
        }


        // =========================
        // Appointment Details
        // =========================

        response.setAppointmentDate(
                appointment.getAppointmentDate()
        );


        response.setAppointmentTime(
                appointment.getAppointmentTime()
        );


        response.setReasons(
                appointment.getReasons()
        );


        response.setStatus(
                appointment.getStatus()
        );


        return response;
    }

}