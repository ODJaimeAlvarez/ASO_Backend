package com.ProyectoASO.controller;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoASO.dto.FicheroDTO;
import com.ProyectoASO.exceptions.FileSystemException;
import com.ProyectoASO.service.IFicheroService;
import com.ProyectoASO.service.IFotoPerfilService;

@RestController
@RequestMapping("/api/files")
@CrossOrigin("*")
public class FicheroController {
	
	private IFicheroService ficheroService;
	private IFotoPerfilService fotoPerfilService;
	
	public FicheroController(IFicheroService ficheroService, IFotoPerfilService fotoPerfilService) {
		this.ficheroService = ficheroService;
		this.fotoPerfilService = fotoPerfilService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<FicheroDTO>> getAll() {
		return new ResponseEntity<>(ficheroService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FicheroDTO> getById(@PathVariable Integer id){
		return new ResponseEntity<>(ficheroService.getById(id),HttpStatus.OK);
	}
	
	@GetMapping("/project/{id}")
	public ResponseEntity<List<FicheroDTO>> getByProyecto(@PathVariable Integer id) throws FileSystemException{
		return new ResponseEntity<>(ficheroService.getByProyecto(id),HttpStatus.OK);
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<FicheroDTO> save(@PathVariable Integer id, @RequestParam("file") MultipartFile saving) throws FileSystemException{
		return new ResponseEntity<>(ficheroService.save(id,saving),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FicheroDTO> update(@PathVariable Integer id, @RequestParam("file") MultipartFile update) throws FileSystemException{
		return new ResponseEntity<>(ficheroService.updateFile(id,update),HttpStatus.OK);
	}
	
	@GetMapping("/file/{id}")
	public ResponseEntity<Resource> download(@PathVariable Integer id) throws FileSystemException{
		return ficheroService.downloadFile(id);
	}
	
	@GetMapping("/fotoUsuario/{id}")
	public ResponseEntity<Resource> downloadPhotoUser(@PathVariable Integer id) throws FileSystemException{
		return fotoPerfilService.downloadPhoto(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) throws FileSystemException{
		return ficheroService.delete(id);
	}
}
