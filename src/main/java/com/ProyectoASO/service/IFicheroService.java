package com.ProyectoASO.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoASO.dto.FicheroDTO;
import com.ProyectoASO.dto.ProyectoDTO;
import com.ProyectoASO.exceptions.FileSystemException;

public interface IFicheroService {
	
	public List<FicheroDTO> getAll();
	
	public FicheroDTO getById(Integer id);
	
	public FicheroDTO save(Integer idProyecto, MultipartFile file) throws FileSystemException;
	
	public FicheroDTO updateFile(Integer idFile, MultipartFile file) throws FileSystemException;
	
	public ResponseEntity<String> delete(Integer id) throws FileSystemException;
	
	public ResponseEntity<Resource> downloadFile(Integer id) throws FileSystemException;
	
	public List<FicheroDTO> getByProyecto(Integer id) throws FileSystemException;
}
