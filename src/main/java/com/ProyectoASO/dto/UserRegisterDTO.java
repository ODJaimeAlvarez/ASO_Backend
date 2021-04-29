package com.ProyectoASO.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterDTO {

	@JsonProperty(value = "nombre")
	private String nombre;
	
	@JsonProperty(value = "apellido1")
	private String apellido1;
	
	@JsonProperty(value = "apellido2")
	private String apellido2;
	
	@JsonProperty(value = "correo")
	private String correo;
	
	@JsonProperty(value = "password")
	private String passwd;

	public UserRegisterDTO() {
	}

	public UserRegisterDTO(String nombre, String apellido1, String apellido2, String correo, String passwd) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.correo = correo;
		this.passwd = passwd;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	
}
