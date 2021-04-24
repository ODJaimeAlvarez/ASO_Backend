package com.ProyectoASO.dto;

public class LoginResponse {
	private final String jwToken;

	public LoginResponse(String jwToken) {
		this.jwToken=jwToken;
	}

	public String getJwToken() {
		return jwToken;
	}
	

}
