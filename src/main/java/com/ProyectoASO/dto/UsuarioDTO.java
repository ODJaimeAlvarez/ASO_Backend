package com.ProyectoASO.dto;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = -3608768120871696326L;

	@JsonProperty(value = "id")
	private Integer id;
	
	@JsonProperty(value = "nombre")
	private String nombre;
	
	@JsonProperty(value = "apellido1")
	private String apellido1;
	
	@JsonProperty(value = "apellido2")
	private String apellido2;
	
	@JsonProperty(value = "correo")
	private String correo;
	
	@JsonProperty(value = "cargo")
	private String cargo;
	
	@JsonProperty(value = "activo")
	private Boolean activo;

	public UsuarioDTO() {}

	public UsuarioDTO(Integer id, String nombre, String apellido1, String apellido2, String correo, String cargo, Boolean activo) {
		super();
		this.id = id;
		this.nombre=nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.correo = correo;
		this.activo = activo;
		this.cargo=cargo;
	}

	public UsuarioDTO(String nombre, String apellido1, String apellido2, String correo, String cargo, Boolean activo) {
		super();
		this.nombre=nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.correo = correo;
		this.cargo=cargo;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	
	

	
}
