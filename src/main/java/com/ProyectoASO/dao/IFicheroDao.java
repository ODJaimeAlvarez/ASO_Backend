package com.ProyectoASO.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoASO.entity.Fichero;
import com.ProyectoASO.entity.Proyecto;

public interface IFicheroDao extends JpaRepository<Fichero, Integer>{
	public List<Fichero> findByProyecto(Proyecto proyecto);
}
