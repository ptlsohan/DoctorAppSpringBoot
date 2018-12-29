package com.java.dao;

import java.time.LocalDate;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.java.dto.Doctor;

@Repository
//@RepositoryRestResource(path="doctors")
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	@Query(nativeQuery=true,value="select * from Doctor where firstName= :firstName")
	List<Doctor> findByFirstName(String firstName);
	
	@Query(nativeQuery=true,value="select * from Doctor NATURAL JOIN doctor_speciality where speciality_id in (select id from speciality where name=:speciality)")
	Set<Doctor> findBySpecialities_Name(String  speciality);
	
	
	@Query(nativeQuery=true,value="select * from Doctor NATURAL JOIN doctor_speciality where speciality_id in (select id from speciality where name=:speciality) and location=:location")
	Set<Doctor> findBySpecialities_NameAndLocation(String  speciality,String location);
//	Set<Doctor> findBySchedule_Date(LocalDate date);
//	@RestResource(path="schedule")
//	Set<Doctor> findBySpecialities_NameAndSchedule_Date(String  name,LocalDate date);

	
}
