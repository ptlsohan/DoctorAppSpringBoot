package com.java.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.Appointment;

@Repository
@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	@RestResource(path="appointmentId")
	List<Appointment> findByAppointmentId(int appointmentId);
	
	@Query(nativeQuery=true,value="select * from Appointment where doctorId=:id order by dateOfAppointment,timeOfAppointment ")
	List<Appointment> findByDoc(int id);
	
	@Query(nativeQuery=true,value="select * from Appointment where patientId=:id order by dateOfAppointment,timeOfAppointment")
	List<Appointment> findByPatient(int id);
	
	@Query(nativeQuery=true,value="select * from Appointment where doctorId=:doctorId and dateOfAppointment=:date order by timeOfAppointment")
	List<Appointment> findByDate(int doctorId, LocalDate date);
}
