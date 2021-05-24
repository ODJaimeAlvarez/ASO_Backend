package com.ProyectoASO.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ProyectoASO.converter.ProyectoConverter;
import com.ProyectoASO.dao.IProyectoDao;
import com.ProyectoASO.dao.IProyectoUsuario;
import com.ProyectoASO.dto.ProyectoDTO;
import com.ProyectoASO.entity.Proyecto;
import com.ProyectoASO.entity.ProyectoUsuario;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.exceptions.DBException;
import com.ProyectoASO.jwt.TokenDetails;
@Service
public class ProyectoService extends BaseService implements IProyectoService {
	private IProyectoDao proyectoDao;
	private ProyectoConverter converter;
	private IProyectoUsuario proyectoUsuarioDao;
	private UserService usuarioService;
	
	

	public ProyectoService(TokenDetails token, IProyectoDao proyectoDao, ProyectoConverter converter,
			IProyectoUsuario proyectoUsuarioDao, UserService usuarioService) {
		super(token);
		this.proyectoDao = proyectoDao;
		this.converter = converter;
		this.proyectoUsuarioDao = proyectoUsuarioDao;
		this.usuarioService = usuarioService;
	}

	@Override
	public List<ProyectoDTO> getAllProyectos() {
		Usuario user = usuarioService.buscarPorcorreo(getToken().getEmail());
		List<ProyectoUsuario> listaProyectos=proyectoUsuarioDao.findByUsuario(user);
		List<Proyecto> proyectos=new ArrayList<>();
		for(ProyectoUsuario p: listaProyectos) {
			proyectos.add(p.getProyecto());
		}
		return converter.convert(proyectos);
	}
	
	@Override
	public ProyectoDTO getById(Integer id) {
		Usuario user = usuarioService.buscarPorcorreo(getToken().getEmail());
		List<ProyectoUsuario> listaProyectos=proyectoUsuarioDao.findByUsuario(user);
		for(ProyectoUsuario p: listaProyectos) {
			if(p.getProyecto().getId()==id) {
				return converter.convert(proyectoDao.findById(id).orElseThrow(()->new DBException("El proyecto con id "+ id +" no se encuentra.", HttpStatus.NOT_FOUND)));
			}
		}
		throw new DBException("No tiene acceso a este proyecto",  HttpStatus.FORBIDDEN);
		
	}


	public IProyectoDao getProyectoDao() {
		return proyectoDao;
	}


	public void setProyectoDao(IProyectoDao proyectoDao) {
		this.proyectoDao = proyectoDao;
	}


	

}
