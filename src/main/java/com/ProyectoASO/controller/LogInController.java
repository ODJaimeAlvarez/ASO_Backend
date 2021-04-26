package com.ProyectoASO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ProyectoASO.dto.LoginResponse;
import com.ProyectoASO.dto.UserLoginDTO;
import com.ProyectoASO.exceptions.DBException;
import com.ProyectoASO.jwt.JwtUtility;
import com.ProyectoASO.service.LogInService;
@RestController
@CrossOrigin(origins = "*")
public class LogInController {
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	LogInService logInService;
	
	@Autowired
	JwtUtility jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> logIn(@RequestBody UserLoginDTO userLogin) throws Exception{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
		}catch (BadCredentialsException e) {
			throw new DBException("Email o contraseña incorrecta.", HttpStatus.UNAUTHORIZED);
		}
		final UserDetails userDetails= logInService.loadUserByUsername(userLogin.getEmail());
		
		final String jwt = "Bearer "+jwtUtil.generateToken(userDetails);
		return new ResponseEntity<>(new LoginResponse(jwt), HttpStatus.OK);
	}
	
	@GetMapping("/hello")
	public ResponseEntity<String> logIn2() throws Exception{
		return new ResponseEntity<>("Hola munod", HttpStatus.OK);
	}

}
