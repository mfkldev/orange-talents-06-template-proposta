package br.com.zupacademy.marciosouza.proposta.controller.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {

	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}
