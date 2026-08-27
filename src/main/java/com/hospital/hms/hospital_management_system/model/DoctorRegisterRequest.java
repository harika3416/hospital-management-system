package com.hospital.hms.hospital_management_system.model;

import lombok.Data;

@Data
public class DoctorRegisterRequest {

    private String name;
    private int age;
    private String gender;
    private String phone;
    private String specialization;

    private String username;
    private String password;
}