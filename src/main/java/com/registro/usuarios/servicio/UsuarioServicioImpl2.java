package com.registro.usuarios.servicio;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.registro.usuarios.dto.UsuarioDTO;
import com.registro.usuarios.modelo.Rol2;
import com.registro.usuarios.modelo.Usuario2;
import com.registro.usuarios.repositorio.UsuarioRepo;


@Service
public class UsuarioServicioImpl2 implements UsuarioServicio2,UserDetailsService{

	@Autowired
	private UsuarioRepo repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
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
	public ResponseEntity<String> verUsuario (UsuarioDTO usuario) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				  usuario.getNombre(), usuario.getPassword()));
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        return new ResponseEntity<>("User login successfully!.", HttpStatus.OK);
	}
	

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Usuario2 usuario = repo.findBynombre(username);
		Usuario2 usuario = repo.findByNombreAndPassword(username,username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getNombre(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol2> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}



	
}
