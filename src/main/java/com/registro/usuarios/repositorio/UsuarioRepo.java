package com.registro.usuarios.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.registro.usuarios.modelo.Usuario2;

public interface UsuarioRepo extends JpaRepository<Usuario2,Long>{

	//public Usuario2 findByNombre (String nombre);
	public Usuario2 findBypassword (String password);
	public Usuario2 findByNombreAndPassword(String nombre,String password);
	
	Optional<Usuario2> findByNombre(String nombre);
	
	 boolean existsByNombre(String username);

	  boolean existsByEmail(String email);
}
