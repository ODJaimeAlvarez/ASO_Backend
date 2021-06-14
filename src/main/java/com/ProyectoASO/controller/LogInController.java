package com.ProyectoASO.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoASO.dto.ClienteDTO;
import com.ProyectoASO.dto.ClienteNuevoDTO;
import com.ProyectoASO.dto.LoginResponse;
import com.ProyectoASO.dto.UserLoginDTO;
import com.ProyectoASO.exceptions.DBException;
import com.ProyectoASO.jwt.JwtUtility;
import com.ProyectoASO.service.IClienteService;
import com.ProyectoASO.service.LogInService;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class LogInController {
	private AuthenticationManager authManager;
	private LogInService logInService;
	private JwtUtility jwtUtil;
	
	private IClienteService clienteservice;
	
	public LogInController(AuthenticationManager authManager, LogInService logInService, JwtUtility jwtUtil,IClienteService clienteservice) {
		this.authManager = authManager;
		this.logInService = logInService;
		this.jwtUtil = jwtUtil;
		this.clienteservice=clienteservice;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> logIn(@RequestBody UserLoginDTO userLogin) throws Exception{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
		}catch (BadCredentialsException e) {
			throw new DBException("Email o contraseña incorrecta.", HttpStatus.UNAUTHORIZED);
		}
		final UserDetails userDetails= logInService.loadUserByUsername(userLogin.getEmail());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		

		return new ResponseEntity<>(new LoginResponse("Bearer "+jwt,jwtUtil.getEmailFronToken(jwt),jwtUtil.getAuthFromToken(jwt)), HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<ClienteDTO> register(@RequestBody ClienteNuevoDTO newClient){
		return new ResponseEntity<>(clienteservice.save(newClient),HttpStatus.OK);
	}
}
