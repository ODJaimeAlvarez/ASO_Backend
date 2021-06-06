package com.ProyectoASO.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ProyectoASO.converter.ProgresoEnumBDConverter;
import com.ProyectoASO.converter.ProyectoConverter;
import com.ProyectoASO.converter.ProyectoDTOConverter;
import com.ProyectoASO.dao.IProyectoDao;
import com.ProyectoASO.dao.IProyectoUsuario;
import com.ProyectoASO.dto.EmpleadoDTO;
import com.ProyectoASO.dto.ProyectoDTO;
import com.ProyectoASO.dto.ProyectoNuevoDTO;
import com.ProyectoASO.entity.Proyecto;
import com.ProyectoASO.entity.ProyectoUsuario;
import com.ProyectoASO.entity.Rol;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.exceptions.DBException;
import com.ProyectoASO.jwt.TokenDetails;
@Service
public class ProyectoService extends BaseService implements IProyectoService {
	private IProyectoDao proyectoDao;
	private ProyectoConverter converter;
	private ProyectoDTOConverter converterDTO;
	private IProyectoUsuario proyectoUsuarioDao;
	private UserService usuarioService;
	private ProgresoEnumBDConverter converterEnumProgreso;
	
	

	public ProyectoService(TokenDetails token, IProyectoDao proyectoDao, ProyectoConverter converter,
			IProyectoUsuario proyectoUsuarioDao, UserService usuarioService, ProgresoEnumBDConverter converterEnumProgreso) {
		super(token);
		this.proyectoDao = proyectoDao;
		this.converter = converter;
		this.proyectoUsuarioDao = proyectoUsuarioDao;
		this.usuarioService = usuarioService;
		this.converterEnumProgreso= converterEnumProgreso;
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

	@Override
	public ProyectoDTO save(ProyectoNuevoDTO proyect) {
		checkAuthority(List.of("DIRECTOR"));
		Proyecto proyectoDTO=proyectoDao.save(converterDTO.convert(new ProyectoDTO(proyect.getNombre(),proyect.getEstado(),proyect.getDescripcion())));
		
		for(EmpleadoDTO emp: proyect.getEmp()) {
			proyectoUsuarioDao.save(new ProyectoUsuario(proyectoDTO, usuarioService.buscarPorcorreo(emp.getCorreo())));
		}
		if(proyect.getCli()!=null)
			proyectoUsuarioDao.save(new ProyectoUsuario(proyectoDTO, usuarioService.buscarPorcorreo(proyect.getCli().getCorreo())));
		for(Usuario dir: usuarioService.getAllUsersByRol(new Rol(1,"DIRECTOR"))) {
			proyectoUsuarioDao.save(new ProyectoUsuario(proyectoDTO, dir));
		}
		
		
		return converter.convert(proyectoDTO);
	}

	@Override
	public ProyectoDTO update(Integer id, ProyectoNuevoDTO proyect) {
		checkAuthority(List.of("DIRECTOR"));
		Proyecto proyectoupdate= proyectoDao.findById(id).orElseThrow(()->new DBException("El proyecto con id "+ id +" no se encuentra.", HttpStatus.NOT_FOUND));
		proyectoupdate.setNombre_proyecto(proyect.getNombre());
		proyectoupdate.setDescripcion(proyect.getDescripcion());
		proyectoupdate.setProgreso(converterEnumProgreso.convertToEntityAttribute(proyect.getEstado().getCode()));
		proyectoupdate=proyectoDao.save(proyectoupdate);
		for(EmpleadoDTO emp: proyect.getEmp()) {
			proyectoUsuarioDao.save(new ProyectoUsuario(proyectoupdate, usuarioService.buscarPorcorreo(emp.getCorreo())));
		}
		if(proyect.getCli()!=null)
			proyectoUsuarioDao.save(new ProyectoUsuario(proyectoupdate, usuarioService.buscarPorcorreo(proyect.getCli().getCorreo())));
		
		return converter.convert(proyectoupdate);
	
		}

}
