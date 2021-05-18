package com.ProyectoASO.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoASO.entity.Empleado;
import com.ProyectoASO.entity.Usuario;

public interface EmpleadoDao extends JpaRepository<Empleado, Integer> {
	
	public Optional<Empleado> findByUsuario(Usuario user);

}
