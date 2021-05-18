package com.ProyectoASO.service;

import java.util.List;

import com.ProyectoASO.dto.UsuarioDTO;
import com.ProyectoASO.entity.Usuario;

public interface IUserService {
	
	public List<UsuarioDTO> getAllUsers();
	
	public UsuarioDTO getUserById(Integer id);
	
	public Usuario saveUser(Usuario user);
	
	public Usuario updateUser(Integer id, Usuario user);
	
	public void deActivateUser(Integer id);
	
	public Usuario buscarPorcorreo(String correo);

}
