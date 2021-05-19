package com.ProyectoASO.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoASO.entity.Cliente;
import com.ProyectoASO.entity.Usuario;

public interface IClienteDao extends JpaRepository<Cliente, Integer> {
	
	public Optional<Cliente> findByUsuario(Usuario user);

}
