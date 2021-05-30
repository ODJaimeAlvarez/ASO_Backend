package com.ProyectoASO.service;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ProyectoASO.dto.ClienteDTO;
import com.ProyectoASO.dto.ClienteNuevoDTO;
import com.ProyectoASO.exceptions.FileSystemException;
import com.ProyectoASO.responses.MethodResponse;

public interface IClienteService {
	public List<ClienteDTO> getAll();

	public ClienteDTO getById(Integer id);

	public ClienteDTO save(ClienteNuevoDTO emp);

	public ClienteDTO update(Integer id, ClienteDTO emp);

	public ResponseEntity<MethodResponse> deActivate(Integer id);
	
	public ResponseEntity<MethodResponse> activate(Integer id);
	
	public ClienteDTO getPerfil();
	
	public ResponseEntity<MethodResponse> changePhoto(Integer id, MultipartFile photo) throws FileSystemException;
}
