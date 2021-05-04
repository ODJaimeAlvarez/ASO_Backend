package com.ProyectoASO.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoASO.converter.FicheroConverter;
import com.ProyectoASO.converter.FicheroDTOConverter;
import com.ProyectoASO.converter.ProyectoDTOConverter;
import com.ProyectoASO.dao.IFicheroDao;
import com.ProyectoASO.dto.FicheroDTO;
import com.ProyectoASO.dto.ProyectoDTO;
import com.ProyectoASO.entity.Fichero;
import com.ProyectoASO.entity.Proyecto;
import com.ProyectoASO.exceptions.DBException;
import com.ProyectoASO.exceptions.FileSystemException;
@Service
public class FicheroService implements IFicheroService{
	
	private IFileStorageService fileStorageService;
	private IProyectoService proyectoService;
	private IFicheroDao ficheroRepository;
	private FicheroConverter converter;
	private FicheroDTOConverter converterDTO;
	private ProyectoDTOConverter convertProyectoDTO;
	@Value("${files.route}")
	String ruta;
	

	public FicheroService(IFileStorageService fileStorageService, IFicheroDao ficheroRepository,IProyectoService proyectoService,
			FicheroConverter converter, FicheroDTOConverter converterDTO, ProyectoDTOConverter convertProyectoDTO) {
		this.fileStorageService = fileStorageService;
		this.ficheroRepository = ficheroRepository;
		this.proyectoService=proyectoService;
		this.converter = converter;
		this.converterDTO = converterDTO;
		this.convertProyectoDTO=convertProyectoDTO;
	}

	@Override
	public List<FicheroDTO> getAll() {
		return converter.convert(ficheroRepository.findAll());
	}

	@Override
	public FicheroDTO getById(Integer id) {
		return converter.convert(ficheroRepository.findById(id)
				.orElseThrow(()->new DBException("El fichero con id "+id+" no se encuentra.", HttpStatus.NOT_FOUND)));
	}
	
	
	@Override
	public FicheroDTO save(Integer idProyecto, MultipartFile file) throws FileSystemException {
		Proyecto proyecto= convertProyectoDTO.convert(proyectoService.getById(idProyecto));
		Fichero fileSave = new Fichero();
		fileSave.setFecha_mod(Date.from(Instant.now()));
		fileSave.setNombre_fichero(file.getOriginalFilename());
		fileSave.setProyecto(proyecto);
		String uriFile=ruta+"\\"+fileSave.getProyecto().getNombre_proyecto().replaceAll(" ", "_");
		fileSave.setUri(uriFile);
		if(!Files.exists(Paths.get(ruta))) {
			fileStorageService.init();
		}
		if(!Files.exists(Paths.get(uriFile))) {
			fileStorageService.init(uriFile);
		}
		fileStorageService.saveFile(file, uriFile);
		return converter.convert(ficheroRepository.save(fileSave));
	}

	@Override
	public FicheroDTO updateFile(Integer idFile, MultipartFile file) throws FileSystemException {
		Fichero fileToUpdate=ficheroRepository.findById(idFile)
				.orElseThrow(()->new DBException("El fichero con id "+idFile+" no se encuentra.", HttpStatus.NOT_FOUND));
		fileStorageService.delete(fileToUpdate.getNombre_fichero(), fileToUpdate.getUri()); 
		
		String uriFile=ruta+"\\"+fileToUpdate.getProyecto().getNombre_proyecto().replaceAll(" ", "_");
		fileToUpdate.setFecha_mod(Date.from(Instant.now()));
		fileToUpdate.setUri(uriFile);
		fileToUpdate.setNombre_fichero(file.getOriginalFilename());
		fileStorageService.saveFile(file, uriFile);
		return converter.convert(ficheroRepository.save(fileToUpdate));
	}

	@Override
	public ResponseEntity<String> delete(Integer id) throws FileSystemException {
		Fichero fileToUpdate=ficheroRepository.findById(id)
				.orElseThrow(()->new DBException("El fichero con id "+id+" no se encuentra.", HttpStatus.NOT_FOUND));
		fileStorageService.delete(fileToUpdate.getNombre_fichero(), fileToUpdate.getUri());
		ficheroRepository.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Resource> downloadFile(Integer id) throws FileSystemException {
		HttpHeaders headerFile= new HttpHeaders();
		Fichero fileName =ficheroRepository.findById(id).orElseThrow(()->new DBException("No se encuentra el archivo solicitado.", HttpStatus.NOT_FOUND));
		headerFile.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileName.getNombre_fichero()+"\"");
		return new ResponseEntity<>(fileStorageService.loadFile(fileName.getNombre_fichero(), fileName.getUri()),headerFile,HttpStatus.OK);
	}

	@Override
	public List<FicheroDTO> getByProyecto(ProyectoDTO proyecto) {
		return converter.convert(ficheroRepository.findByProyecto(convertProyectoDTO.convert(proyecto)));
	}

}
