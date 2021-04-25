package com.ProyectoASO.service;

import java.util.List;

import com.ProyectoASO.dto.UsuarioDTO;
import com.ProyectoASO.entity.Usuario;

public interface IUserService {
	
	public List<UsuarioDTO> getAllUsers();
	
	public UsuarioDTO getUserById(Integer id);
	
	public UsuarioDTO saveUser(UsuarioDTO user);
	
	public UsuarioDTO updateUser(Long id, UsuarioDTO user);
	
	public void deleteUserById(Integer id);
	
	public Usuario buscarPorcorreo(String correo);

}
