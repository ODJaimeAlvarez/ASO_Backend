package com.ProyectoASO.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testAngular")
public class UserController {
	
	@PostMapping("/login")
	public ResponseEntity<String> holaLogin(@RequestBody String Login){
		return new ResponseEntity<>("Hola "+Login,HttpStatus.OK);
	}

}
