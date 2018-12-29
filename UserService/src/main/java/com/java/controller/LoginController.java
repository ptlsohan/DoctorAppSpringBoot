package com.java.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.dao.UserRepository;
import com.java.dto.Address;
import com.java.dto.User;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired UserRepository userRep;

	@PostMapping
	public ResponseEntity<?> getAddress(@RequestParam("email")String email, @RequestParam("password") String password){
		//addRep.fin
		User user=userRep.findByEmail(email);
		if(user==null) {
			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
		}
		if(email.equals(user.getEmail()) && password.equals(user.getPassword())) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Invalid email/password", HttpStatus.FORBIDDEN);
	}
}
