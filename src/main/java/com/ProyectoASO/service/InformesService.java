package com.ProyectoASO.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ProyectoASO.dao.IProyectoDao;
import com.ProyectoASO.dto.InformeDTO;
import com.ProyectoASO.entity.Proyecto;
import com.ProyectoASO.entity.Rol;
import com.ProyectoASO.enums.Progreso;
@Service
public class InformesService {
	private IProyectoDao proyectoRepository;
	private IUserService userService;

	public InformesService(IProyectoDao proyectoRepository, IUserService userService) {
		this.proyectoRepository = proyectoRepository;
		this.userService=userService;
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

}
