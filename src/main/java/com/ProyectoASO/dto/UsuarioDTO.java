package com.ProyectoASO.dto;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = -3608768120871696326L;

	@JsonProperty(value = "id")
	private Integer id;
	
	@JsonProperty(value = "apellido1")
	private String apellido1;
	
	@JsonProperty(value = "apellido2")
	private String apellido2;
	
	@JsonProperty(value = "correo")
	private String correo;
	
	@JsonProperty(value = "activo")
	private Boolean activo;

	public UsuarioDTO() {}

	public UsuarioDTO(Integer id, String apellido1, String apellido2, String correo, Boolean activo) {
		super();
		this.id = id;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.correo = correo;
		this.activo = activo;
	}

	public UsuarioDTO(String apellido1, String apellido2, String correo, Boolean activo) {
		super();
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.correo = correo;
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

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
	

	
}
