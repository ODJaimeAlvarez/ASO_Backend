package com.ProyectoASO.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoASO.dto.ProyectoDTO;
import com.ProyectoASO.service.IProyectoService;

@RestController
@RequestMapping("/api/proyecto")
@CrossOrigin(origins = "*")
public class ProyectoController {
	
	private IProyectoService proyectoService;

	public ProyectoController(IProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}
	@GetMapping
	public ResponseEntity<List<ProyectoDTO>> getAll(){
		return new ResponseEntity<>(proyectoService.getAllProyectos(),HttpStatus.OK);
	}

}
