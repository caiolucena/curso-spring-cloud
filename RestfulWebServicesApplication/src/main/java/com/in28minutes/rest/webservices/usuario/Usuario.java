package com.in28minutes.rest.webservices.usuario;

import java.util.Date;

import lombok.Data;

@Data
public class Usuario {

	
	private Integer id;
	
	private String name;
	
	private Date birthDate;

	public Usuario(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	
}
