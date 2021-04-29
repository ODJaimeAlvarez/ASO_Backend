package com.ProyectoASO.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoASO.entity.Proyecto;

public interface IProyectoDao extends JpaRepository<Proyecto, Integer>{

}
