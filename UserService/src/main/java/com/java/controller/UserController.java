package com.java.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.java.dao.UserRepository;
import com.java.dto.Address;

import com.java.dto.User;

@RepositoryRestController
@RequestMapping(path="users")
public class UserController {
	
	
	@Autowired RestTemplate template;
	@Autowired UserRepository userRep;
	
	
	@GetMapping(path = "{id}")
	public ResponseEntity<?> findPatient(@PathVariable("id") int id) {
		User user = userRep.findByUserId(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		

		return ResponseEntity.ok(user);
	}
	
//	@PutMapping(path="/cart")
//	public ResponseEntity<String> 
	
//	@GetMapping(path="/{userId}/cart")
//	public ResponseEntity<Cart> getCart(@PathVariable("userId")int userId){
//		Cart cart=userRep.getCart(userId);
//		
//		return ResponseEntity.status(HttpStatus.OK).body(cart);
//	}
//	
//	@HystrixCommand(fallbackMethod="fallbackForAddUser",
//			commandProperties= {
//					@HystrixProperty(name= "circuitBreaker.errorThresholdPercentage", value="10"),
//					@HystrixProperty(name= "circuitBreaker.sleepWindowInMilliseconds", value="50000"),
//					@HystrixProperty(name= "execution.isolation.thread.timeoutInMilliseconds", value="1")
//					
//			})
	@SuppressWarnings("unchecked")
	@PostMapping(path="/register", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> addUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
		HttpHeaders header = new HttpHeaders();
		List<String> values= Arrays.asList("application/json","application/xml");
		header.addAll("content-type", values);
		
		
		JSONObject obj = new JSONObject();
		obj.put("firstName",user.getFirstName());
		obj.put("lastName",user.getLastName());
		obj.put("emailId",user.getEmail());
		HttpEntity <String> httpEntity = new HttpEntity <String> (obj.toString(), header);
		int resp=-1;
		if("PATIENT".equals(user.getRole().name())){
			 resp= template.postForObject("http://patient-service/patients",httpEntity,Integer.class);
		}else if("DOCTOR".equals(user.getRole().name())){
			
			 resp= template.postForObject("http://doctor-service/doctors",httpEntity,Integer.class);
			
		}
		if(resp!=-1) {
		user.setUserId(resp);
		}
		User newUser=userRep.save(user);
		if(newUser!=null) {

			Link selfLink = ControllerLinkBuilder
					.linkTo(UserController.class).slash(user.getUserId()).withSelfRel();
		
			newUser.add(selfLink);
			return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	
	@PutMapping(path = "{id}",consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> updateUser(@RequestBody User user,@PathVariable("id") int id){
		HttpHeaders header = new HttpHeaders();
		List<String> values= Arrays.asList("application/json","application/xml");
		System.out.println("in put user controller");
		header.addAll("content-type", values);
	//	User u=userRep.findByUserId(id);
		userRep.updateUserById(id,user.getFirstName(),user.getLastName());
		
		return new ResponseEntity<String>("User updated", HttpStatus.OK);
		
	}
	
	
	@ResponseStatus(code=HttpStatus.SERVICE_UNAVAILABLE, reason="Currently unable to retrieve data!")
	public ResponseEntity<?> fallbackForAddUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
		System.out.println("Fallback");
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Currently unable to retrieve data!");
		
	}
	
}
