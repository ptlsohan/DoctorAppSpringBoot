package com.java.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.AppointmentRepository;
import com.java.dto.Appointment;

@Service
public class AppointmentService {

	@Autowired AppointmentRepository aptRep;
	
	public List<Appointment> getAptByDoc(int id){
		return aptRep.findByDoc(id);
	}
	
	public List<Appointment> getAptByPatient(int id){
		return aptRep.findByPatient(id);
	}
	
	public List<Appointment> getAptByDate(int id, LocalDate date){
		return aptRep.findByDate(id, date);
	}
}
