package com.ProyectoASO.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoASO.entity.Fichero;

public interface IFicheroDao extends JpaRepository<Fichero, Integer>{

}
