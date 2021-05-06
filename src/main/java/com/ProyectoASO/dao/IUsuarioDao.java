package com.ProyectoASO.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ProyectoASO.entity.Usuario;


public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {
	
	public Optional<Usuario> findByCorreo(String correo);

}
