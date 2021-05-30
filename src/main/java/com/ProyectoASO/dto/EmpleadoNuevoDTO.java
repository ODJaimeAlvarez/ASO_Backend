package com.ProyectoASO.dto;

import com.ProyectoASO.entity.Rol;
import com.ProyectoASO.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmpleadoNuevoDTO {

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
	@JsonProperty(value = "correo")
	private String correo;
	@JsonProperty(value = "telefono", required = true)
	private String telefono;
	@JsonProperty(value = "descripcion")
	private String descripcion;
	@JsonProperty(value = "direccion", required = true)
	private String direccion;
	@JsonProperty(value = "pais", required = true)
	private String pais;
	@JsonProperty(value = "cuidad", required = true)
	private String ciudad;
	@JsonProperty(value = "CP", required = true)
	private String codigoPostal;
	@JsonProperty(value = "password")
	private String contraseña;
	@JsonProperty(value = "rol")
	private Rol rol;

	public EmpleadoNuevoDTO(String nombre, String apellido1, String apellido2, String cargo, String correo,
			String telefono, String descripcion, String direccion, String pais, String ciudad, String codigoPostal,
			String contraseña, Rol rol) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.cargo = cargo;
		this.correo = correo;
		this.telefono = telefono;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.pais = pais;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.contraseña = contraseña;
		this.rol = rol;
	}

	public EmpleadoNuevoDTO(Integer id, String nombre, String apellido1, String apellido2, String cargo, String correo,
			String telefono, String descripcion, String direccion, String pais, String ciudad, String codigoPostal,
			String contraseña, Rol rol) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.cargo = cargo;
		this.correo = correo;
		this.telefono = telefono;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.pais = pais;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.contraseña = contraseña;
		this.rol = rol;
	}

	public EmpleadoNuevoDTO() {

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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
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
