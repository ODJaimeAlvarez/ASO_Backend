package com.ProyectoASO.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoASO.dto.NotasDTO;
import com.ProyectoASO.service.INotasService;

@RestController
@RequestMapping("/api/notas")
@CrossOrigin("*")
public class NotasController {
	
	private INotasService notasService;

	public NotasController(INotasService notasService) {
		this.notasService = notasService;
	}
	
	@GetMapping
	public ResponseEntity<List<NotasDTO>> getAll(){
		return new ResponseEntity<>(notasService.getAll(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<NotasDTO> save(@RequestBody NotasDTO note){
		return new ResponseEntity<>(notasService.save(note),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id){
		notasService.delete(id);
	}

}
