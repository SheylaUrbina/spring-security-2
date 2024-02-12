package com.registro.usuarios.controlador;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.registro.usuarios.dto.UsuarioDTO;
import com.registro.usuarios.modelo.Usuario2;
import com.registro.usuarios.servicio.UsuarioServicio2;


import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/crear")
public class RegistroUsuarioRest 
{
	
	@Autowired
	private UsuarioServicio2 usuarioservicio;
	
	

	@PostMapping("/usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public String crear(@RequestBody UsuarioDTO usuario) {
		return usuarioservicio.guardarRest(usuario); 
				
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String>  BuscarUsuario(@RequestBody UsuarioDTO usuario)  {
		  
	        return usuarioservicio.verUsuario(usuario);
		
	}
}
