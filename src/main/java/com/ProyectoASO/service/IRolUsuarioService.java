package com.ProyectoASO.service;

import java.util.List;

import com.ProyectoASO.entity.Rol;
import com.ProyectoASO.entity.Usuario;

public interface IRolUsuarioService {

	public List<Rol> getRolesByUser(Usuario user);
	
	public List<Usuario> getUserByRol(Rol rol);
	
	public void saveRolUser(Usuario user, Rol roles);
	
	
}
