package com.java.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.Appointment;

import com.java.service.AppointmentService;

@BasePathAwareController
@RequestMapping(path="appointments")
public class AppointmentController {

	@Autowired AppointmentService aptServ;
	
	@GetMapping(path="doc/{id}")
	public ResponseEntity<?> getAptByDocEmail(@PathVariable("id") int id){
		List<Appointment> list = aptServ.getAptByDoc(id);
		
		if (list == null) {
			return ResponseEntity.notFound().build();
		}
		

		return ResponseEntity.ok(list);

		
	}
	
	@GetMapping(path="patient/{id}")
	public ResponseEntity<?> getAptByPatientEmail(@PathVariable("id") int id){
		List<Appointment> list = aptServ.getAptByPatient(id);
		
		if (list == null) {
			return ResponseEntity.notFound().build();
		}
		

		return ResponseEntity.ok(list);

		
	}
	
	@GetMapping(path="calendar/{id}")
	public ResponseEntity<?> getAptByDate(@PathVariable("id") int id,@RequestParam("date")LocalDate date){
		List<Appointment> list = aptServ.getAptByDate(id,date);
		
		if (list == null) {
			return ResponseEntity.notFound().build();
		}
		

		return ResponseEntity.ok(list);

		
	}
}
