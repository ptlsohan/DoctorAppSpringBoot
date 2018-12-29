package com.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.dto.Patient;
//@RepositoryRestResource(path="patients")
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
//	@RestResource(path="firstName")
//	List<Patient> findByFirstName(@Param("name")String firstName);

	@Query(nativeQuery=true,value="select * from Patient where active= :active")
	List<Patient> findByActive(boolean active);

	@Modifying
	@Query("update Patient set firstName=:firstName, lastName=:lastName where patientId=:id")
	void updatePatientById(int id,String firstName, String lastName);
	

}
