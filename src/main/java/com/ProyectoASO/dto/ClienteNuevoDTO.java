package com.ProyectoASO.dto;

import com.ProyectoASO.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteNuevoDTO {

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
	@JsonProperty(value = "telefono", required = false)
	private String telefono;
	@JsonProperty(value = "direccion", required = false)
	private String direccion;
	@JsonProperty(value = "descripcion")
	private String descripcion;
	@JsonProperty(value = "pais")
	private String pais;
	@JsonProperty(value = "cuidad")
	private String ciudad;
	@JsonProperty(value = "CP")
	private String codigoPostal;
	@JsonProperty(value = "correo")
	private String correo;
	@JsonProperty(value = "password")
	private String contraseña;

	

	public ClienteNuevoDTO(String nombre, String apellido1, String apellido2, String empresa, String telefono,
			String direccion, String descripcion, String pais, String ciudad, String codigoPostal, String correo,
			String contraseña) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
		this.telefono = telefono;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.pais = pais;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.correo = correo;
		this.contraseña = contraseña;
	}

	public ClienteNuevoDTO(Integer id, String nombre, String apellido1, String apellido2, String empresa,
			String telefono, String direccion, String descripcion, String pais, String ciudad, String codigoPostal,
			String correo, String contraseña) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
		this.telefono = telefono;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.pais = pais;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.correo = correo;
		this.contraseña = contraseña;
	}

	public ClienteNuevoDTO() {
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
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
