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
@Table(name = "empleado")
public class Empleado {

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
	@Column(name = "puesto")
	private String puesto;
	@OneToOne
	@JoinColumn(name = "fk_id_usuario")
	private Usuario usuario;

	public Empleado() {
	}

	public Empleado(Integer id, String nombre, String apellido1, String apellido2, String puesto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.puesto = puesto;
	}

	public Empleado(String nombre, String apellido1, String apellido2, String puesto) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.puesto = puesto;
	}

	public Empleado(String nombre, String apellido1, String apellido2, String puesto, Usuario usuario) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.puesto = puesto;
		this.usuario = usuario;
	}

	public Empleado(Integer id, String nombre, String apellido1, String apellido2, String puesto, Usuario usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.puesto = puesto;
		this.usuario = usuario;
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

	public String getPuesto() {
		return puesto;
	}

	public void getPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}