package com.ProyectoASO.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoASO.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {

}
