package com.ProyectoASO.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ProyectoASO.dao.IFicheroDao;
import com.ProyectoASO.dao.IProyectoDao;
import com.ProyectoASO.dao.IRolDao;
import com.ProyectoASO.dao.IRolUsuarioDao;
import com.ProyectoASO.dao.IUsuarioDao;
import com.ProyectoASO.entity.Fichero;
import com.ProyectoASO.entity.Proyecto;
import com.ProyectoASO.entity.Rol;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.entity.RolUsuario;
import com.ProyectoASO.enums.Progreso;
import com.ProyectoASO.exceptions.FileSystemException;
import com.ProyectoASO.service.IFileStorageService;

/*
 * Es una clase que se ejecuta al ejecutarse el programa de Spring, una de sus utilidades es introducir valores 
 * por defecto en la base de datos.
 */
@Configuration
public class Runner {

	@Autowired
	IUsuarioDao user;
	
	@Autowired
	IRolUsuarioDao rolUser;
	
	@Autowired
	IProyectoDao proyecto;

	@Autowired
	IRolDao rol;
	
	@Autowired
	IFicheroDao ficheroDao;
	
	@Autowired
	IFileStorageService fsS;
	
	@Autowired
	BCryptPasswordEncoder encoder;

	@Profile("Testing")
	@Bean
	public ApplicationRunner meterUser() {

		return arg -> addUsuarioRunner();
	}

	/**
	 * Método que crea una lista con Usuarios para su posterior inserción en la base
	 * de datos
	 * @throws FileSystemException 
	 */
	private void addUsuarioRunner() {

		List<Usuario> list_user = new ArrayList<>();
		List<Rol> list_rol = new ArrayList<>();
		List<Fichero> listFicheros= new ArrayList<>();
		
		list_user.add(new Usuario("Martos", "Lopez", "adri@hotmail.com", encoder.encode("uwuwu"), true, "Adrian"));
		list_user.add(new Usuario("De Jaime", "Alvarez", "oscar@hotmail.com", encoder.encode("uwito"), true, "Oscar"));
		list_user.add(new Usuario("Pelayo", "Mellas", "salma@hotmail.com", encoder.encode("awitademadrid"), true, "Salma"));
		list_user.add(new Usuario("Garcia", "Muro", "jose@hotmail.com", encoder.encode("shreckriko"), true, "Jose"));
		list_user.add(new Usuario("Perez", "Gonzales", "david@hotmail.com", encoder.encode("violinistacion"), true, "David"));
		list_user.add(new Usuario("Perez", "Jobs", "sergio@hotmail.com", encoder.encode("haterapple"), true, "Sergio"));
		list_user.add(new Usuario("Volvo", "Gonzales", "marta@hotmail.com", encoder.encode("comopega"), true, "Marta"));		
		user.saveAll(list_user);
		
		
		list_rol.add(new Rol("DIRECTOR"));
		list_rol.add(new Rol("CLIENTE"));
		list_rol.add(new Rol("EMPLEADO"));
		
		
		rol.saveAll(list_rol);
		
		List<RolUsuario> listUserRole= new ArrayList<>();
		
		for(Usuario u : list_user) {
			listUserRole.add(new RolUsuario( list_rol.get(2),u));
		}
		
		rolUser.saveAll(listUserRole);
		
		List<Proyecto> list_proyecto= new ArrayList<>();
		list_proyecto.add(new Proyecto("primer proyecto",Progreso.ACEPTADO,"este es el primer proyecto"));
		list_proyecto.add(new Proyecto("segundo proyecto",Progreso.EN_CURSO,"este es el segundo proyecto"));
		list_proyecto.add(new Proyecto("tercer proyecto",Progreso.FINALIZADO,"este es el tercer proyecto"));
		list_proyecto.add(new Proyecto("cuarto proyecto",Progreso.INICIADO,"este es el cuarto proyecto"));
		
		
		proyecto.saveAll(list_proyecto);
		
		listFicheros.add(new Fichero("C:\\proyectoASOFiles\\primer_proyecto", "Datos_Importantes.txt", Date.from(Instant.now()), list_proyecto.get(0)));
		listFicheros.add(new Fichero("C:\\proyectoASOFiles\\primer_proyecto", "cuentas.txt", Date.from(Instant.now()), list_proyecto.get(0)));
		listFicheros.add(new Fichero("C:\\proyectoASOFiles\\tercer_proyecto", "Datos_Importantes.txt", Date.from(Instant.now()), list_proyecto.get(2)));
		listFicheros.add(new Fichero("C:\\proyectoASOFiles\\cuarto_proyecto", "Datos_Importantes.txt", Date.from(Instant.now()), list_proyecto.get(3)));
		ficheroDao.saveAll(listFicheros);
		
		try {
			fsS.init();
			fsS.init("primer_proyecto");
			fsS.init("tercer_proyecto");
			fsS.init("cuarto_proyecto");
		} catch (FileSystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String hola= "hola";
		try {
			Files.write(Paths.get("C:\\proyectoASOFiles\\primer_proyecto\\Datos_Importantes.txt"), hola.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.WRITE);
			Files.write(Paths.get("C:\\proyectoASOFiles\\primer_proyecto\\cuentas.txt"), hola.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.WRITE);
			Files.write(Paths.get("C:\\proyectoASOFiles\\tercer_proyecto\\Datos_Importantes.txt"), hola.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.WRITE);
			Files.write(Paths.get("C:\\proyectoASOFiles\\cuarto_proyecto\\Datos_Importantes.txt"), hola.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.WRITE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
