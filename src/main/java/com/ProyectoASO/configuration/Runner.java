package com.ProyectoASO.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ProyectoASO.dao.IProyectoDao;
import com.ProyectoASO.dao.IRolDao;
import com.ProyectoASO.dao.IUsuarioDao;
import com.ProyectoASO.entity.Proyecto;
import com.ProyectoASO.entity.Rol;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.enums.Progreso;

/*
 * Es una clase que se ejecuta al ejecutarse el programa de Spring, una de sus utilidades es introducir valores 
 * por defecto en la base de datos.
 */
@Configuration
public class Runner {

	@Autowired
	IUsuarioDao user;
	
	@Autowired
	IProyectoDao proyecto;

	@Autowired
	IRolDao rol;

	@Profile("Testing")
	@Bean
	public ApplicationRunner meterUser() {

		return arg -> addUsuarioRunner();
	}

	/**
	 * Método que crea una lista con Usuarios para su posterior inserción en la base
	 * de datos
	 */
	private void addUsuarioRunner() {

		List<Usuario> list_user = new ArrayList<>();

		list_user.add(new Usuario("Martos", "Lopez", "adri@hotmail.com", "uwuwu", true, "Adrian"));
		list_user.add(new Usuario("De Jaime", "Alvarez", "oscar@hotmail.com", "uwito", true, "Oscar"));
		list_user.add(new Usuario("Pelayo", "Mellas", "salma@hotmail.com", "awitademadrid", true, "Salma"));
		list_user.add(new Usuario("Garcia", "Muro", "jose@hotmail.com", "shreckriko", true, "Jose"));
		list_user.add(new Usuario("Perez", "Gonzales", "david@hotmail.com", "violinistacion", true, "David"));
		list_user.add(new Usuario("Perez", "Jobs", "sergio@hotmail.com", "haterapple", true, "Sergio"));
		list_user.add(new Usuario("Volvo", "Gonzales", "marta@hotmail.com", "comopega", true, "Marta"));
		List<Rol> list_rol = new ArrayList<>();
		user.saveAll(list_user);
		proyecto.save(new Proyecto("HOOOOLLAAA", Progreso.ACEPTADO, "TESTING"));
		proyecto.save(new Proyecto("HOOOOLLAAA1", Progreso.ACEPTADO, "TESTING2"));
		proyecto.save(new Proyecto("HOOOOLLAAA2", Progreso.ACEPTADO, "TESTING3"));
		
		/*list_rol.add(new Rol("ver_proyecto"));
		list_rol.add(new Rol("agregar_proyecto"));
		list_rol.add(new Rol("borrar_proyecto"));
		list_rol.add(new Rol("ver_empleados"));
		list_rol.add(new Rol("agregar_empleados"));
		list_rol.add(new Rol("borrar_empleados"));
		list_rol.add(new Rol("encargar_proyecto"));
		list_rol.add(new Rol("verificar_proyecto"));
		rol.saveAll(list_rol);*/
		

	}
}
