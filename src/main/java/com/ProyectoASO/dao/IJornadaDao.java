package com.ProyectoASO.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoASO.entity.Jornada;
import com.ProyectoASO.entity.Usuario;

public interface IJornadaDao extends JpaRepository<Jornada, Integer> {
	
	public Optional<Jornada> findByUserAndIniciada(Usuario user, Boolean iniciada);
	public List<Jornada> findByUser(Usuario user);

}
