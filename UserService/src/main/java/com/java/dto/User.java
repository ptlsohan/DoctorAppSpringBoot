package com.java.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "patient")
@Entity
@DynamicUpdate
public class User extends ResourceSupport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3887288660544206868L;
	@Id
	private int userId;
//	@Id
//	@Column(length=50)
	@Email
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	@ManyToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<Address> addresses =new ArrayList<>();
	@Embedded
	@ElementCollection
	@Builder.Default
	private List<CardDetails> card=new ArrayList<>();
	@Embedded
	private Cart cart;
}
