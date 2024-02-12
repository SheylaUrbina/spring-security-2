package com.registro.usuarios.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.registro.usuarios.modelo.Usuario2;

public interface UsuarioRepo extends CrudRepository<Usuario2,Long>{

	public Usuario2 findBynombre (String nombre);
	public Usuario2 findBypassword (String password);
}
