package com.registro.usuarios.servicio;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.registro.usuarios.UsuarioDTO;
import com.registro.usuarios.modelo.Rol2;
import com.registro.usuarios.modelo.Usuario2;
import com.registro.usuarios.repositorio.UsuarioRepo;


@Service
public class UsuarioServicioImpl2 implements UsuarioServicio2,UserDetailsService{

	@Autowired
	private UsuarioRepo repo;
	
	@Autowired
	//private BCryptPasswordEncoder passwordEncoder;
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public String guardarRest(UsuarioDTO usuario) {
		Usuario2 usuarioSave = new Usuario2(usuario.getNombre(),
				usuario.getApellido(),
				usuario.getEmail(),
				passwordEncoder.encode(usuario.getPassword()), //guardar clave codificada en BD
				Arrays.asList(new Rol2("ROLE_USER")));
		repo.save(usuarioSave);
		return "guardado";
	}

	
	@Override
	public Usuario2 verUsuario(String nombre) {
		Usuario2 us = repo.findBynombre(nombre);
		return us;
	}
	

	@Override
	public Usuario2 verUsuarioxPass(String password) {
		
		return repo.findBypassword(password);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario2 usuario = repo.findBynombre(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol2> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}



	
}
