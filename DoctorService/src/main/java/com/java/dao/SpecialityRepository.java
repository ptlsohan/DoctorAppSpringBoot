package com.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.dto.Speciality;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {
	
	

}
