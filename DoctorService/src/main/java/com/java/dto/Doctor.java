package com.java.dto;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(builderMethodName = "doctor")
@SQLDelete(sql = "update Doctor set active=false where doctorId= ?")
public class Doctor {
	@Id
	@GeneratedValue
	//@JsonIgnore
	int doctorId;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@Email
	private String emailId;
	//@Size(min= 7, max= 11)
	private long phoneNumber;
	@Past
	//@JsonFormat(pattern="yyyy-Mon-dd")//serializer deserializer
	private LocalDate dob;
	private String location;
	@Builder.Default
	@ManyToMany(fetch= FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="doctor_speciality", joinColumns= @JoinColumn(name="doctor_id"),
	inverseJoinColumns=@JoinColumn(name="speciality_id"))
	private Set<Speciality> specialities= new HashSet<>();
	
	@Builder.Default
	private boolean active = true;

	@Builder.Default
	@Embedded
	@ElementCollection
	private Set<Schedule> schedule= new HashSet<>();
}

//medical history