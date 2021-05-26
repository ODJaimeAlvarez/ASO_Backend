package com.ProyectoASO.dto;

import com.ProyectoASO.entity.FotoUsuarios;
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
	@JsonProperty(value = "activo")
	private Boolean active;
	@JsonProperty(value = "correo")
	private String correo;
	@JsonProperty(value = "telefono")
	private String telefono;
	@JsonProperty(value = "direccion")
	private String direccion;
	@JsonProperty(value = "descripcion")
	private String descripcion;
	@JsonProperty(value = "pais")
	private String pais;
	@JsonProperty(value = "cuidad")
	private String ciudad;
	@JsonProperty(value = "CP")
	private String codigoPostal;
	@JsonProperty(value = "fotoPerfil")
	private FotoUsuarios fotoPerfil;

	public ClienteDTO() {
	}

	public ClienteDTO(String nombre, String apellido1, String apellido2, String empresa, Boolean active, String correo,
			String telefono, String direccion, String descripcion, String pais, String ciudad, String codigoPostal,
			FotoUsuarios fotoPerfil) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
		this.active = active;
		this.correo = correo;
		this.telefono = telefono;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.pais = pais;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.fotoPerfil = fotoPerfil;
	}

	public ClienteDTO(Integer id, String nombre, String apellido1, String apellido2, String empresa, Boolean active,
			String correo, String telefono, String direccion, String descripcion, String pais, String ciudad,
			String codigoPostal, FotoUsuarios fotoPerfil) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
		this.active = active;
		this.correo = correo;
		this.telefono = telefono;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.pais = pais;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
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

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
