package com.ProyectoASO.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginDTO implements Serializable {

	private static final long serialVersionUID = -20272502785314137L;
	
	@JsonProperty(value= "email")
	private String email;
	@JsonProperty(value= "password")
	private String password;
	
	
	
	public UserLoginDTO() {		
	}
	
	public UserLoginDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}

}
