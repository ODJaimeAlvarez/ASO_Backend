package com.ProyectoASO.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoASO.dto.InformeDTO;
import com.ProyectoASO.service.InformesService;

@RestController
@RequestMapping("/api/informe")
@CrossOrigin("*")
public class InformeController {
	private InformesService informesService;

	public InformeController(InformesService informesService) {
		this.informesService = informesService;
	}
	
	@GetMapping("/estado")
	public ResponseEntity<List<InformeDTO>> getProyectsByStatus(){
		return new ResponseEntity<>(informesService.getProyectsByStatus(),HttpStatus.OK);
	}
}
