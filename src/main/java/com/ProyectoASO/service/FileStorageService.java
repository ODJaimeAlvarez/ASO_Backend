package com.ProyectoASO.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoASO.exceptions.FileSystemException;
@Service
public class FileStorageService implements IFileStorageService{
	@Value("${files.route}")
	private String path;
	

	@Override
	public void init() throws FileSystemException {
		try {
			Files.createDirectory(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileSystemException("Error al crear el directorio raiz, ¿Tiene permisos?", HttpStatus.UNAUTHORIZED);
		}
		
	}
	@Override
	public void init(String dirPath) throws FileSystemException {
		try {
			System.out.println(Paths.get(dirPath));
			Files.createDirectory(Paths.get(dirPath));
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileSystemException("Error al crear el directorio del proyecto, ¿Tiene permisos?", HttpStatus.UNAUTHORIZED);
		}
	}


	@Override
	public Resource loadFile(String name, String dirPath) throws FileSystemException {
		try {
			Path fileName = Paths.get(dirPath).resolve(name);
			Resource file= new UrlResource(fileName.toUri());
			if(file.exists() && file.isReadable()) {
				return file;
			}else {
				throw new FileSystemException("Error al leer el fichero, no existe o no es legible.", HttpStatus.CONFLICT);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(String name, String dirPath) {
		try {
			Files.deleteIfExists(Paths.get(dirPath).resolve(name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveFile(MultipartFile file, String dirPath, String name) throws FileSystemException {
		try {
			Files.copy(file.getInputStream(), Paths.get(dirPath).resolve(name));
		}catch (FileAlreadyExistsException e) {
			e.printStackTrace();
			throw new FileSystemException("Error guardar el archivo, el archivo ya existe.", HttpStatus.CONFLICT);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	

}
