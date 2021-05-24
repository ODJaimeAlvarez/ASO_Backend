package com.ProyectoASO.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "foto_user", uniqueConstraints = @UniqueConstraint(columnNames = { "URI", "nombre" }))
public class FotoUsuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_foto")
	private Integer id;

	/**
	 * Almacena la ruta del fichero
	 */
	@Column(name = "URI")
	private String uri;

	/**
	 * Almacena el nombre que tendrá el fichero
	 */
	@Column(name = "nombre", nullable = false)
	private String nombreFoto;

	public FotoUsuarios() {
	}

	public FotoUsuarios(String uri, String nombreFoto) {
		this.uri = uri;
		this.nombreFoto = nombreFoto;
	}

	public FotoUsuarios(Integer id, String uri, String nombreFoto) {
		this.id = id;
		this.uri = uri;
		this.nombreFoto = nombreFoto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getNombreFoto() {
		return nombreFoto;
	}

	public void setNombreFoto(String nombreFoto) {
		this.nombreFoto = nombreFoto;
	}

}
