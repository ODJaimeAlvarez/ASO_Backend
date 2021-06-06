package com.ProyectoASO.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ProyectoASO.converter.EmpleadoConverter;
import com.ProyectoASO.dao.EmpleadoDao;
import com.ProyectoASO.dto.EmpleadoDTO;
import com.ProyectoASO.dto.EmpleadoNuevoDTO;
import com.ProyectoASO.entity.Empleado;
import com.ProyectoASO.entity.FotoUsuarios;
import com.ProyectoASO.entity.Rol;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.exceptions.DBException;
import com.ProyectoASO.exceptions.FileSystemException;
import com.ProyectoASO.jwt.TokenDetails;
import com.ProyectoASO.responses.MethodResponse;

@Service
public class EmpleadoService extends BaseService implements IEmpleadoService {

	private final EmpleadoDao empleadoRepository;
	private final EmpleadoConverter converter;
	private final UserService usuarioService;
	private final RolUsuarioService rolUsuarioService;
	private IFotoPerfilService fotoPerfilService;


	public EmpleadoService(TokenDetails token, EmpleadoDao empleadoRepository, EmpleadoConverter converter,
			UserService usuarioService, RolUsuarioService rolUsuarioService, IFotoPerfilService fotoPerfilService) {
		super(token);
		this.empleadoRepository = empleadoRepository;
		this.converter = converter;
		this.usuarioService = usuarioService;
		this.rolUsuarioService = rolUsuarioService;
		this.fotoPerfilService = fotoPerfilService;
	}

	@Override
	public List<EmpleadoDTO> getAll() {
		checkAuthority(List.of("DIRECTOR"));
		return converter.convert(empleadoRepository.findAll());
	}

	@Override
	public EmpleadoDTO getById(Integer id) {
		checkAuthority(List.of("DIRECTOR"));
		return converter.convert(empleadoRepository.findById(id).orElseThrow(
				() -> new DBException("El empleado con id " + id + " no esta en la bbdd.", HttpStatus.NOT_FOUND)));
	}

	@Override
	public EmpleadoDTO save(EmpleadoNuevoDTO emp) {
		checkAuthority(List.of("DIRECTOR"));
		if(!usuarioService.userExist(emp.getCorreo())) {
		Usuario user = usuarioService.saveUser(new Usuario(emp.getCorreo(), emp.getContraseña(), true));
		rolUsuarioService.saveRolUser(user, emp.getRol());
		return converter.convert(empleadoRepository
				.save(new Empleado(emp.getNombre(), emp.getApellido1(), emp.getApellido2(), emp.getCargo(),
						emp.getTelefono(),emp.getDireccion(),emp.getDescripcion(),emp.getCiudad(),emp.getPais(),emp.getCodigoPostal(),null ,user)));
		}
		else {
			throw new DBException("El usuario ya existe.", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public EmpleadoDTO update(Integer id, EmpleadoDTO emp) {
		Empleado empUpdate = empleadoRepository.findById(id).orElseThrow(() -> new DBException("El empleado con id " + id + " no esta en la bbdd.", HttpStatus.NOT_FOUND));
		empUpdate.setNombre(emp.getNombre());
		empUpdate.setApellido1(emp.getApellido1());
		empUpdate.setApellido2(emp.getApellido2());
		empUpdate.setTelefono(emp.getTelefono());
		empUpdate.setDireccion(emp.getDireccion());
		empUpdate.setDescripcion(emp.getDescripcion());
		empUpdate.setPais(emp.getPais());
		empUpdate.setCiudad(emp.getCiudad());
		empUpdate.setCodigoPostal(emp.getCodigoPostal());
		return converter.convert(empleadoRepository.save(empUpdate));
	}

	@Override
	public ResponseEntity<MethodResponse> deActivate(Integer id) {
		checkAuthority(List.of("DIRECTOR"));
		Empleado emp= empleadoRepository.findById(id).orElseThrow(
				() -> new DBException("El empleado con id " + id + " no esta en la bbdd.", HttpStatus.NOT_FOUND));
		usuarioService.deActivateUser(emp.getUsuario().getId());
		return new ResponseEntity<>(new MethodResponse("El usuario con id "+id+" ha sido dado de baja"),HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<MethodResponse> activate(Integer id) {
		checkAuthority(List.of("DIRECTOR"));
		Empleado emp= empleadoRepository.findById(id).orElseThrow(
				() -> new DBException("El empleado con id " + id + " no esta en la bbdd.", HttpStatus.NOT_FOUND));
		usuarioService.activateUser(emp.getUsuario().getId());
		return new ResponseEntity<>(new MethodResponse("El usuario con id "+id+" ha sido dado de alta"),HttpStatus.OK);
	}
	
	protected Empleado getEmpleadoByUser(Usuario user) {
		return empleadoRepository.findByUsuario(user).orElseThrow(()->new DBException("El empleado no esta en la bbdd.", HttpStatus.NOT_FOUND));
	}

	@Override
	public EmpleadoDTO getPerfil() {
		Usuario user= usuarioService.buscarPorcorreo(getToken().getEmail());
		return converter.convert(empleadoRepository.findByUsuario(user).orElseThrow(()->new DBException("El empleado no esta en la bbdd.", HttpStatus.NOT_FOUND)));
	}
	
	@Override
	public ResponseEntity<MethodResponse> changePhoto(Integer id, MultipartFile photo) throws FileSystemException {
		Empleado emp = empleadoRepository.findById(id).orElseThrow(
				() -> new DBException("El empleado con id " + id + " no esta en la bbdd.", HttpStatus.NOT_FOUND));
		if (emp.getFoto() == null) {
			FotoUsuarios foto = fotoPerfilService.save(photo);
			emp.setFoto(foto);

		} else {
			FotoUsuarios foto = fotoPerfilService.updateFile(emp.getFoto().getId(), photo);
			emp.setFoto(foto);
		}
		empleadoRepository.save(emp);
		return new ResponseEntity<>(new MethodResponse("Imagen actualizada satisfactoriamente"), HttpStatus.OK);
	}

	@Override
	public List<EmpleadoDTO> getAllEmpleados() {
		List<Usuario> emp = usuarioService.getAllUsersByRol(new Rol(3,"EMPLEADO"));
		List<Empleado> empDTO = new ArrayList<>();
		for(Usuario e : emp) {
			empDTO.add(empleadoRepository.findByUsuario(e).orElseThrow(()->new DBException("El empleado no esta en la bbdd.", HttpStatus.NOT_FOUND)));
		}
		return converter.convert(empDTO);
	}

	@Override
	public List<EmpleadoDTO> getAllDirectores() {
		List<Usuario> dir = usuarioService.getAllUsersByRol(new Rol(1,"DIRECTOR"));
		List<Empleado> dirDTO = new ArrayList<>();
		for(Usuario e : dir) {
			dirDTO.add(empleadoRepository.findByUsuario(e).orElseThrow(()->new DBException("El empleado no esta en la bbdd.", HttpStatus.NOT_FOUND)));
		}
		return converter.convert(dirDTO);
	}
	

}
