package com.java.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dao.AddressRepository;
import com.java.dao.UserRepository;
import com.java.dto.Address;
import com.java.dto.Cart;


@RepositoryRestController
@RequestMapping(path="users/{id}")
public class AddressController {
	
	
	
	@Autowired AddressRepository addRep;
	@Autowired UserRepository userRep;

	
	@GetMapping(path="/addresses")
	public ResponseEntity<?> getAddress(@PathVariable("id")int userId){
		//addRep.fin
		List<Address>addresses=userRep.findByAddresses(userId);
		if(addresses==null) {
			return new ResponseEntity<String>("Address not found", HttpStatus.NOT_FOUND);
		}
		for(Address a:addresses) {
			Link selfLink = ControllerLinkBuilder
					.linkTo(ControllerLinkBuilder.methodOn(AddressController.class).getAddress(userId)).withSelfRel();
		
			a.add(selfLink);
		}
		return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
	}
	
		
}
