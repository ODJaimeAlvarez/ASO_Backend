package com.ProyectoASO.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "rol_usuario")
public class rol_usuario {

	
	@ManyToOne
	@JoinColumn(name = "fk_id_rol")
	Rol id_rol;

	
	@ManyToOne
	@JoinColumn(name = "fk_id_usuario")
	Usuario id_usuario;

	public rol_usuario() {
		
	}
	

	public rol_usuario(Rol id_rol, Usuario id_usuario) {
		super();
		this.id_rol = id_rol;
		this.id_usuario = id_usuario;
	}


	public Rol getId_rol() {
		return id_rol;
	}


	public void setId_rol(Rol id_rol) {
		this.id_rol = id_rol;
	}


	public Usuario getId_usuario() {
		return id_usuario;
	}


	public void setId_usuario(Usuario id_usuario) {
		this.id_usuario = id_usuario;
	}
}
