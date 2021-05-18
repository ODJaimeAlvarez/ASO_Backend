package com.ProyectoASO.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteDTO {

	@JsonProperty(value = "id")
	private Integer id;
	@JsonProperty(value = "nombre")
	private String nombre;
	@JsonProperty(value = "apellido1")
	private String apellido1;
	@JsonProperty(value = "apellido2")
	private String apellido2;
	@JsonProperty(value = "empresa")
	private String empresa;

	public ClienteDTO(Integer id, String nombre, String apellido1, String apellido2, String empresa) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
	}

	public ClienteDTO(String nombre, String apellido1, String apellido2, String empresa) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

}
