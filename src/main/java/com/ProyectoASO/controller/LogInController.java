package com.ProyectoASO.controller;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoASO.configuration.JwtUtility;
import com.ProyectoASO.dao.IUsuarioDao;
import com.ProyectoASO.dto.LoginResponse;
import com.ProyectoASO.dto.UserLoginDTO;
import com.ProyectoASO.service.IUserService;
import com.ProyectoASO.service.UserService;
@RestController
public class LogInController {
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtUtility jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> logIn(@RequestBody UserLoginDTO userLogin) throws Exception{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
		}catch (BadCredentialsException e) {
			throw new Exception("Email o contraseña incorrecta.");
		}
		final UserDetails userDetails= userService.loadUserByUsername(userLogin.getEmail());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		return new ResponseEntity<>(new LoginResponse(jwt), HttpStatus.OK);
	}

}
