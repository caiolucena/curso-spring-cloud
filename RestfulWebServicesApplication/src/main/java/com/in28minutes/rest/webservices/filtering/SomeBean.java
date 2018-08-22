package com.in28minutes.rest.webservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Data;

@Data
//@JsonIgnoreProperties(value= {"field1","field2"}) //- static filtering
@JsonFilter("SomeBeanFilter")
public class SomeBean {

	private String field1;
	
	private String field2;
	
	//@JsonIgnore - static filtering
	private String field3;
	
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}
	
	
}
