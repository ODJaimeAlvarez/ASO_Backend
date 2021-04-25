package com.ProyectoASO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ProyectoASO.enums.Progreso;

@Entity
@Table(name = "proyecto", uniqueConstraints = @UniqueConstraint(columnNames = { "nombre" }))
public class Proyecto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proyecto")
	private Integer id;

	@Column(name = "nombre", nullable = false)
	private String nombre_proyecto;

	@Column(name = "progreso", nullable = false)
	private Progreso progreso;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	public Proyecto() {
	}

	public Proyecto(Integer id, String nombre_proyecto, Progreso progreso, String descripcion) {
		super();
		this.id = id;
		this.nombre_proyecto = nombre_proyecto;
		this.progreso = progreso;
		this.descripcion = descripcion;
	}

	public Proyecto(String nombre_proyecto, Progreso progreso, String descripcion) {
		super();
		this.nombre_proyecto = nombre_proyecto;
		this.progreso = progreso;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre_proyecto() {
		return nombre_proyecto;
	}

	public void setNombre_proyecto(String nombre_proyecto) {
		this.nombre_proyecto = nombre_proyecto;
	}

	public Progreso getProgreso() {
		return progreso;
	}

	public void setProgreso(Progreso progreso) {
		this.progreso = progreso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
