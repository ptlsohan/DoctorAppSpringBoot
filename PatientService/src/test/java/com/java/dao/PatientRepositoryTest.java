package com.java.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.dto.Patient;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PatientRepositoryTest {
	
	@Autowired TestEntityManager em;
	@Autowired PatientRepository patientRep;
	
	@Test
	public void test1() {
		Patient p= new Patient();
		p.setFirstName("sohan");
	
		em.persist(p);
		em.flush();
		Optional<Patient> patient=patientRep.findById(1);
		assertNotNull(patient.get());
	}
	
	@Test
	public void test2() {
		LocalDate dob= LocalDate.parse("1993-10-10");
		Patient p= new Patient();
		p.setPatientId(2);
		p.setFirstName("sohan");
		 p.setLastName("patil");
		   p.setEmailId("abc@gmail.com");
		   p.setDob(dob);
	
		
		Patient patient=patientRep.save(p);
		em.flush();
		Optional<Patient> p1=patientRep.findById(2);
		
	//	System.out.println("patient"+p1);
		assertEquals(2,p1.get().getPatientId());
	}
	
	@Test
	public void test3() {
		Patient p= new Patient();
		p.setPatientId(1);
		p.setFirstName("sohan");
	
		
		//Patient patient=patientRep.save(p);
		int id=(int) em.getId(p);
		
		assertEquals(1,id);
	}
	
//	@Test
	public void test4() {
		Patient p= new Patient();
		p.setPatientId(1);
		p.setFirstName("sohan");
		em.persist(p);
		em.flush();
		
		//Patient patient=patientRep.save(p);
		patientRep.delete(p);
		
		
		//assertEquals(1,id);
	}
	

}
