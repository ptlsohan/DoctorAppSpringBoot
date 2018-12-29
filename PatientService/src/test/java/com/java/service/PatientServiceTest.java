package com.java.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.dao.PatientRepository;
import com.java.dto.Patient;


@RunWith(SpringRunner.class)
public class PatientServiceTest {
	@TestConfiguration
	static class Config{
		 @Bean
	        public PatientService getService() {
	            return new PatientServiceImpl();
	        }
	}
	
	@MockBean PatientRepository patientRep;
	@Autowired
    private PatientService patientServ;
	
	@Test
	public void findPatient() {
			LocalDate dob= LocalDate.parse("1993-10-10");
		   Patient p= new Patient();
		   p.setPatientId(1);
		   p.setFirstName("sohan");
		   p.setLastName("patil");
		   p.setEmailId("abc@gmail.com");
		   p.setDob(dob);
		   Optional<Patient> p1= Optional.ofNullable(p);
		   
		   Mockito.when(patientRep.findById(1)).thenReturn(p1);
		   Patient patient=patientServ.findById(1);
		   assertNotNull(patient);
		   assertEquals(1, patient.getPatientId());
		   assertEquals("sohan", patient.getFirstName());
	}
	@Test
	public void findPatient1() {
			
		   Patient p= new Patient();
		   
		   Optional<Patient> p1= Optional.ofNullable(null);
		   
		   Mockito.when(patientRep.findById(1)).thenReturn(p1);
		   Patient patient=patientServ.findById(1);
		  
		   assertEquals(p, patient);
		   System.out.println(p);

	}
	
	@Test
	public void findByActiveTest() {
			LocalDate dob= LocalDate.parse("1993-10-10");
		   Patient p= new Patient();
		   p.setPatientId(1);
		   p.setFirstName("sohan");
		   p.setLastName("patil");
		   p.setEmailId("abc@gmail.com");
		   p.setDob(dob);
		   
		   Patient p1= new Patient();
		   p1.setPatientId(2);
		   p1.setFirstName("manoj");
		   p1.setEmailId("test@gmail.com");
		   p1.setDob(dob);
		   p1.setActive(true);
		   
		   List<Patient> list= Arrays.asList(p,p1);
		   
		   Mockito.when(patientRep.findByActive(true)).thenReturn(list);
		   List<Patient> patients=patientServ.findByActive(true);
		   assertNotNull(patients);
		   assertEquals(1, patients.get(0).getPatientId());
		   assertEquals(2, patients.get(1).getPatientId());
		   
	}
	

}
