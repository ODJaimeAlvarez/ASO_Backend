package com.ProyectoASO.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoASO.dto.UserLoginDTO;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@GetMapping
	public ResponseEntity<String> holaLogin(){
		return new ResponseEntity<>("Usuario:",HttpStatus.OK);
	}
}
