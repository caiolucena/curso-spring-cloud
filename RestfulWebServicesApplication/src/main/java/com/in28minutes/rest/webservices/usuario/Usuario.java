package com.in28minutes.rest.webservices.usuario;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="Todos os detalhes do usuário")
@Entity
@Table(name="user")
public class Usuario {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Size(min=2,max=40)
	@ApiModelProperty(notes ="Name deve ter entre 2 e 40 caracteres")
	private String name;
	
	@Past
	@ApiModelProperty(notes="BirthDate deve estar no passado")
	private Date birthDate;

	@OneToMany(mappedBy="usuario")
	private List<Post> posts;
	
	public Usuario(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Usuario() {
		super();
	}

	
}
