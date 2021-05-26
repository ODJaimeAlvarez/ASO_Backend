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
@Table(name = "nota")
public class Notas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_nota")
	private Integer id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "nota")
	private String nota;
	@OneToOne
	@JoinColumn(name = "fk_id_usuario")
	private Usuario usuario;

	public Notas(String nombre, String nota, Usuario usuario) {
		this.nombre = nombre;
		this.nota = nota;
		this.usuario = usuario;
	}

	public Notas(Integer id, String nombre, String nota, Usuario usuario) {
		this.id = id;
		this.nombre = nombre;
		this.nota = nota;
		this.usuario = usuario;
	}

	public Notas(String nombre, String nota) {
		this.nombre = nombre;
		this.nota = nota;
	}

	public Notas(Integer id, String nombre, String nota) {
		this.id = id;
		this.nombre = nombre;
		this.nota = nota;
	}

	public Notas() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
