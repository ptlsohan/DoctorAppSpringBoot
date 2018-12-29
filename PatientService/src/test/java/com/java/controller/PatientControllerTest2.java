package com.java.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.java.dto.Patient;
import com.java.service.PatientService;

@RunWith(MockitoJUnitRunner.class)
public class PatientControllerTest2 {

	@Mock
	PatientService patientServ;
	MockMvc mvc;
	@InjectMocks
	PatientController controller = new PatientController();
	
	@Before
	public void setup() {
		
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	
	}
	@Test
	public void getActivePatientsTest() throws Exception  {
		
		Patient p= new Patient();
		p.setPatientId(1);
		p.setFirstName("sohan");
		Patient p1= new Patient();
		p1.setPatientId(2);
		p1.setFirstName("manoj");
		List <Patient> list = Arrays.asList(p,p1);
		when(patientServ.findByActive(true)).thenReturn(list);
		
		mvc.perform(get("/patients").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(jsonPath("$[0].firstName", is(p.getFirstName())))
			.andExpect(jsonPath("$[1].firstName", is(p1.getFirstName())));
		
	}
	
	@Test
	public void getPatientsByIdTest() throws Exception  {
		
		Patient p= new Patient();
		p.setPatientId(1);
		p.setFirstName("sohan");
		
		when(patientServ.findById(1)).thenReturn(p);
		
		mvc.perform(get("/patients/1").contentType(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk())
			.andExpect(jsonPath("$.firstName", is(p.getFirstName())));
		
	}
	
	@Test
	public void getPatientsByIdTest1() throws Exception  {
		
		when(patientServ.findById(2)).thenReturn(null);
		
		mvc.perform(get("/patients/2").contentType(MediaType.APPLICATION_JSON)).
			andExpect(status().isNotFound());
		
	}
	
	@Test
	public void addPatientTest() throws Exception  {
		
		Patient p= new Patient();
		p.setPatientId(1);
		p.setFirstName("sohan");
		
		when(patientServ.save(p)).thenReturn(p);
		
		mvc.perform(post("/patients").contentType(MediaType.APPLICATION_JSON)
				.content("{\"patientId\":1, \"firstName\":\"sohan\"}")).
			andExpect(status().isOk())
			.andExpect(jsonPath("$.firstName", is(p.getFirstName()))); 
		
	}
	


}
