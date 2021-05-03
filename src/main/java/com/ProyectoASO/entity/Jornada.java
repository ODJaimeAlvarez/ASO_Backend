package com.ProyectoASO.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jornada")
public class Jornada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_jornada")
	private Integer id_jornada;

	@Column(name = "fecha", nullable = false)
	private Date nombre_proyecto;

	@Column(name = "hora_inicio", nullable = false)
	private Time hora_inicio;

	@Column(name = "hora_fin", nullable = true)
	private Time hora_fin;

	@ManyToOne
	@JoinColumn(name = "fk_id_usuario")
	private Usuario user;

	public Integer getId() {
		return id_jornada;
	}

	public void setId(Integer id) {
		this.id_jornada = id;
	}

	public Date getNombre_proyecto() {
		return nombre_proyecto;
	}

	public void setNombre_proyecto(Date nombre_proyecto) {
		this.nombre_proyecto = nombre_proyecto;
	}

	public Time getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public Time getHora_fin() {
		return hora_fin;
	}

	public void setHora_fin(Time hora_fin) {
		this.hora_fin = hora_fin;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	
	
}
