package com.registro.usuarios.servicio;



import com.registro.usuarios.UsuarioDTO;
import com.registro.usuarios.modelo.Usuario2;


public interface UsuarioServicio2 {
	
	public String guardarRest(UsuarioDTO usuario);
	public Usuario2 verUsuario(String nombre);
	public Usuario2 verUsuarioxPass(String password);

}
