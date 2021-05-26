package com.ProyectoASO.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoASO.dto.ClienteDTO;
import com.ProyectoASO.dto.EmpleadoDTO;
import com.ProyectoASO.responses.MethodResponse;
import com.ProyectoASO.service.IClienteService;


@RestController
@RequestMapping("/api/cliente")
@CrossOrigin("*")
public class ClienteController {
	
private IClienteService clienteService;
	
	
	public ClienteController(IClienteService clienteService) {
	this.clienteService = clienteService;
}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getAll(){
		return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO>getById(@PathVariable Integer id){
		return new ResponseEntity<>(clienteService.getById(id), HttpStatus.OK);
	}
	@GetMapping("/perfil")
	public ResponseEntity<ClienteDTO>getPerfil(){
		return new ResponseEntity<>(clienteService.getPerfil(), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@RequestBody ClienteDTO cliente){
		return new ResponseEntity<>(clienteService.update(id, cliente),HttpStatus.OK);
	}
	
	@PutMapping("/alta")
	public ResponseEntity<MethodResponse> activate(@RequestBody Integer id){
		return clienteService.activate(id);
	}

	@PutMapping("/baja")
	public ResponseEntity<MethodResponse>deActivate(@RequestBody Integer id){
		return clienteService.deActivate(id);
	}
}
