package com.java.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.java.dto.Patient;
import com.java.service.PatientService;

@RunWith(SpringRunner.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {
	
	@MockBean PatientService patientServ;
	@Autowired MockMvc mvc;
	
//	@InjectMocks
//	PatientController controller = new PatientController();
//	
//	@Before
//	public void setup() {
//		
//		mvc = MockMvcBuilders.standaloneSetup(controller).build();
//	
//	}
	
	
	
	@Test
	public void getActivePatientsTest() throws Exception  {
		
		Patient p= new Patient();
		p.setPatientId(1);
		p.setFirstName("sohan");
		List <Patient> list = Arrays.asList(p);
		when(patientServ.findByActive(true)).thenReturn(list);
		mvc.perform(get("/patients").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(jsonPath("$[0].firstName", is(p.getFirstName())));
		
	}
	
	@Test
	public void getPatientsByIdTest() throws Exception  {
		when(patientServ.findById(1)).thenReturn(null);		
		mvc.perform(get("/patients/1").contentType(MediaType.APPLICATION_JSON)).
			andExpect(status().isNotFound());
		
	}

}
