package com.ProyectoASO.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "proyecto_usuario")
public class ProyectoUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "fk_id_proyecto")
	private Proyecto proyecto;
	@ManyToOne
	@JoinColumn(name = "fk_id_usuario")
	private Usuario usuario;

	public ProyectoUsuario() {
	}

	public ProyectoUsuario(Proyecto proyecto, Usuario usuario) {
		this.proyecto = proyecto;
		this.usuario = usuario;
	}

	public ProyectoUsuario(Integer id, Proyecto proyecto, Usuario usuario) {
		this.id = id;
		this.proyecto = proyecto;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
