package com.ProyectoASO.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoASO.entity.ProyectoUsuario;
import com.ProyectoASO.entity.Usuario;

public interface IProyectoUsuario extends JpaRepository<ProyectoUsuario, Integer>{
	
	public List<ProyectoUsuario> findByUsuario(Usuario user);

}
