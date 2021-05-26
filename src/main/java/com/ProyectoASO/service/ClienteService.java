package com.ProyectoASO.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ProyectoASO.converter.ClienteConverter;
import com.ProyectoASO.dao.IClienteDao;
import com.ProyectoASO.dto.ClienteDTO;
import com.ProyectoASO.dto.ClienteNuevoDTO;
import com.ProyectoASO.dto.EmpleadoDTO;
import com.ProyectoASO.dto.EmpleadoNuevoDTO;
import com.ProyectoASO.entity.Cliente;
import com.ProyectoASO.entity.Empleado;
import com.ProyectoASO.entity.Rol;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.exceptions.DBException;
import com.ProyectoASO.jwt.TokenDetails;
import com.ProyectoASO.responses.MethodResponse;
@Service
public class ClienteService extends BaseService implements IClienteService {
	
	private final IClienteDao clienteRepository;
	private final ClienteConverter converter;
	private final UserService usuarioService;
	private final RolUsuarioService rolUsuarioService;
	
	public ClienteService(TokenDetails token, IClienteDao clienteRepository, ClienteConverter converter,
			UserService usuarioService, RolUsuarioService rolUsuarioService) {
		super(token);
		this.clienteRepository = clienteRepository;
		this.converter = converter;
		this.usuarioService = usuarioService;
		this.rolUsuarioService = rolUsuarioService;
	}	

	@Override
	public List<ClienteDTO> getAll() {
		
		return converter.convert(clienteRepository.findAll());
	}

	@Override
	public ClienteDTO getById(Integer id) {
		return converter.convert(clienteRepository.findById(id).orElseThrow(
				() -> new DBException("El cliente con id " + id + " no esta en la bbdd.", HttpStatus.NOT_FOUND)));
	}

	
	@Override
	public ClienteDTO save(ClienteNuevoDTO newCli) {
		Usuario user = usuarioService.saveUser(new Usuario(newCli.getCorreo(), newCli.getContraseña(), true));
		rolUsuarioService.saveRolUser(user, new Rol(2,"CLIENTE"));
		return converter.convert(clienteRepository
				.save(new Cliente(newCli.getNombre(), newCli.getApellido1(), newCli.getApellido2(),
						newCli.getEmpresa(),newCli.getTelefono(),newCli.getDireccion(),newCli.getDescripcion(),newCli.getCiudad(),newCli.getPais(),newCli.getCodigoPostal(), user,null)));
	}

	@Override
	public ClienteDTO update(Integer id, ClienteDTO cli) {
		Cliente cliUpdate = clienteRepository.findById(id).orElseThrow(() -> new DBException("El cliente con id " + id + " no esta en la bbdd.", HttpStatus.NOT_FOUND));
		cliUpdate.setNombre(cli.getNombre());
		cliUpdate.setApellido1(cli.getApellido1());
		cliUpdate.setApellido2(cli.getApellido2());
		cliUpdate.setTelefono(cli.getTelefono());
		cliUpdate.setDireccion(cli.getDireccion());
		cliUpdate.setPais(cli.getPais());
		cliUpdate.setCiudad(cli.getCiudad());
		cliUpdate.setCodigoPostal(cli.getCodigoPostal());
		return converter.convert(clienteRepository.save(cliUpdate));
	}

	@Override
	public ResponseEntity<MethodResponse> deActivate(Integer id) {
		Cliente cli= clienteRepository.findById(id).orElseThrow(
				() -> new DBException("El cliente con id " + id + " no esta en la bbdd.", HttpStatus.NOT_FOUND));
		usuarioService.deActivateUser(cli.getUsuario().getId());
		return new ResponseEntity<>(new MethodResponse("El cliente con id "+id+" ha sido dado de baja"),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<MethodResponse> activate(Integer id) {
		Cliente cli= clienteRepository.findById(id).orElseThrow(
				() -> new DBException("El cliente con id " + id + " no esta en la bbdd.", HttpStatus.NOT_FOUND));
		usuarioService.activateUser(cli.getUsuario().getId());
		return new ResponseEntity<>(new MethodResponse("El cliente con id "+id+" ha sido dado de alta"),HttpStatus.OK);
	}

	@Override
	public ClienteDTO getPerfil() {
		Usuario user= usuarioService.buscarPorcorreo(getToken().getEmail());
		return converter.convert(clienteRepository.findByUsuario(user).orElseThrow(()->new DBException("El cliente no esta en la bbdd.", HttpStatus.NOT_FOUND)));
	}
	
	

}
