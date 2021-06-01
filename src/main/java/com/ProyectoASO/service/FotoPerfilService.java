package com.ProyectoASO.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoASO.dao.FotoUsuariosDao;
import com.ProyectoASO.entity.FotoUsuarios;
import com.ProyectoASO.exceptions.AuthoritiesException;
import com.ProyectoASO.exceptions.DBException;
import com.ProyectoASO.exceptions.FileSystemException;
import com.ProyectoASO.jwt.TokenDetails;

@Service
public class FotoPerfilService extends BaseService implements IFotoPerfilService {
	FileStorageService fileStoreService;
	FotoUsuariosDao fotoUsuarioDao;

	@Value("${files.route}")
	private String ruta;

	public FotoPerfilService(TokenDetails token, FileStorageService fileStoreService, FotoUsuariosDao fotoUsuarioDao) {
		super(token);
		this.fileStoreService = fileStoreService;
		this.fotoUsuarioDao = fotoUsuarioDao;
	}

	@Override
	public FotoUsuarios save(MultipartFile file) throws FileSystemException {
		if (file.getOriginalFilename().contains(".jpg") || file.getOriginalFilename().contains(".png")) {
			FotoUsuarios foto = new FotoUsuarios();
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yy-MM-dd_HH_mm_ss");
			foto.setNombreFoto(df.format(LocalDateTime.now()) + file.getOriginalFilename());
			foto.setUri(ruta + "\\fotoUsers");
			if (!Files.exists(Paths.get(ruta))) {
				fileStoreService.init();
			}
			if (!Files.exists(Paths.get(foto.getUri()))) {
				fileStoreService.init(foto.getUri());
			}
			fileStoreService.saveFile(file, foto.getUri(), foto.getNombreFoto());
			fotoUsuarioDao.save(foto);
			return fotoUsuarioDao.save(foto);
		} else {
			System.out.println("Hola" + !file.getOriginalFilename().contains(".png")
					+ !file.getOriginalFilename().contains(".jpg"));
			throw new AuthoritiesException("No se permite almacenar imagenes que no sean formato .png o .jpg",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public FotoUsuarios updateFile(Integer idFile, MultipartFile file) throws FileSystemException {
		if (file.getOriginalFilename().contains(".jpg") || file.getOriginalFilename().contains(".png")) {
			FotoUsuarios foto = fotoUsuarioDao.findById(idFile).orElseThrow(
					() -> new DBException("La foto de usuario no existe en Base de datos", HttpStatus.NOT_FOUND));
			fileStoreService.delete(file.getOriginalFilename(), foto.getUri());
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yy-MM-dd_HH_mm_ss");
			foto.setNombreFoto(df.format(LocalDateTime.now()) + file.getOriginalFilename());
			foto.setUri(ruta + "\\fotoUsers");
			if (!Files.exists(Paths.get(ruta))) {
				fileStoreService.init();
			}
			if (!Files.exists(Paths.get(foto.getUri()))) {
				fileStoreService.init(foto.getUri());
			}
			fileStoreService.saveFile(file, foto.getUri(), foto.getNombreFoto());
			fotoUsuarioDao.save(foto);
			return fotoUsuarioDao.save(foto);
		} else {
			System.out.println("Hola" + file.getOriginalFilename());
			throw new AuthoritiesException("No se permite almacenar imagenes que no sean formato .png o .jpg",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Resource> downloadPhoto(Integer idFile) throws FileSystemException {
		HttpHeaders headerFile = new HttpHeaders();
		FotoUsuarios foto = fotoUsuarioDao.findById(idFile)
				.orElseThrow(() -> new DBException("No se encuentra el archivo solicitado.", HttpStatus.NOT_FOUND));
		headerFile.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + foto.getNombreFoto() + "\"");
		return new ResponseEntity<>(fileStoreService.loadFile(foto.getNombreFoto(), foto.getUri()), headerFile,
				HttpStatus.OK);
	}

}
