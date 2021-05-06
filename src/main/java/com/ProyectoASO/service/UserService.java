package com.ProyectoASO.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ProyectoASO.converter.UsuarioConverter;
import com.ProyectoASO.dao.IUsuarioDao;
import com.ProyectoASO.dto.UsuarioDTO;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.exceptions.DBException;
@Service
public class UserService implements IUserService {
	@Autowired
	IUsuarioDao usuarioRepository;
	
	@Autowired
	UsuarioConverter usuarioConverter;

	@Override
	public List<UsuarioDTO> getAllUsers() {
		return usuarioConverter.convert(usuarioRepository.findAll());
	}

	@Override
	public UsuarioDTO getUserById(Integer id) {
		Optional<Usuario> findedUser= usuarioRepository.findById(id);
		
		if(findedUser.isPresent()) {
			return usuarioConverter.convert(findedUser.get());
		}
		throw new DBException("Usuario con id "+id+" no encontrado.", HttpStatus.NOT_FOUND);
	}

	@Override
	public UsuarioDTO saveUser(UsuarioDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDTO updateUser(Long id, UsuarioDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	protected Usuario getUserByIdEntity(Integer id) {
		return usuarioRepository.findById(id).orElseThrow(()-> new DBException("No existe un usuario con id "+id, HttpStatus.NOT_FOUND));
	}

	@Override
	public Usuario buscarPorcorreo(String correo) {
		return usuarioRepository.findByCorreo(correo).orElseThrow(()-> new DBException("No existe un usuario con correo "+correo, HttpStatus.NOT_FOUND));
	}
}
