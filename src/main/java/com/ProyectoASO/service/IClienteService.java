package com.ProyectoASO.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ProyectoASO.dto.ClienteDTO;
import com.ProyectoASO.dto.ClienteNuevoDTO;
import com.ProyectoASO.dto.EmpleadoNuevoDTO;
import com.ProyectoASO.responses.MethodResponse;

public interface IClienteService {
	public List<ClienteDTO> getAll();

	public ClienteDTO getById(Integer id);

	public ClienteDTO save(ClienteNuevoDTO emp);

	public ClienteDTO update(Integer id, EmpleadoNuevoDTO emp);

	public ResponseEntity<MethodResponse> deActivate(Integer id);
	
	public ResponseEntity<MethodResponse> activate(Integer id);
	
	public ClienteDTO getPerfil();
}
