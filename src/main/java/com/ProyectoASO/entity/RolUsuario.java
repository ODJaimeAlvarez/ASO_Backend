package com.ProyectoASO.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rol_usuario")
public class RolUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	
	@ManyToOne
	@JoinColumn(name = "fk_id_rol")
	Rol rol;

	
	@ManyToOne
	@JoinColumn(name = "fk_id_usuario")
	Usuario usuario;

	public RolUsuario() {
		
	}
	

	public RolUsuario(Integer id, Rol rol, Usuario usuario) {
		this.id = id;
		this.rol = rol;
		this.usuario = usuario;
	}


	public RolUsuario(Rol rol, Usuario usuario) {
		super();
		this.rol = rol;
		this.usuario = usuario;
	}


	public Rol getRol() {
		return rol;
	}


	public void setRol(Rol rol) {
		this.rol = rol;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
