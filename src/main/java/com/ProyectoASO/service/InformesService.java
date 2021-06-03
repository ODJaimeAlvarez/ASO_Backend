package com.ProyectoASO.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ProyectoASO.dao.IProyectoDao;
import com.ProyectoASO.dto.InformeDTO;
import com.ProyectoASO.entity.Proyecto;
import com.ProyectoASO.enums.Progreso;
@Service
public class InformesService {
	private IProyectoDao proyectoRepository;

	public InformesService(IProyectoDao proyectoRepository) {
		this.proyectoRepository = proyectoRepository;
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

}
