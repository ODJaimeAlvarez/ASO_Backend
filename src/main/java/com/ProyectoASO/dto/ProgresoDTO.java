package com.ProyectoASO.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProgresoDTO implements Serializable {
	private static final long serialVersionUID = 3999843314308155141L;
	
	@JsonProperty(value = "estado")
	private String estado;
	
	@JsonProperty(value = "code")
	private String code;
	
	public ProgresoDTO() {
	}
	
	public ProgresoDTO( String estado, String code) {
		this.estado = estado;
		this.code= code;
	}
	
	
	public String getCode() {
		return code;
	}

	public String getEstado() {
		return estado;
	}
	
}
