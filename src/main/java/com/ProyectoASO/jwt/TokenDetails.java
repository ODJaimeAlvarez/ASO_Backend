package com.ProyectoASO.jwt;

import java.util.List;

public class TokenDetails {
	
	private String email;
	private List<String> auth;
	
	public TokenDetails() {
	}
	public TokenDetails(String email, List<String> auth) {
		this.email = email;
		this.auth = auth;
	}
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<String> getAuth() {
		return auth;
	}


	public void setAuth(List<String> list) {
		this.auth = list;
	}
	
	
	
	

}
