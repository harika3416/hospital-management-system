package com.hospital.hms.hospital_management_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hms.hospital_management_system.model.Appoinment;
import com.hospital.hms.hospital_management_system.model.AppoinmentRequest;
import com.hospital.hms.hospital_management_system.model.AppoinmentResponse;
import com.hospital.hms.hospital_management_system.model.Doctor;
import com.hospital.hms.hospital_management_system.model.Patient;
import com.hospital.hms.hospital_management_system.repo.AppoinmentRepo;
import com.hospital.hms.hospital_management_system.repo.DoctorRepo;
import com.hospital.hms.hospital_management_system.repo.PatientRepo;

@Service
public class AppoinmentService {

	@Autowired
	private AppoinmentRepo repo;
	@Autowired
	private PatientRepo patient;
	@Autowired
	private DoctorRepo doctor;
	
	public AppoinmentResponse bookAppoinments(AppoinmentRequest request) {
		
		Patient p=patient.findById(request.getPatientId()).orElse(null);
		Doctor d=doctor.findById(request.getDoctorId()).orElse(null);
		
		if(p==null || d==null) {
			return null;
		}
		Appoinment appoinment = new Appoinment();
		appoinment.setPatient(p);
		appoinment.setDoctor(d);
		appoinment.setAppointmentDate(request.getAppointmentDate());
		appoinment.setAppointmentTime(request.getAppointmentTime());
		appoinment.setReasons(request.getReasons());
		appoinment.setStatus(request.getStatus());

		Appoinment savedAppointment = repo.save(appoinment);

		AppoinmentResponse response = new AppoinmentResponse();

		response.setId(savedAppointment.getId());
		response.setPatientId(savedAppointment.getPatient().getId());
		response.setDoctorId(savedAppointment.getDoctor().getId());
		response.setAppointmentDate(savedAppointment.getAppointmentDate());
		response.setAppointmentTime(savedAppointment.getAppointmentTime());
		response.setReasons(savedAppointment.getReasons());
		response.setStatus(savedAppointment.getStatus());

		return response;
	}

	public List<AppoinmentResponse> getAllAppoinments() {

	    List<Appoinment> appoinments = repo.findAll();

	    List<AppoinmentResponse> responses = new ArrayList<>();

	    for (Appoinment a : appoinments) {

	        AppoinmentResponse response = new AppoinmentResponse();

	        response.setId(a.getId());
	        response.setPatientId(a.getPatient().getId());
	        response.setDoctorId(a.getDoctor().getId());
	        response.setAppointmentDate(a.getAppointmentDate());
	        response.setAppointmentTime(a.getAppointmentTime());
	        response.setReasons(a.getReasons());
	        response.setStatus(a.getStatus());

	        responses.add(response);
	    }

	    return responses;
	}

	public AppoinmentResponse getAppoinmentById(int id) {
		 Appoinment a =repo.findById(id).orElse(null);
		  if (a == null) {
		        return null;
		    }
		  AppoinmentResponse response=new AppoinmentResponse();
		  response.setId(a.getId());
		  response.setPatientId(a.getPatient().getId());
		  response.setDoctorId(a.getDoctor().getId());
		  response.setAppointmentDate(a.getAppointmentDate());
		  response.setAppointmentTime(a.getAppointmentTime());
		  response.setReasons(a.getReasons());
		  response.setStatus(a.getStatus());
		  
		  
		  return response;
		  
	}

	public AppoinmentResponse updateAppoinment(int id, AppoinmentRequest request) {

	    Appoinment a = repo.findById(id).orElse(null);

	    if (a == null) {
	        return null;
	    }

	    a.setAppointmentDate(request.getAppointmentDate());
	    a.setAppointmentTime(request.getAppointmentTime());
	    a.setReasons(request.getReasons());
	    a.setStatus(request.getStatus());

	    Appoinment savedAppointment = repo.save(a);

	    AppoinmentResponse response = new AppoinmentResponse();

	    response.setId(savedAppointment.getId());
	    response.setPatientId(savedAppointment.getPatient().getId());
	    response.setDoctorId(savedAppointment.getDoctor().getId());
	    response.setAppointmentDate(savedAppointment.getAppointmentDate());
	    response.setAppointmentTime(savedAppointment.getAppointmentTime());
	    response.setReasons(savedAppointment.getReasons());
	    response.setStatus(savedAppointment.getStatus());

	    return response;
	}

	public AppoinmentResponse cancelAppoinment(int id) {
		 Appoinment a = repo.findById(id).orElse(null);

		    if (a == null) {
		        return null;
		    }
		    a.setStatus("Cancelled");
		    Appoinment savedAppointment = repo.save(a);

		    AppoinmentResponse response = new AppoinmentResponse();

		    response.setId(savedAppointment.getId());
		    response.setPatientId(savedAppointment.getPatient().getId());
		    response.setDoctorId(savedAppointment.getDoctor().getId());
		    response.setAppointmentDate(savedAppointment.getAppointmentDate());
		    response.setAppointmentTime(savedAppointment.getAppointmentTime());
		    response.setReasons(savedAppointment.getReasons());
		    response.setStatus(savedAppointment.getStatus());

		    return response;
		
	}
	}


