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
	private Integer idJornada;

	@Column(name = "fecha", nullable = false)
	private Date fechaJornada;

	@Column(name = "hora_inicio", nullable = false)
	private Time horaInicio;

	@Column(name = "hora_fin", nullable = true)
	private Time horaFin;

	@Column(name = "iniciada", nullable = false)
	private Boolean iniciada;

	@ManyToOne
	@JoinColumn(name = "fk_id_empleado")
	private Empleado empleado;

	public Jornada() {
	}

	public Jornada(Integer idJornada, Date fechaJornada, Time horaInicio, Time horaFin, Empleado empleado) {
		this.idJornada = idJornada;
		this.fechaJornada = fechaJornada;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.empleado = empleado;
	}

	public Jornada(Date fechaJornada, Time horaInicio, Time horaFin, Empleado empleado) {
		this.fechaJornada = fechaJornada;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.empleado = empleado;
	}

	public Integer getIdJornada() {
		return idJornada;
	}

	public void setIdJornada(Integer idJornada) {
		this.idJornada = idJornada;
	}

	public Date getFechaJornada() {
		return fechaJornada;
	}

	public void setFechaJornada(Date fechaJornada) {
		this.fechaJornada = fechaJornada;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	public Empleado getUser() {
		return empleado;
	}

	public void setUser(Empleado empleado) {
		this.empleado = empleado;
	}

	public Boolean getIniciada() {
		return iniciada;
	}

	public void setIniciada(Boolean iniciada) {
		this.iniciada = iniciada;
	}
}
