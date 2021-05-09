package com.ProyectoASO.dto;

import java.util.List;


public class LoginResponse {
	private final String jwToken;
	private final String email;
	private final List<String> authorities;
	
	public LoginResponse(String jwToken, String email, List<String> authorities) {
		this.jwToken = jwToken;
		this.email = email;
		this.authorities = authorities;
	}
	public String getJwToken() {
		return jwToken;
	}
	public String getEmail() {
		return email;
	}
	public List<String> getAuthorities() {
		return authorities;
	}
	
	
	

	

}
