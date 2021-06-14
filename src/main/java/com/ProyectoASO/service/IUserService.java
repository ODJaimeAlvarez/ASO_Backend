package com.ProyectoASO.service;

import java.util.List;

import com.ProyectoASO.dto.UsuarioDTO;
import com.ProyectoASO.entity.Rol;
import com.ProyectoASO.entity.Usuario;

public interface IUserService {
	
	public List<UsuarioDTO> getAllUsers();
	
	public List<Usuario> getAllUsersByRol(Rol rol);
	
	public UsuarioDTO getUserById(Integer id);
	
	public Usuario saveUser(Usuario user);
	
	
	public void deActivateUser(Integer id);
	
	public void activateUser(Integer id);
	
	public Usuario buscarPorcorreo(String correo);

}
