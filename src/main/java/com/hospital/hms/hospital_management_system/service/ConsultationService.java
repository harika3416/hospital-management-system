package com.hospital.hms.hospital_management_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hms.hospital_management_system.model.Appoinment;

import com.hospital.hms.hospital_management_system.model.Consultation;
import com.hospital.hms.hospital_management_system.model.ConsultationRequest;
import com.hospital.hms.hospital_management_system.model.ConsultationResponse;

import com.hospital.hms.hospital_management_system.repo.AppoinmentRepo;
import com.hospital.hms.hospital_management_system.repo.ConsultationRepo;

@Service
public class ConsultationService {

	 @Autowired
	    private ConsultationRepo repo;

	    @Autowired
	    private AppoinmentRepo appoinment;
	
	public ConsultationResponse createConsulation(ConsultationRequest request) {
		
		 Appoinment appoinment1 =
	                appoinment.findById(request.getAppoinmentId())
	                              .orElse(null);

	        if (appoinment1 == null) {
	            return null;
	        }
	        Consultation c=new Consultation();
	        c.setAppoinment(appoinment1);
	        c.setDiagnosis(request.getDiagnosis());
	        c.setDoctorNotes(request.getDoctorNotes());
	        c.setConsultationDate(request.getConsultationDate());
	        
	        Consultation saved=repo.save(c);
	        
	        ConsultationResponse response=new ConsultationResponse();
	        
	       
	        response.setId(saved.getId());
	        response.setAppointmentId(saved.getAppoinment().getId());
	        response.setDiagnosis(saved.getDiagnosis());
	        response.setDoctorNotes(saved.getDoctorNotes());
	        response.setConsultationDate(saved.getConsultationDate());
	        
	        return response;
	        
	        }
	
	public List<ConsultationResponse> getAllConsultations() {

	    List<Consultation> consultations = repo.findAll();

	    List<ConsultationResponse> responses = new ArrayList<>();

	    for (Consultation c : consultations) {

	        ConsultationResponse response = new ConsultationResponse();

	        response.setId(c.getId());
	        response.setAppointmentId(c.getAppoinment().getId());
	        response.setDiagnosis(c.getDiagnosis());
	        response.setDoctorNotes(c.getDoctorNotes());
	        response.setConsultationDate(c.getConsultationDate());

	        responses.add(response);
	    }

	    return responses;
	}
	public ConsultationResponse getConsultationById(int id) {

	    Consultation c = repo.findById(id).orElse(null);

	    if (c == null) {
	        return null;
	    }

	    ConsultationResponse response = new ConsultationResponse();

	    response.setId(c.getId());
	    response.setAppointmentId(c.getAppoinment().getId());
	    response.setDiagnosis(c.getDiagnosis());
	    response.setDoctorNotes(c.getDoctorNotes());
	    response.setConsultationDate(c.getConsultationDate());

	    return response;
	}

}
