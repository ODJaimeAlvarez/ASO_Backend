package com.ProyectoASO.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ProyectoASO.dao.EmpleadoDao;
import com.ProyectoASO.dao.IFicheroDao;
import com.ProyectoASO.dao.IJornadaDao;
import com.ProyectoASO.dao.IProyectoDao;
import com.ProyectoASO.dao.IRolDao;
import com.ProyectoASO.dao.IRolUsuarioDao;
import com.ProyectoASO.dao.IUsuarioDao;
import com.ProyectoASO.entity.Empleado;
import com.ProyectoASO.entity.Fichero;
import com.ProyectoASO.entity.Jornada;
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
	private IUsuarioDao user;
	private IRolUsuarioDao rolUser;
	private IProyectoDao proyecto;
	private IRolDao rol;
	private IFicheroDao ficheroDao;
	private IFileStorageService fsS;
	private IJornadaDao jornadaDao;
	//private IClienteDao clienteDao;
	private EmpleadoDao empleadoDao;
	BCryptPasswordEncoder encoder;
	
	public Runner(IUsuarioDao user, IRolUsuarioDao rolUser, IProyectoDao proyecto, IRolDao rol, IFicheroDao ficheroDao,
			IFileStorageService fsS, IJornadaDao jornadaDao, EmpleadoDao empleadoDao, BCryptPasswordEncoder encoder) {
		this.user = user;
		this.rolUser = rolUser;
		this.proyecto = proyecto;
		this.rol = rol;
		this.ficheroDao = ficheroDao;
		this.fsS = fsS;
		this.jornadaDao = jornadaDao;
		this.empleadoDao=empleadoDao;
		this.encoder = encoder;
	}

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

		
		List<Usuario> list_user_normalize = new ArrayList<>();
		List<Rol> list_rol = new ArrayList<>();
		List<Fichero> listFicheros= new ArrayList<>();
		List<Empleado> listEmp= new ArrayList<>();
		
		list_user_normalize.add(new Usuario("director@aso.com", encoder.encode("director"), true));
		list_user_normalize.add(new Usuario("empleado@aso.com", encoder.encode("empleado"), true));
		list_user_normalize.add(new Usuario("cliente@aso.com", encoder.encode("cliente"), true));
		user.saveAll(list_user_normalize);
		
		listEmp.add(empleadoDao.save(new Empleado("Juan Carlos", "Alvarez", "Martinez", "Director",list_user_normalize.get(0))));
		listEmp.add(empleadoDao.save(new Empleado("Andrea", "Fernandez", "del Alba", "Developer",list_user_normalize.get(1))));
		
		
		
		list_rol.add(new Rol("DIRECTOR"));
		list_rol.add(new Rol("CLIENTE"));
		list_rol.add(new Rol("EMPLEADO"));
		rol.saveAll(list_rol);
		
		
		
		rolUser.save(new RolUsuario( list_rol.get(0),list_user_normalize.get(0)));
		rolUser.save(new RolUsuario( list_rol.get(1),list_user_normalize.get(2)));
		rolUser.save(new RolUsuario( list_rol.get(2),list_user_normalize.get(1)));
		
		
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
		List<Jornada> jornada_list= new ArrayList<>();
		Jornada j1=new Jornada(java.sql.Date.valueOf(LocalDate.now()),Time.valueOf("8:00:25"),Time.valueOf("14:30:00"),listEmp.get(1));
		j1.setIniciada(Boolean.FALSE);
		jornada_list.add(j1);
		Jornada j2=new Jornada(java.sql.Date.valueOf(LocalDate.now()),Time.valueOf("8:10:00"),Time.valueOf("14:31:00"),listEmp.get(1));
		j2.setIniciada(Boolean.FALSE);
		jornada_list.add(j2);
		Jornada j3=new Jornada(java.sql.Date.valueOf(LocalDate.now()),Time.valueOf("8:05:30"),Time.valueOf("14:29:00"),listEmp.get(1));
		j3.setIniciada(Boolean.FALSE);
		jornada_list.add(j3);
		Jornada j4=new Jornada(java.sql.Date.valueOf(LocalDate.now()),Time.valueOf("8:02:10"),Time.valueOf("14:18:00"),listEmp.get(1));
		j4.setIniciada(Boolean.FALSE);
		jornada_list.add(j4);
		Jornada j5=new Jornada(java.sql.Date.valueOf(LocalDate.now()),Time.valueOf("8:12:15"),Time.valueOf("14:49:00"),listEmp.get(1));
		j5.setIniciada(Boolean.FALSE);
		jornada_list.add(j5);
		
		
		Jornada j7=new Jornada(java.sql.Date.valueOf(LocalDate.now()),Time.valueOf("8:01:25"),Time.valueOf("15:00:00"),listEmp.get(0));
		j7.setIniciada(Boolean.FALSE);
		jornada_list.add(j7);
		Jornada j8=new Jornada(java.sql.Date.valueOf(LocalDate.now()),Time.valueOf("8:07:02"),Time.valueOf("16:00:00"),listEmp.get(0));
		j8.setIniciada(Boolean.FALSE);
		jornada_list.add(j8);
		Jornada j9=new Jornada(java.sql.Date.valueOf(LocalDate.now()),Time.valueOf("8:02:37"),Time.valueOf("16:17:00"),listEmp.get(0));
		j9.setIniciada(Boolean.FALSE);
		jornada_list.add(j9);
		Jornada j10=new Jornada(java.sql.Date.valueOf(LocalDate.now()),Time.valueOf("8:24:02"),Time.valueOf("12:50:00"),listEmp.get(0));
		j10.setIniciada(Boolean.FALSE);
		jornada_list.add(j10);
		Jornada j11=new Jornada(java.sql.Date.valueOf(LocalDate.now()),Time.valueOf("8:00:00"),Time.valueOf("13:18:00"),listEmp.get(0));
		j11.setIniciada(Boolean.FALSE);
		jornada_list.add(j11);
		
		
		jornadaDao.saveAll(jornada_list);
		
		
		
	}
}
