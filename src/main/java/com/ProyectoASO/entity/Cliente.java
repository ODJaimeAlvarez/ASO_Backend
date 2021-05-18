package com.ProyectoASO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@OneToOne
	private Usuario usuario;

	public Cliente(Integer id, String nombre, String apellido1, String apellido2, String empresa, Usuario usuario) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
		this.usuario = usuario;
	}

	public Cliente(String nombre, String apellido1, String apellido2, String empresa) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
	}

	public Cliente(Integer id, String nombre, String apellido1, String apellido2, String empresa) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
	}

	public Cliente(String nombre, String apellido1, String apellido2, String empresa, Usuario usuario) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
		this.usuario = usuario;
	}

	public Cliente() {
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
