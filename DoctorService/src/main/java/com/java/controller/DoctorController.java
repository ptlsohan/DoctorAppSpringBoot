package com.java.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.java.dao.DoctorRepository;
import com.java.dto.Doctor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RepositoryRestController
@RequestMapping(path="doctors")
public class DoctorController {

	@Autowired RestTemplate template;
	@Autowired DoctorRepository doctorRep;
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor){
		HttpHeaders header = new HttpHeaders();
		List<String> values= Arrays.asList("application/json","application/xml");
		header.addAll("content-type", values);
		Doctor newDoctor=doctorRep.save(doctor);
	
		return new ResponseEntity<Integer>(newDoctor.getDoctorId(), HttpStatus.CREATED);
	}
//	
//	@GetMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
//			MediaType.APPLICATION_XML_VALUE })
//	public ResponseEntity<List<Doctor>> getDoctorByFirstName(@RequestParam("name") String name){
//		HttpHeaders header = new HttpHeaders();
//		List<String> values= Arrays.asList("application/json","application/xml");
//		header.addAll("content-type", values);
//		List<Doctor> doctors=doctorRep.findByFirstName(name);
//	
//		return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
//	}
	
	@GetMapping(path="search",consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Set<Doctor>> getDoctorBySpeciality(@RequestParam("speciality") String speciality){
		HttpHeaders header = new HttpHeaders();
		List<String> values= Arrays.asList("application/json","application/xml");
		header.addAll("content-type", values);
		Set<Doctor> doctors=doctorRep.findBySpecialities_Name(speciality);
	
		return new ResponseEntity<Set<Doctor>>(doctors, HttpStatus.OK);
	}
	
	@GetMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Set<Doctor>> getDoctorBySpeciality(@RequestParam("speciality") String speciality,@RequestParam("location") String location){
		HttpHeaders header = new HttpHeaders();
		List<String> values= Arrays.asList("application/json","application/xml");
		header.addAll("content-type", values);
		Set<Doctor> doctors=doctorRep.findBySpecialities_NameAndLocation(speciality, location);
	
		return new ResponseEntity<Set<Doctor>>(doctors, HttpStatus.OK);
	}
	
	@PutMapping(path="{id}",consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> updateDoctor(@PathVariable int id,@RequestBody Doctor doctor){
		HttpHeaders header = new HttpHeaders();
		List<String> values= Arrays.asList("application/json","application/xml");
		header.addAll("content-type", values);
		doctor.setDoctorId(id);
		Doctor newDoctor=doctorRep.save(doctor);
		if(newDoctor==null) {
			return ResponseEntity.badRequest().build();
		}
		
		JSONObject obj = new JSONObject();
		
		obj.put("firstName",doctor.getFirstName());
		obj.put("lastName",doctor.getLastName());
		obj.put("emailId",doctor.getEmailId());
		HttpEntity <String> httpEntity = new HttpEntity <String> (obj.toString(), header);
		ResponseEntity<String> response=template.exchange("http://user-service/users/"+id, HttpMethod.PUT, httpEntity,String.class);
		return new ResponseEntity<String>(response.getBody(), HttpStatus.OK);
		
	}
}
