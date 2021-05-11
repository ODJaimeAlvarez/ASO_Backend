package com.ProyectoASO.dto;

import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JornadaDTO {
	
	@JsonProperty(value = "id")
	private Integer id;
	@JsonProperty(value = "fecha_jornada")
	private Date fechaJornada;
	@JsonProperty(value = "hora_inicio")
	private Time horaInicio;
	@JsonProperty(value = "hora_fin")
	private Time horaFin;
	@JsonProperty(value = "total")
	private String total;
	

	public JornadaDTO() {
	}

	public JornadaDTO(Date fechaJornada, Time horaInicio, Time horaFin) {
		this.fechaJornada = fechaJornada;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		
	}

	public JornadaDTO(Integer id, Date fechaJornada, Time horaInicio, Time horaFin) {
		this.id = id;
		this.fechaJornada = fechaJornada;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
