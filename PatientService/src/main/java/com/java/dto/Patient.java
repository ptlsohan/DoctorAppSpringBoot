package com.java.dto;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.SQLDelete;
import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(builderMethodName = "patient")
@SQLDelete(sql = "update Patient set active=false where patientId= ?")
public class Patient extends ResourceSupport{
	@Id
	@GeneratedValue
	int patientId;
	private String firstName;
	private String lastName;
	private String emailId;
	private long phoneNumber;
	private LocalDate dob;
	@Builder.Default
	private boolean active = true;
	//multipart file upload
	
	@Builder.Default
	@ElementCollection
	private List<File> medicalRecords= new ArrayList<>();

}
