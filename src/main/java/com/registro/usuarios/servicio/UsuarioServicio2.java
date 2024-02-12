package com.registro.usuarios.servicio;



import org.springframework.http.ResponseEntity;

import com.registro.usuarios.dto.UsuarioDTO;
import com.registro.usuarios.modelo.Usuario2;


public interface UsuarioServicio2 {
	
	public String guardarRest(UsuarioDTO usuario);
	public ResponseEntity<String> verUsuario(UsuarioDTO usuario);
	

}
