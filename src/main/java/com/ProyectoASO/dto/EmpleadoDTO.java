package com.ProyectoASO.dto;

import com.ProyectoASO.entity.FotoUsuarios;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmpleadoDTO {

	@JsonProperty(value = "id")
	private Integer id;
	@JsonProperty(value = "nombre")
	private String nombre;
	@JsonProperty(value = "apellido1")
	private String apellido1;
	@JsonProperty(value = "apellido2")
	private String apellido2;
	@JsonProperty(value = "cargo")
	private String cargo;
	@JsonProperty(value = "activo")
	private Boolean activo;
	@JsonProperty(value = "correo")
	private String correo;
	@JsonProperty(value = "telefono")
	private String telefono;
	@JsonProperty(value = "direccion")
	private String direccion;
	@JsonProperty(value = "descripcion")
	private String descripcion;
	@JsonProperty(value = "fotoPerfil")
	private FotoUsuarios fotoPerfil;

	public EmpleadoDTO() {
	}

	public EmpleadoDTO(String nombre, String apellido1, String apellido2, String cargo, Boolean activo, String correo,
			String telefono, String direccion, String descripcion, FotoUsuarios fotoPerfil) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.cargo = cargo;
		this.activo = activo;
		this.correo = correo;
		this.telefono = telefono;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.fotoPerfil = fotoPerfil;
	}

	public EmpleadoDTO(Integer id, String nombre, String apellido1, String apellido2, String cargo, Boolean activo,
			String correo, String telefono, String direccion, String descripcion, FotoUsuarios fotoPerfil) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.cargo = cargo;
		this.activo = activo;
		this.correo = correo;
		this.telefono = telefono;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.fotoPerfil = fotoPerfil;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public FotoUsuarios getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(FotoUsuarios fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
