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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoASO.dto.ClienteDTO;
import com.ProyectoASO.exceptions.FileSystemException;
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
	public ResponseEntity<List<ClienteDTO>> getAll() {
		return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> getById(@PathVariable Integer id) {
		return new ResponseEntity<>(clienteService.getById(id), HttpStatus.OK);
	}

	@GetMapping("/perfil")
	public ResponseEntity<ClienteDTO> getPerfil() {
		return new ResponseEntity<>(clienteService.getPerfil(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody ClienteDTO cliente) {
		return new ResponseEntity<>(clienteService.update(id, cliente), HttpStatus.OK);
	}

	@PutMapping("/alta/{id}")
	public ResponseEntity<MethodResponse> activate(@PathVariable Integer id) {
		return clienteService.activate(id);
	}

	@PutMapping("/baja/{id}")
	public ResponseEntity<MethodResponse> deActivate(@PathVariable Integer id) {
		return clienteService.deActivate(id);
	}

	@PutMapping("/cambiarFoto/{id}")
	public ResponseEntity<MethodResponse> save(@PathVariable Integer id, @RequestParam("file") MultipartFile saving)
			throws FileSystemException {
		return clienteService.changePhoto(id, saving);
	}
	
}
