package com.ProyectoASO.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JornadaManagerResponse {
	
	@JsonProperty(value = "mensaje")
	private String mensaje;
	@JsonProperty(value = "fecha")
	private String fecha;
	@JsonProperty(value = "horaInicio")
	private String horaInicio;
	@JsonProperty(value = "horafin")
	private String horaFin;

	public JornadaManagerResponse(String mensaje, String fecha, String horaInicio, String horaFin) {
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
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
