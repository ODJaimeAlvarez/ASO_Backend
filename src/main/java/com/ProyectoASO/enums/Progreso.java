package com.ProyectoASO.enums;

public enum Progreso {
	INICIADO("iniciado"), EN_CURSO("en_curso"), FINALIZADO("finalizado"), ACEPTADO("aceptado");

	private String codigo_bd;

	private Progreso(String pro) {

		codigo_bd = pro;

	}

	public String getCodigo_bd() {
		return codigo_bd;
	}

}
