package com.ProyectoASO.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = -3608768120871696326L;

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "correo")
	private String correo;

	@JsonProperty(value = "activo")
	private Boolean activo;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Integer id, String correo, Boolean activo) {
		super();
		this.id = id;
		this.correo = correo;
		this.activo = activo;
	}

	public UsuarioDTO(String correo, Boolean activo) {
		super();
		this.correo = correo;
		this.activo = activo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
