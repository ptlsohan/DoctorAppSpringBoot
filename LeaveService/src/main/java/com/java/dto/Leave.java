package com.java.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName="appointment")
@Data
@Entity
@SQLDelete(sql="update Leave set status = 'REJECTED' where leaveId = ?")
public class Leave implements Serializable{
	
	
	
	
	private static final long serialVersionUID = 2765846169953513647L;
	@Id
	@GeneratedValue
	//@JsonIgnore
	@Column(name="leaveId")
	private int leaveId;
	@NotBlank
	private int doctorId;
	@NotEmpty
	private String doctorEmailId; 
	@NotEmpty
	private String doctorName;
	@Future
	private LocalDate startDateOfLeave;
	private LocalTime startTimeOfLeave;
	@Future
	private LocalDate endDateOfLeave;
	private LocalTime endTimeOfLeave;
	
	@Builder.Default
	@Enumerated(EnumType.STRING)
	private LeaveStatus status=LeaveStatus.PENDING; 
}

//medical history