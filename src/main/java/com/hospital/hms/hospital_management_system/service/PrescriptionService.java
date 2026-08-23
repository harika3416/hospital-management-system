package com.hospital.hms.hospital_management_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hms.hospital_management_system.model.Consultation;
import com.hospital.hms.hospital_management_system.model.Prescription;
import com.hospital.hms.hospital_management_system.model.PrescriptionRequest;
import com.hospital.hms.hospital_management_system.model.PrescriptionResponse;
import com.hospital.hms.hospital_management_system.repo.ConsultationRepo;
import com.hospital.hms.hospital_management_system.repo.PrescriptionRepo;

@Service
public class PrescriptionService {
	
	@Autowired
	private ConsultationRepo consultation;
	
	@Autowired
	private PrescriptionRepo repo;

	public PrescriptionResponse createPrescription(PrescriptionRequest request) {
		Consultation c=consultation.findById(request.getConsultationId()).orElse(null);
		  if (c == null) {
	            return null;
	        }
		  
		  Prescription p=new Prescription();
		 p.setConsultation(c);
		 p.setMedicineName(request.getMedicineName());
		 p.setDosage(request.getDosage());
		 p.setFrequency(request.getFrequency());
		 p.setDuration(request.getDuration());
		 p.setInstructions(request.getInstructions());
		 
		 Prescription saved=repo.save(p);
		 
		 PrescriptionResponse response=new PrescriptionResponse();
		 response.setId(saved.getId());
		 response.setConsultationId(saved.getConsultation().getId());
		 response.setMedicineName(saved.getMedicineName());
		response.setDosage(saved.getDosage());
		response.setFrequency(saved.getFrequency());
		response.setDuration(saved.getDuration());
		response.setInstructions(saved.getInstructions());
		
		return response;
	}
	
	public List<PrescriptionResponse> getPrescriptionsByConsultation(int consultationId) {

	    List<Prescription> prescriptions =
	            repo.findByConsultationId(consultationId);

	    List<PrescriptionResponse> responses = new ArrayList<>();

	    for (Prescription p : prescriptions) {

	        PrescriptionResponse response = new PrescriptionResponse();

	        response.setId(p.getId());
	        response.setConsultationId(p.getConsultation().getId());
	        response.setMedicineName(p.getMedicineName());
	        response.setDosage(p.getDosage());
	        response.setFrequency(p.getFrequency());
	        response.setDuration(p.getDuration());
	        response.setInstructions(p.getInstructions());

	        responses.add(response);
	    }

	    return responses;
	}

}
