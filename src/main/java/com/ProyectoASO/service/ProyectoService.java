package com.ProyectoASO.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ProyectoASO.converter.ProyectoConverter;
import com.ProyectoASO.dao.IProyectoDao;
import com.ProyectoASO.dto.ProyectoDTO;
@Service
public class ProyectoService implements IProyectoService {
	private IProyectoDao proyectoDao;
	private ProyectoConverter converter;
	
	public ProyectoService(IProyectoDao proyectoDao, ProyectoConverter converter) {
		super();
		this.proyectoDao = proyectoDao;
		this.converter = converter;
	}


	@Override
	public List<ProyectoDTO> getAllProyectos() {
		return converter.convert(proyectoDao.findAll());
	}


	public IProyectoDao getProyectoDao() {
		return proyectoDao;
	}


	public void setProyectoDao(IProyectoDao proyectoDao) {
		this.proyectoDao = proyectoDao;
	}

}
