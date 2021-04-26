package com.ProyectoASO.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "fichero", uniqueConstraints = @UniqueConstraint(columnNames = { "URI" }))
public class Fichero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fichero")
	private Integer id;

	@Column(name = "URI")
	private String uri;

	@Column(name = "nombre", nullable = false)
	private String nombre_fichero;

	@Column(name = "fecha_mod", nullable = false)
	private Date fecha_mod;

	
	@ManyToOne
	private Proyecto proyecto;

	
	
	public Fichero(String uri, String nombre_fichero, Date fecha_mod, Proyecto proyecto) {
		super();
		this.uri = uri;
		this.nombre_fichero = nombre_fichero;
		this.fecha_mod = fecha_mod;
		this.proyecto = proyecto;
	}


	public Fichero(Integer id, String uri, String nombre_fichero, Date fecha_mod, Proyecto proyecto) {
		super();
		this.id = id;
		this.uri = uri;
		this.nombre_fichero = nombre_fichero;
		this.fecha_mod = fecha_mod;
		this.proyecto = proyecto;
	}


	public Fichero() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUri() {
		return uri;
	}


	public void setUri(String uri) {
		this.uri = uri;
	}


	public String getNombre_fichero() {
		return nombre_fichero;
	}


	public void setNombre_fichero(String nombre_fichero) {
		this.nombre_fichero = nombre_fichero;
	}


	public Date getFecha_mod() {
		return fecha_mod;
	}


	public void setFecha_mod(Date fecha_mod) {
		this.fecha_mod = fecha_mod;
	}


	public Proyecto getProyecto() {
		return proyecto;
	}


	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}


	
}
