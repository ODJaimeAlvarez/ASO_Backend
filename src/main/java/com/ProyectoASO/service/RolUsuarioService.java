package com.ProyectoASO.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ProyectoASO.dao.IRolUsuarioDao;
import com.ProyectoASO.entity.Rol;
import com.ProyectoASO.entity.RolUsuario;
import com.ProyectoASO.entity.Usuario;
@Service
public class RolUsuarioService implements IRolUsuarioService {
	
	private IRolUsuarioDao rolUsuarioDao;

	public RolUsuarioService(IRolUsuarioDao rolUsuarioDao) {
		this.rolUsuarioDao = rolUsuarioDao;
	}

	@Override
	public List<Rol> getRolesByUser(Usuario user) {
		
		List<Rol> roles= new ArrayList<>();
		List<RolUsuario> rolesUsuario=rolUsuarioDao.findByUsuario(user);
		for(RolUsuario ru : rolesUsuario)
			roles.add(ru.getRol());
		return roles;
	}

	@Override
	public void changeRolesToUser(Usuario user, List<Rol> roles) {
		// TODO Auto-generated method stub
		
	}

	

}
