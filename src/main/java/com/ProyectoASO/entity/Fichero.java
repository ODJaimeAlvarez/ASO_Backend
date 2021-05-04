package com.ProyectoASO.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "fichero", uniqueConstraints = @UniqueConstraint(columnNames = { "URI", "nombre" }))
public class Fichero {

	/**
	 * Almacena el identificador del fichero
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fichero")
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
	private String nombre_fichero;

	/**
	 * Almacena la fecha de última modificación del fichero
	 */
	@Column(name = "fecha_mod", nullable = false)
	private Date fecha_mod;

	/**
	 * Relación que almacena el identificador del proyecto en el que se encuentra el
	 * fichero
	 */
	@ManyToOne
	@JoinColumn(name = "fk_id_proyecto")
	private Proyecto proyecto;

	/**
	 * Método constructor de fichero
	 * 
	 * @param uri            , ruta del fichero
	 * @param nombre_fichero , nombre que tendrá el fichero
	 * @param fecha_mod      , cuando fue modificado por última vez
	 * @param proyecto       , proyecto en el que está el fichero
	 */
	public Fichero(String uri, String nombre_fichero, Date fecha_mod, Proyecto proyecto) {
		super();
		this.uri = uri;
		this.nombre_fichero = nombre_fichero;
		this.fecha_mod = fecha_mod;
		this.proyecto= proyecto;
	}

	/**
	 * Método constructor de Fichero con el cual se crearán los objeto fichero
	 * 
	 * @param id
	 * @param uri
	 * @param nombre_fichero
	 * @param fecha_mod
	 * @param proyecto
	 */
	public Fichero(Integer id, String uri, String nombre_fichero, Date fecha_mod, Proyecto proyecto) {
		super();
		this.id = id;
		this.uri = uri;
		this.nombre_fichero = nombre_fichero;
		this.fecha_mod = fecha_mod;
		this.proyecto= proyecto;
	}

	/**
	 * Método constructor de fichero vacío
	 */
	public Fichero() {
		super();
	}

	/**
	 * Método que obtiene el identificador del fichero
	 * 
	 * @return identificador del fichero
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Método que establece un identificador al fichero
	 * 
	 * @param id identificador del fichero
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Método que obtiene la ruta del fichero
	 * 
	 * @return la ruta del fichero
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Método que establece una ruta al fichero
	 * 
	 * @param uri nueva del fichero
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * Método que obtiene el nombre del fichero
	 * 
	 * @return nombre del fichero
	 */
	public String getNombre_fichero() {
		return nombre_fichero;
	}

	/**
	 * Método que establece un nombre al fichero
	 * 
	 * @param nombre_fichero nombre del fichero
	 */
	public void setNombre_fichero(String nombre_fichero) {
		this.nombre_fichero = nombre_fichero;
	}

	/**
	 * Método que obtiene la fecha de última modificación del fichero
	 * 
	 * @return la fecha de última modificación del fichero
	 */
	public Date getFecha_mod() {
		return fecha_mod;
	}

	/**
	 * Método que establece una fecha de moficación al fichero
	 * 
	 * @param fecha_mod fecha de modifación del fichero
	 */
	public void setFecha_mod(Date fecha_mod) {
		this.fecha_mod = fecha_mod;
	}

	/**
	 * Método que obtiene el proyecto con el que está relacionado el fichero
	 * 
	 * @return proyecto con el que está relacionado el fichero
	 */
	public Proyecto getProyecto() {
		return proyecto;
	}

	/**
	 *  Método que establece el proyecto con el que está relacionado el fichero
	 * @param proyecto
	 */
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

}
