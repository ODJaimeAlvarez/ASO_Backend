package com.ProyectoASO.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProyectoDTO implements Serializable {
	private static final long serialVersionUID = -8013817840816663111L;

	@JsonProperty(value = "id")
	private Integer id;
	
	@JsonProperty(value = "nombre")
	private String nombre;
	
	//TODO converter de progreso
	
	@JsonProperty(value = "descripcion")
	private String descripcion;

	public ProyectoDTO(Integer id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public ProyectoDTO() {
		super();
	}

	public ProyectoDTO(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
