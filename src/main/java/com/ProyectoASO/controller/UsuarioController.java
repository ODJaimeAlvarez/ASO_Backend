package com.ProyectoASO.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoASO.dto.UsuarioDTO;
import com.ProyectoASO.service.UserService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	private UserService usuarioService;
		
	public UsuarioController(UserService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> getAll(){
		return new ResponseEntity<>(usuarioService.getAllUsers(), HttpStatus.OK);
	}

}
