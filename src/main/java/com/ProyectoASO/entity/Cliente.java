package com.ProyectoASO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellido_1")
	private String apellido1;
	@Column(name = "apellido_2")
	private String apellido2;
	@Column(name = "empresa")
	private String empresa;
	@Column(name = "telefono",nullable = true)
	private String telefono;
	@Column(name = "direccion",nullable = true)
	private String direccion;
	@OneToOne
	private Usuario usuario;
	@OneToOne
	@JoinColumn(name = "fk_id_foto",nullable = true)
	private FotoUsuarios foto;

	
	public Cliente() {
	}
	
	public Cliente(String nombre, String apellido1, String apellido2, String empresa, String telefono, String direccion,
			 FotoUsuarios foto) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
		this.telefono = telefono;
		this.direccion = direccion;
		this.foto = foto;
	}

	public Cliente(Integer id, String nombre, String apellido1, String apellido2, String empresa, String telefono,
			String direccion, FotoUsuarios foto) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
		this.telefono = telefono;
		this.direccion = direccion;
		this.foto = foto;
	}

	public Cliente(String nombre, String apellido1, String apellido2, String empresa, String telefono, String direccion,
			Usuario usuario, FotoUsuarios foto) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
		this.telefono = telefono;
		this.direccion = direccion;
		this.usuario = usuario;
		this.foto = foto;
	}

	public Cliente(Integer id, String nombre, String apellido1, String apellido2, String empresa, String telefono,
			String direccion, Usuario usuario, FotoUsuarios foto) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
		this.telefono = telefono;
		this.direccion = direccion;
		this.usuario = usuario;
		this.foto = foto;
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

	public FotoUsuarios getFoto() {
		return foto;
	}

	public void setFoto(FotoUsuarios foto) {
		this.foto = foto;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
