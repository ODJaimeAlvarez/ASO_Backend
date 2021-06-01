package com.ProyectoASO.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoASO.dto.EmpleadoDTO;
import com.ProyectoASO.dto.EmpleadoNuevoDTO;
import com.ProyectoASO.exceptions.FileSystemException;
import com.ProyectoASO.responses.MethodResponse;

public interface IEmpleadoService {
	public List<EmpleadoDTO> getAll();

	public EmpleadoDTO getById(Integer id);

	public EmpleadoDTO save(EmpleadoNuevoDTO emp);

	public EmpleadoDTO update(Integer id, EmpleadoDTO emp);

	public ResponseEntity<MethodResponse> deActivate(Integer id);
	
	public ResponseEntity<MethodResponse> activate(Integer id);
	
	public EmpleadoDTO getPerfil();

	public ResponseEntity<MethodResponse> changePhoto(Integer id, MultipartFile photo) throws FileSystemException;
}
