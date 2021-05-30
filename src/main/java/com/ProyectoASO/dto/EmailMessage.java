package com.ProyectoASO.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailMessage {
	@JsonProperty(value = "asunto")
	private String asunto;
	@JsonProperty(value = "correo")
	private String correo;
	@JsonProperty(value = "mensaje")
	private String mensaje;

	public EmailMessage(String asunto, String correo, String mensaje) {
		this.asunto = asunto;
		this.correo = correo;
		this.mensaje = mensaje;
	}

	public EmailMessage() {
	}

	public String getAsunto() {
		return asunto;
	}

	public String getCorreo() {
		return correo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
