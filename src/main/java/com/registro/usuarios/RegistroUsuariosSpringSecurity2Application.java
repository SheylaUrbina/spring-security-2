package com.registro.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RegistroUsuariosSpringSecurity2Application {

	public static void main(String[] args) {
		SpringApplication.run(RegistroUsuariosSpringSecurity2Application.class, args);
	}
	
	@Bean
	public org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder BCryptPasswordEncoder() {
		return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
	}

}
