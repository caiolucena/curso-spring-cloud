package com.in28minutes.rest.webservices.usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UsuarioDaoService {

	private static List<Usuario> usuarios = new ArrayList<>();
	
	private static int usuariosCount = 3;
	
	static {
		usuarios.add(new Usuario(1, "Adam", new Date()));
		usuarios.add(new Usuario(2, "Eve", new Date()));
		usuarios.add(new Usuario(3, "Jack", new Date()));
	}
	
	
	public List<Usuario> findAll(){
		return usuarios;
	}
	
	public Usuario save(Usuario usuario) {
		
		if(usuario.getId()==null) {
			usuario.setId(++usuariosCount);
		}
		usuarios.add(usuario);
		return usuario;
	}
	
	public Usuario findOne(int id) {
		
		for(Usuario usuario:usuarios) {
			if(usuario.getId()==id) {
				return usuario;
			}
		}
		return null;
	}
}
