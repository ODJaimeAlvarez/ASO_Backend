package com.ProyectoASO.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JornadaDTO {
	
	@JsonProperty(value = "id")
	private Integer id;
	@JsonProperty(value = "fecha_jornada")
	private Date fechaJornada;
	@JsonProperty(value = "hora_inicio")
	private String horaInicio;
	@JsonProperty(value = "hora_fin")
	private String horaFin;
	@JsonProperty(value = "total")
	private String total;
	

	public JornadaDTO() {
	}
	public JornadaDTO(Integer id,Date fechaJornada, String horaInicio, String horaFin, String total) {
		this.id=id;
		this.fechaJornada = fechaJornada;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.total = total;
	}

	public JornadaDTO(Date fechaJornada, String horaInicio, String horaFin, String total) {
		this.fechaJornada = fechaJornada;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.total = total;
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

	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	public String getHoraInicio() {
		return horaInicio;
	}


	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}


	public String getHoraFin() {
		return horaFin;
	}


	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
}
