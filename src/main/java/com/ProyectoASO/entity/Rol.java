package com.ProyectoASO.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "rol", uniqueConstraints = @UniqueConstraint(columnNames = {"rol"}))
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Integer id;
	
	@Column(name = "rol")
	private String rolType;

//	@OneToMany(mappedBy = "rol")
//	Set<rol_usuario> usuario;
	
//	public Set<rol_usuario> getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Set<rol_usuario> usuario) {
//		this.usuario = usuario;
//	}

	public Rol() {
	}

	public Rol(Integer id, String rolType) {
		super();
		this.id = id;
		this.rolType = rolType;
	}

	public Rol(String rolType) {
		super();
		this.rolType = rolType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRol() {
		return rolType;
	}

	public void setRol(String rolType) {
		this.rolType = rolType;
	}

	public String getRolType() {
		return rolType;
	}

	public void setRolType(String rolType) {
		this.rolType = rolType;
	}
	
	
}
