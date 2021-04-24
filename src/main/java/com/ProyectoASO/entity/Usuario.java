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
	
	@Column(name = "apellido1")
	private String apellido1;
	
	@Column(name = "apellido2")
	private String apellido2;
	
	@Column(name = "correo_corporativo")
	private String correo;

	@Column(name="contraseña_usuario")
	private String passwd;
	
	@Column(name= "activo")
	private Boolean activo;
	
	public Usuario() {
	}

	public Usuario(String apellido1, String apellido2, String correo, String passwd, Boolean activo) {
		super();
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.correo = correo;
		this.passwd = passwd;
		this.activo = activo;
	}

	public Usuario(Integer id, String apellido1, String apellido2, String correo, String passwd, Boolean activo) {
		super();
		this.id = id;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
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

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
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
