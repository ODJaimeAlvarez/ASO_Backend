package com.ProyectoASO.exceptions;

import org.springframework.http.HttpStatus;

public class FileSystemException extends Exception {

private static final long serialVersionUID = 2657203386981312780L;
	
	private final HttpStatus estado;

	public FileSystemException(String mensaje, HttpStatus estado) {
		super(mensaje);
		this.estado = estado;
	}

	public HttpStatus getEstado() {
		return estado;
	}
}
