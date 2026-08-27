package com.hospital.hms.hospital_management_system.model;

import lombok.Data;

@Data
public class DoctorPatientResponse {

    private int patientId;

    private String name;

    private String email;

    private int age;

    private String gender;

    private String phone;
}