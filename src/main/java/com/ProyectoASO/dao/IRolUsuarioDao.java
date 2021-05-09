package com.ProyectoASO.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoASO.entity.RolUsuario;
import com.ProyectoASO.entity.Usuario;

public interface IRolUsuarioDao extends JpaRepository<RolUsuario, Integer> {
	public List<RolUsuario> findByUsuario(Usuario user);
}
