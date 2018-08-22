package com.in28minutes.rest.webservices.usuario;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public Resource <Usuario> retrieveUser(@PathVariable int id) {
		
		Usuario usuario = service.findOne(id); 
		
		if(usuario == null) {
			throw new UserNotFoundException("id-"+id);
		}
		
		Resource<Usuario> resource = new Resource<Usuario> (usuario);
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		Usuario usuario = service.deleteById(id);
		
		if(usuario == null) {
			throw new UserNotFoundException("id-" + id);
		} 
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Usuario usuario) {
		
		Usuario savedUser = service.save(usuario);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	

	
}
