package com.java.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientIntegrationTest {
//	@Autowired MockMvc mvc;
//	@Autowired PatientController controller;

	@Autowired TestRestTemplate template;
	
	@Test
	public void test1() throws Exception {
		
//		mvc.perform(get("/patients").contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk());
		
		ResponseEntity<List> response=template.getForEntity("/patients",List.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());

	}

}
