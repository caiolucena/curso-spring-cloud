package com.in28minutes.rest.webservices.usuario;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data	
public class Post {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;

	
	
	
	 
}
