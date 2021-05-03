package com.ProyectoASO.enums;

public enum Progreso {
	INICIADO("iniciado"), EN_CURSO("en_curso"), FINALIZADO("finalizado"), ACEPTADO("aceptado");

	private final String codigo_bd;
	private final String estado;

	private Progreso(String codigo_bd) {
		this.codigo_bd = codigo_bd;
		this.estado=this.name();
	}

	public String getCodigo_bd() {
		return codigo_bd;
	}

	public String getEstado() {
		return estado;
	}


}
