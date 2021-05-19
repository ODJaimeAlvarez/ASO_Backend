package com.ProyectoASO.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ProyectoASO.converter.EmpleadoConverter;
import com.ProyectoASO.converter.EmpleadoDTOConverter;
import com.ProyectoASO.dao.EmpleadoDao;
import com.ProyectoASO.dto.EmpleadoDTO;
import com.ProyectoASO.dto.EmpleadoNuevoDTO;
import com.ProyectoASO.entity.Empleado;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.exceptions.DBException;
import com.ProyectoASO.jwt.TokenDetails;
import com.ProyectoASO.responses.MethodResponse;

@Service
public class EmpleadoService extends BaseService implements IEmpleadoService {

	private final EmpleadoDao empleadoRepository;
	private final EmpleadoConverter converter;
	private final UserService usuarioService;
	private final RolUsuarioService rolUsuarioService;

	public EmpleadoService(TokenDetails token, EmpleadoDao empleadoRepository, EmpleadoConverter converter,
			UserService usuarioService, RolUsuarioService rolUsuarioService) {
		super(token);
		this.empleadoRepository = empleadoRepository;
		this.converter = converter;
		this.usuarioService = usuarioService;
		this.rolUsuarioService = rolUsuarioService;
	}

	@Override
	public List<EmpleadoDTO> getAll() {
		return converter.convert(empleadoRepository.findAll());
	}

	@Override
	public EmpleadoDTO getById(Integer id) {
		return converter.convert(empleadoRepository.findById(id).orElseThrow(
				() -> new DBException("El empleado con id " + id + " no esta en la bbdd.", HttpStatus.NOT_FOUND)));
	}

	@Override
	public EmpleadoDTO save(EmpleadoNuevoDTO emp) {
		Usuario user = usuarioService.saveUser(emp.getUsuario());
		rolUsuarioService.saveRolUser(user, emp.getRol());
		return converter.convert(empleadoRepository
				.save(new Empleado(emp.getNombre(), emp.getApellido1(), emp.getApellido2(), emp.getPuesto(), user)));
	}

	@Override
	public EmpleadoDTO update(Integer id, EmpleadoNuevoDTO emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<MethodResponse> deActivate(Integer id) {
		Empleado emp= empleadoRepository.findById(id).orElseThrow(
				() -> new DBException("El empleado con id " + id + " no esta en la bbdd.", HttpStatus.NOT_FOUND));
		usuarioService.deActivateUser(emp.getUsuario().getId());
		return new ResponseEntity<>(new MethodResponse("El usuario con id "+id+" ha sido dado de baja"),HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<MethodResponse> activate(Integer id) {
		Empleado emp= empleadoRepository.findById(id).orElseThrow(
				() -> new DBException("El empleado con id " + id + " no esta en la bbdd.", HttpStatus.NOT_FOUND));
		usuarioService.activateUser(emp.getUsuario().getId());
		return new ResponseEntity<>(new MethodResponse("El usuario con id "+id+" ha sido dado de alta"),HttpStatus.OK);
	}
	
	protected Empleado getEmpleadoByUser(Usuario user) {
		return empleadoRepository.findByUsuario(user).orElseThrow(()->new DBException("El empleado no esta en la bbdd.", HttpStatus.NOT_FOUND));
	}

}
