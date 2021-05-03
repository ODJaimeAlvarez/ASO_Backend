package com.ProyectoASO.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProyectoDTO implements Serializable {
	private static final long serialVersionUID = -8013817840816663111L;

	@JsonProperty(value = "id")
	private Integer id;
	
	@JsonProperty(value = "nombre")
	private String nombre;
	
	@JsonProperty(value = "estado")
	private ProgresoDTO estado;
	
	@JsonProperty(value = "descripcion")
	private String descripcion;

	public ProyectoDTO(Integer id, String nombre, ProgresoDTO estado, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado=estado;
		this.descripcion = descripcion;
	}

	public ProyectoDTO() {
		super();
	}

	public ProyectoDTO(String nombre, ProgresoDTO estado, String descripcion) {
		super();
		this.nombre = nombre;
		this.estado=estado;
		this.descripcion = descripcion;
	}

	public ProgresoDTO getEstado() {
		return estado;
	}

	public void setEstado(ProgresoDTO estado) {
		this.estado = estado;
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
