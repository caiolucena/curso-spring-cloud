package com.in28minutes.rest.webservices.usuario;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UsuarioResource {

	@Autowired
	private UsuarioDaoService service;
	
	@GetMapping("/users")
	public List<Usuario> retrieveAllUsers(){
		return service.findAll();
	}
	
	
	@GetMapping("/users/{id}")
	public Usuario retrieveUser(@PathVariable int id) {
		Usuario usuario = service.findOne(id); 
		if(usuario == null) {
			throw new UserNotFoundException("id-"+id);
		}
		return usuario;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody Usuario usuario) {
		
		Usuario savedUser = service.save(usuario);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
}