package com.hospital.hms.hospital_management_system.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hospital.hms.hospital_management_system.model.Doctor;

import com.hospital.hms.hospital_management_system.repo.DoctorRepo;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepo repo;
	
	public List<Doctor> getAllDoctors() {
		
		return repo.findAll();
	}
	
public Doctor getDoctorById(int id) {
		
		return repo.findById(id).orElse(null);
	}

	
	public Doctor addDoctor(Doctor doctor, MultipartFile imageFile) throws IOException {
		doctor.setImageName(imageFile.getOriginalFilename());
		doctor.setImageType(imageFile.getContentType());
		doctor.setImageData(imageFile.getBytes());
		return repo.save(doctor);
	}

	public Doctor updateDoctor(int id, Doctor doctor) {

	    Doctor existingDoctor = repo.findById(id).orElse(null);

	    if (existingDoctor != null) {

	    	existingDoctor .setName(doctor.getName());
	    	existingDoctor .setAge(doctor.getAge());
	    	existingDoctor .setGender(doctor.getGender());
	    	existingDoctor .setPhone(doctor.getPhone());
	    	existingDoctor .setSpecialization(doctor.getSpecialization());

	        return repo.save(existingDoctor);
	    }

	    return null;
	}

	public void deleteDoctor(int id) {
		repo.deleteById(id);
		
	}


}
