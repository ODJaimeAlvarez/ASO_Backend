package com.ProyectoASO.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoASO.entity.FotoUsuarios;
import com.ProyectoASO.exceptions.FileSystemException;

public interface IFotoPerfilService {
	
	public FotoUsuarios save(MultipartFile file) throws FileSystemException;
	
	public FotoUsuarios updateFile(Integer idFile, MultipartFile file) throws FileSystemException;	
	
	public ResponseEntity<Resource> downloadPhoto(Integer idFile) throws FileSystemException;
}
