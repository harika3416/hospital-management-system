package com.hospital.hms.hospital_management_system.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class AppoinmentResponse {

    private int id;

    // =========================
    // Patient Details
    // =========================

    private int patientId;

    private String patientName;

    private int patientAge;

    private String patientGender;

    private String patientPhone;


    // =========================
    // Doctor Details
    // =========================

    private int doctorId;

    private String doctorName;

    private String doctorSpecialization;


    // =========================
    // Appointment Details
    // =========================

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private String reasons;

    private String status;
}