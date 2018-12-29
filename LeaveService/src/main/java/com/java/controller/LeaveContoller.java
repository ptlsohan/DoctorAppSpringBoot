package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dao.LeaveRepository;
import com.java.dto.LeaveStatus;

@BasePathAwareController
@RequestMapping("leave")
public class LeaveContoller {
	
	@Autowired LeaveRepository lRep;

	@PutMapping(path="{id}")
	public ResponseEntity<?> updateStatus(@PathVariable int id,@RequestParam("status")LeaveStatus status){
		
		lRep.updateLeave_Status(status, id);
		
		return ResponseEntity.ok().build();
		
	}
}
