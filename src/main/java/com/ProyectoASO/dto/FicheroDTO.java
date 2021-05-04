package com.ProyectoASO.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FicheroDTO implements Serializable {

	private static final long serialVersionUID = 4158277299194856640L;
	
	@JsonProperty(value="id")
	private Integer id;
	
	@JsonProperty(value="nombre")
	private String name;
	
	@JsonProperty(value="fecha_mod")
	private Date fechaMod;
	
	@JsonProperty(value="proyecto")
	private ProyectoDTO proyecto;
	

	public FicheroDTO() {
	}

	public FicheroDTO(String name, Date fechaMod,ProyectoDTO proyecto) {
		this.name = name;
		this.fechaMod = fechaMod;
		this.proyecto=proyecto;
	}

	public FicheroDTO(Integer id ,String name, Date fechaMod,ProyectoDTO proyecto) {
		this.id=id;
		this.name = name;
		this.fechaMod = fechaMod;
		this.proyecto=proyecto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFechaMod() {
		return fechaMod;
	}

	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProyectoDTO getProyecto() {
		return proyecto;
	}

	public void setProyecto(ProyectoDTO proyecto) {
		this.proyecto = proyecto;
	}
	
}
