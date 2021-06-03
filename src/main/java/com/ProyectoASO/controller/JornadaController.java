package com.ProyectoASO.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoASO.dto.JornadaDTO;
import com.ProyectoASO.responses.JornadaManagerResponse;
import com.ProyectoASO.service.IJornadaService;

@RestController
@RequestMapping("/api/jornada")
@CrossOrigin("*")
public class JornadaController {
	private IJornadaService jornadaService;

	public JornadaController(IJornadaService jornadaService) {
		this.jornadaService = jornadaService;
	}
	
	@GetMapping("/isIniciada")
	public ResponseEntity<JornadaDTO> getJornadaIniciada(@RequestHeader(name = "Authorization") String jwt){
		return new ResponseEntity<>(jornadaService.getInicioJornada(jwt),HttpStatus.OK);
	}
	
	@PostMapping("/jornadaManager")
	public ResponseEntity<JornadaManagerResponse> jornadaManager(@RequestHeader(name = "Authorization") String jwt){
		return jornadaService.jornadaManager(jwt);
	}
	@GetMapping("/{id}")
	public ResponseEntity<List<JornadaDTO>> getJornadaByUser(@PathVariable Integer id){
		return new ResponseEntity<>(jornadaService.getAllByEmp(id),HttpStatus.OK);
	}

}
