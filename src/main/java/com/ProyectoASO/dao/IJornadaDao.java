package com.ProyectoASO.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoASO.entity.Empleado;
import com.ProyectoASO.entity.Jornada;

public interface IJornadaDao extends JpaRepository<Jornada, Integer> {
	
	public Optional<Jornada> findByEmpleadoAndIniciada(Empleado emp, Boolean iniciada);
	
	public List<Jornada> findByEmpleado(Empleado emp);

}
