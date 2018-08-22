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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/jpa/users")
public class UsuarioJpaResource {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PostRepository postRepository;
	
	@GetMapping()
	public List<Usuario> retrieveAllUsers() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/{id}")
	public Resource<Usuario> retrieveUser(@PathVariable int id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (!usuario.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		Resource<Usuario> resource = new Resource<Usuario>(usuario.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {

		usuarioRepository.deleteById(id);

	}

	@PostMapping()
	public ResponseEntity<Object> createUser(@Valid @RequestBody Usuario usuario) {

		Usuario savedUser = usuarioRepository.save(usuario);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@GetMapping("/{id}/posts")
	public List<Post> retrieveAllPosts(@PathVariable int id) {

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

		if (!usuarioOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);

		}
		return usuarioOptional.get().getPosts();
	}

	@PostMapping("/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Post post) {
	
		Optional <Usuario> usuarioOptional = usuarioRepository.findById(id);
		
		if (!usuarioOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);

		}
		
	
		Usuario usuario = usuarioOptional.get();

		post.setUsuario(usuario);
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(post.getId())
			.toUri();

		return ResponseEntity.created(location).build();

	}
}