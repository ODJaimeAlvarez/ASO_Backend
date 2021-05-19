package com.ProyectoASO.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ProyectoASO.converter.UsuarioConverter;
import com.ProyectoASO.dao.IUsuarioDao;
import com.ProyectoASO.dto.UsuarioDTO;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.exceptions.DBException;
import com.ProyectoASO.jwt.TokenDetails;
@Service
public class UserService extends BaseService implements IUserService {
	private IUsuarioDao usuarioRepository;
	private UsuarioConverter usuarioConverter;
	
	@Autowired
	BCryptPasswordEncoder crypt;
	
	
	public UserService(TokenDetails token, IUsuarioDao usuarioRepository, UsuarioConverter usuarioConverter) {
		super(token);
		this.usuarioRepository = usuarioRepository;
		this.usuarioConverter = usuarioConverter;
	}

	@Override
	public List<UsuarioDTO> getAllUsers() {
		checkAuthority(List.of("DIRECTOR"));
		return usuarioConverter.convert(usuarioRepository.findAll());
	}

	@Override
	public UsuarioDTO getUserById(Integer id) {
		checkAuthority(List.of("DIRECTOR"));
		Optional<Usuario> findedUser= usuarioRepository.findById(id);
		
		if(findedUser.isPresent()) {
			return usuarioConverter.convert(findedUser.get());
		}
		throw new DBException("Usuario con id "+id+" no encontrado.", HttpStatus.NOT_FOUND);
	}

	@Override
	public Usuario saveUser(Usuario user) {
		//checkAuthority(List.of("DIRECTOR"));
		System.out.println(user.getPasswd());
		user.setPasswd(crypt.encode(user.getPasswd()));
		return usuarioRepository.save(user);
	}

	@Override
	public Usuario updateUser(Integer id, Usuario user) {
		checkAuthority(List.of("DIRECTOR"));
		return null;
	}

	@Override
	public void deActivateUser(Integer id) {
		checkAuthority(List.of("DIRECTOR"));
		Usuario user= usuarioRepository.findById(id).orElseThrow(()-> new DBException("Usuario con id "+id+" no encontrado.", HttpStatus.NOT_FOUND));
		user.setActivo(false);
		usuarioRepository.save(user);
	}
	
	@Override
	public void activateUser(Integer id) {
		checkAuthority(List.of("DIRECTOR"));
		Usuario user= usuarioRepository.findById(id).orElseThrow(()-> new DBException("Usuario con id "+id+" no encontrado.", HttpStatus.NOT_FOUND));
		user.setActivo(false);
		usuarioRepository.save(user);
	}
	
	protected Usuario getUserByIdEntity(Integer id) {
		return usuarioRepository.findById(id).orElseThrow(()-> new DBException("No existe un usuario con id "+id, HttpStatus.NOT_FOUND));
	}

	@Override
	public Usuario buscarPorcorreo(String correo) {
		return usuarioRepository.findByCorreo(correo).orElseThrow(()-> new DBException("No existe un usuario con correo "+correo, HttpStatus.NOT_FOUND));
	}
}
