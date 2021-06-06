package com.ProyectoASO.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProyectoNuevoDTO {
	@JsonProperty(value = "id")
	private Integer id;
	
	@JsonProperty(value = "nombre")
	private String nombre;
	
	@JsonProperty(value = "estado")
	private ProgresoDTO estado;
	
	@JsonProperty(value = "descripcion")
	private String descripcion;
	
	@JsonProperty(value = "empleados")
	private List<EmpleadoDTO> emp;
	
	@JsonProperty(value = "cliente")
	private ClienteDTO cli;

	public ProyectoNuevoDTO(Integer id, String nombre, ProgresoDTO estado, String descripcion, List<EmpleadoDTO> emp,
			ClienteDTO cli) {
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.descripcion = descripcion;
		this.emp = emp;
		this.cli = cli;
	}

	public ProyectoNuevoDTO() {
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public ProgresoDTO getEstado() {
		return estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public List<EmpleadoDTO> getEmp() {
		return emp;
	}

	public ClienteDTO getCli() {
		return cli;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEstado(ProgresoDTO estado) {
		this.estado = estado;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEmp(List<EmpleadoDTO> emp) {
		this.emp = emp;
	}

	public void setCli(ClienteDTO cli) {
		this.cli = cli;
	}

	
}
