package com.hospital.hms.hospital_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hms.hospital_management_system.model.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
	Users findByUsername(String username);

}
