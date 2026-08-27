package com.hospital.hms.hospital_management_system.model;

import lombok.Data;

@Data
public class PatientRegisterRequest {

    private String name;

    private String email;

    private int age;

    private String gender;

    private String phone;

    private String username;

    private String password;
}