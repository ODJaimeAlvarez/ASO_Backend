package com.ProyectoASO.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ProyectoASO.converter.ProyectoConverter;
import com.ProyectoASO.dao.IProyectoDao;
import com.ProyectoASO.dto.ProyectoDTO;
import com.ProyectoASO.exceptions.DBException;
import com.ProyectoASO.jwt.TokenDetails;
@Service
public class ProyectoService extends BaseService implements IProyectoService {
	private IProyectoDao proyectoDao;
	private ProyectoConverter converter;
	
	public ProyectoService(TokenDetails token, IProyectoDao proyectoDao, ProyectoConverter converter) {
		super(token);
		this.proyectoDao = proyectoDao;
		this.converter = converter;
	}

	@Override
	public List<ProyectoDTO> getAllProyectos() {
		return converter.convert(proyectoDao.findAll());
	}
	
	@Override
	public ProyectoDTO getById(Integer id) {
		return converter.convert(proyectoDao.findById(id).orElseThrow(()->new DBException("El proyecto con id "+ id +" no se encuentra.", HttpStatus.NOT_FOUND)));
	}


	public IProyectoDao getProyectoDao() {
		return proyectoDao;
	}


	public void setProyectoDao(IProyectoDao proyectoDao) {
		this.proyectoDao = proyectoDao;
	}


	

}
