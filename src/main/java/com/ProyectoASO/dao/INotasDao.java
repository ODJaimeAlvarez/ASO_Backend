package com.ProyectoASO.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoASO.entity.Notas;
import com.ProyectoASO.entity.Usuario;

public interface INotasDao extends JpaRepository<Notas, Integer>{
	
	public List<Notas> findByUsuario(Usuario usuario);

}
