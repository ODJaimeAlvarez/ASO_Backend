package com.ProyectoASO.exceptions;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerDB extends RuntimeException{

	private static final long serialVersionUID = -5434494296736918119L;
	
	@ExceptionHandler(DBException.class)
	public ResponseEntity<String> dBException(final DBException e, final WebRequest request) {
		final String error = new JSONObject()
				.put("DataBase Error", e.getMessage()).toString();
		return new ResponseEntity<>(error, e.getEstado());
	}
	@ExceptionHandler(FileSystemException.class)
	public ResponseEntity<String> fileSystemException(final FileSystemException e, final WebRequest request) {
		final String error = new JSONObject()
				.put("File System Error", e.getMessage()).toString();
		return new ResponseEntity<>(error, e.getEstado());
	}
	@ExceptionHandler(AuthoritiesException.class)
	public ResponseEntity<String> authoritiesException(final AuthoritiesException e, final WebRequest request) {
		final String error = new JSONObject()
				.put("Authorization Error", e.getMessage()).toString();
		return new ResponseEntity<>(error, e.getEstado());
	}
}
