package com.java.service;

import java.util.List;

import com.java.dto.Patient;

public interface PatientService {
	
	public  List<Patient> findByActive(boolean active);
	public Patient findById(int id);
	public Patient save(Patient patient);
	public void updatePatientById(int patientId, String firstName, String lastName);

}
