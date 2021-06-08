package com.ProyectoASO.service;

import java.util.List;

import com.ProyectoASO.dto.ProyectoDTO;
import com.ProyectoASO.dto.ProyectoNuevoDTO;

public interface IProyectoService {
	
	public List<ProyectoDTO> getAllProyectos();
	
	public ProyectoDTO getById(Integer id);
	
	public ProyectoDTO save(ProyectoNuevoDTO proyect);
	
	public ProyectoDTO update(Integer id, ProyectoDTO proyect);
	
	

}
