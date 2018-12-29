package com.java.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ADDRESS_TABLE")
@DynamicUpdate
@Data
@NoArgsConstructor
@Builder(builderMethodName = "address")
@Entity
@AllArgsConstructor
public class Address extends ResourceSupport implements Serializable{
	
	
	private static final long serialVersionUID = -8924177362606395510L;

	@Id
	@GeneratedValue
	private int addressId;
	
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	//@Length(min = 5, max = 5)
	private int zipcode;
}
