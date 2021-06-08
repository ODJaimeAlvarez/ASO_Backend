package com.ProyectoASO.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoASO.dto.ProyectoDTO;
import com.ProyectoASO.dto.ProyectoNuevoDTO;
import com.ProyectoASO.service.IProyectoService;

@RestController
@RequestMapping("/api/proyecto")
@CrossOrigin("*")
public class ProyectoController {
	
	private IProyectoService proyectoService;

	public ProyectoController(IProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}
	@GetMapping
	public ResponseEntity<List<ProyectoDTO>> getAll(){
		return new ResponseEntity<>(proyectoService.getAllProyectos(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProyectoDTO> getById(@PathVariable Integer id){
		return new ResponseEntity<>(proyectoService.getById(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProyectoDTO> save(@RequestBody ProyectoNuevoDTO proyect){
		return new ResponseEntity<>(proyectoService.save(proyect),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProyectoDTO> update(@PathVariable Integer id,@RequestBody ProyectoDTO proyect){
		return new ResponseEntity<>(proyectoService.update(id,proyect),HttpStatus.OK);
	}
}
