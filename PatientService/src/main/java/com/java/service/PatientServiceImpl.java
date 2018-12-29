package com.java.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.PatientRepository;
import com.java.dto.Patient;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired PatientRepository patientRep;
	
	public Patient findById(int id) {
		Optional<Patient> p = patientRep.findById(id);
		if(!p.isPresent())
		return p.orElse(new Patient());
		return p.get();
	}
	
	public List<Patient> findByActive(boolean active){
		return patientRep.findByActive(active);
	}

	@Override
	public Patient save(Patient patient) {
		// TODO Auto-generated method stub
		return patientRep.save(patient);
	}

	@Override
	public void updatePatientById(int patientId, String firstName, String lastName) {
		 patientRep.updatePatientById(patientId, firstName, lastName);
		
	}

}
