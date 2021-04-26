package com.ProyectoASO.exceptions;

import org.springframework.http.HttpStatus;

public class DBException extends RuntimeException{

	private static final long serialVersionUID = 2657203386981312780L;
	
	private final HttpStatus estado;

	public DBException(String mensaje, HttpStatus estado) {
		super(mensaje);
		this.estado = estado;
	}

	public HttpStatus getEstado() {
		return estado;
	}
	

}
