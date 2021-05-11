package com.ProyectoASO.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ProyectoASO.exceptions.AuthoritiesException;
import com.ProyectoASO.jwt.TokenDetails;

public abstract class BaseService {
	private TokenDetails token;
	
	public BaseService(TokenDetails token) {
		this.token = token;
	}



	public void checkAuthority(List<String> authorities) {
	
		List<String> authsToken= token.getAuth();
		if(authsToken.isEmpty()||authsToken.stream().noneMatch(x-> authorities.contains(x)))
			throw new AuthoritiesException("No tiene los permisos necesarios para utilizar este método.", HttpStatus.FORBIDDEN);
		
	}
	

}
