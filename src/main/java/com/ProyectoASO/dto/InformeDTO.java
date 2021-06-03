package com.ProyectoASO.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InformeDTO {

	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty
	private Integer valor;
	
	public InformeDTO(String nombre, Integer valor) {
		this.nombre = nombre;
		this.valor = valor;
	}
	public String getNombre() {
		return nombre;
	}
	public Integer getValor() {
		return valor;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
}
