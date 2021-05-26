package com.ProyectoASO.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotasDTO {
	@JsonProperty(value = "id")
	private Integer id;
	@JsonProperty(value = "nombre")
	private String nombre;
	@JsonProperty(value = "nota")
	private String nota;

	public NotasDTO() {
	}

	public NotasDTO(String nombre, String nota) {
		this.nombre = nombre;
		this.nota = nota;
	}

	public NotasDTO(Integer id, String nombre, String nota) {
		this.id = id;
		this.nombre = nombre;
		this.nota = nota;
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

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

}
