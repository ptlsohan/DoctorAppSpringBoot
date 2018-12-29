package com.java.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dao.PatientRepository;
import com.java.dto.Patient;
import com.java.service.PatientService;


@BasePathAwareController
@RequestMapping(path="patients")
public class PatientController {

	
	@Autowired PatientService patientServ;
	
	
	@GetMapping
	public ResponseEntity<?> getActivePatients() {
		List<Patient> list = patientServ.findByActive(true);
		
		if (list == null) {
//			list = new ArrayList<>();
			return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<?> findPatient(@PathVariable("id") int id) {
		Patient patient = patientServ.findById(id);
		if (patient == null) {
			return ResponseEntity.notFound().build();
		}
		

		return ResponseEntity.ok(patient);
	}

	
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> addUser(@RequestBody Patient patient){
		HttpHeaders header = new HttpHeaders();
		List<String> values= Arrays.asList("application/json","application/xml");
		header.addAll("content-type", values);
		patient.setActive(true);
		Patient newPatient=patientServ.save(patient);
		
		
		return new ResponseEntity<Integer>(newPatient.getPatientId(), HttpStatus.OK);
	}
	
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> updatePatient(@RequestBody Patient patient){
		HttpHeaders header = new HttpHeaders();
		List<String> values= Arrays.asList("application/json","application/xml");
		header.addAll("content-type", values);
		patientServ.updatePatientById(patient.getPatientId(),patient.getFirstName(),patient.getLastName());
		
		return new ResponseEntity<String>("User updated", HttpStatus.OK);
		
	}
}
