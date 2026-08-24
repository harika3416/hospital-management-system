package com.hospital.hms.hospital_management_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

    @GetMapping("/doctor/test")
    public String doctorTest() {
        return "Doctor access granted";
    }

    @GetMapping("/admin/test")
    public String adminTest() {
        return "Admin access granted";
    }

    @GetMapping("/patient/test")
    public String patientTest() {
        return "Patient access granted";
    }
}