package com.ProyectoASO.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ProyectoASO.dao.ILogClienteDiaDao;
import com.ProyectoASO.dao.ILogEmpleadoDiaDao;
import com.ProyectoASO.dao.IProyectoDao;
import com.ProyectoASO.dto.InformeDTO;
import com.ProyectoASO.entity.LogClienteDia;
import com.ProyectoASO.entity.LogEmpleadoDia;
import com.ProyectoASO.entity.Proyecto;
import com.ProyectoASO.entity.Rol;
import com.ProyectoASO.enums.Progreso;
@Service
public class InformesService {
	private IProyectoDao proyectoRepository;
	private IUserService userService;
	private ILogClienteDiaDao logClientesRepository;
	private ILogEmpleadoDiaDao logEmpleadoRepository;


	public InformesService(IProyectoDao proyectoRepository, IUserService userService,
			ILogClienteDiaDao logClientesRepository, ILogEmpleadoDiaDao logEmpleadoRepository) {
		this.proyectoRepository = proyectoRepository;
		this.userService = userService;
		this.logClientesRepository = logClientesRepository;
		this.logEmpleadoRepository = logEmpleadoRepository;
	}

	public List<InformeDTO> getProyectsByStatus() {
		List<Proyecto> proyects = proyectoRepository.findAll();
		List<InformeDTO> proyectsInform = new ArrayList<>();
		for (Progreso pro : Progreso.values()) {
			Integer numberOfProyects = 0;
			for (Proyecto p : proyects) {
				if(p.getProgreso().equals(pro)) 
					numberOfProyects++;					
			}
			proyectsInform.add(new InformeDTO(pro.getEstado().replace("_", " "),numberOfProyects));
		}
		return proyectsInform;
	}
	
	public List<InformeDTO> getNumberOfUserByRol() {
		List<InformeDTO> rolesInform = new ArrayList<>();
		rolesInform.add(new InformeDTO("Directores", userService.getAllUsersByRol(new Rol(1,"DIRECTOR")).size()));
		rolesInform.add(new InformeDTO("Clientes", userService.getAllUsersByRol(new Rol(2,"CLIENTES")).size()));
		rolesInform.add(new InformeDTO("Empleados", userService.getAllUsersByRol(new Rol(3,"EMPLEADOS")).size()));
		return rolesInform;
	}
	
	public List<InformeDTO> getRegisteredClients() {
		List<InformeDTO> clientsRegisteredInform = new ArrayList<>();
		Calendar now = Calendar.getInstance();
		Calendar minus7days = Calendar.getInstance();
		minus7days.add(Calendar.DATE,-6);
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		List<LogClienteDia> logs= logClientesRepository.findByFechaBetween(Date.from( minus7days.toInstant()),Date.from(now.toInstant()));
		for(int i=0;i<7;i++) {
			if(logs.stream().anyMatch(date -> sdf.format(date.getFecha()).equals(sdf.format(Date.from(minus7days.toInstant()))))) {
				LogClienteDia log=logs.stream().filter(date -> sdf.format(date.getFecha()).equals(sdf.format(Date.from(minus7days.toInstant())))).findFirst().get();
				clientsRegisteredInform.add(new InformeDTO(sdf.format(log.getFecha()), log.getClientes()));
			}else
				clientsRegisteredInform.add(new InformeDTO(sdf.format(Date.from(minus7days.toInstant())), 0));
			minus7days.add(Calendar.DATE,1);	
		}
		return clientsRegisteredInform;
	}
	
	public List<InformeDTO> getRegisteredEmployers() {
		List<InformeDTO> empRegisteredInform = new ArrayList<>();
		Calendar now = Calendar.getInstance();
		Calendar minus7days = Calendar.getInstance();
		minus7days.add(Calendar.DATE,-6);
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		List<LogEmpleadoDia> logs= logEmpleadoRepository.findByFechaBetween(Date.from( minus7days.toInstant()),Date.from(now.toInstant()));
		for(int i=0;i<7;i++) {
			if(logs.stream().anyMatch(date -> sdf.format(date.getFecha()).equals(sdf.format(Date.from(minus7days.toInstant()))))) {
				LogEmpleadoDia log=logs.stream().filter(date -> sdf.format(date.getFecha()).equals(sdf.format(Date.from(minus7days.toInstant())))).findFirst().get();
				empRegisteredInform.add(new InformeDTO(sdf.format(log.getFecha()), log.getClientes()));
			}else
				empRegisteredInform.add(new InformeDTO(sdf.format(Date.from(minus7days.toInstant())), 0));
			minus7days.add(Calendar.DATE,1);
		}
		return empRegisteredInform;
	}

}
