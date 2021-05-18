package com.ProyectoASO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id;

	@Column(name = "correo_corporativo")
	private String correo;

	@Column(name = "password_usuario")
	private String passwd;

	@Column(name = "activo")
	private Boolean activo;

	public Usuario() {
	}

	public Usuario(String correo, String passwd, Boolean activo) {

		this.correo = correo;
		this.passwd = passwd;
		this.activo = activo;

	}

	public Usuario(Integer id,String correo, String passwd,
			Boolean activo) {
		this.id = id;

		this.correo = correo;
		this.passwd = passwd;
		this.activo = activo;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
