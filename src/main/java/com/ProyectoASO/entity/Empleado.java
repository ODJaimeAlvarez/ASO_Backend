package com.ProyectoASO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "empleado")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado")
	private Integer id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellido_1")
	private String apellido1;
	@Column(name = "apellido_2")
	private String apellido2;
	@Column(name = "puesto")
	private String puesto;
	@Column(name = "telefono")
	private String telefono;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "descripcion", nullable = true)
	private String descripcion;
	@Column(name = "pais")
	private String pais;
	@Column(name = "ciudad")
	private String ciudad;
	@Column(name = "CP")
	private String codigoPostal;
	@OneToOne
	@JoinColumn(name = "fk_id_foto", nullable = true)
	private FotoUsuarios foto;

	@OneToOne
	@JoinColumn(name = "fk_id_usuario")
	private Usuario usuario;

	public Empleado() {
	}

	public Empleado(String nombre, String apellido1, String apellido2, String puesto, String telefono, String direccion,
			String descripcion, String pais, String ciudad, String codigoPostal, FotoUsuarios foto) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.puesto = puesto;
		this.telefono = telefono;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.pais = pais;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.foto = foto;
	}

	public Empleado(Integer id, String nombre, String apellido1, String apellido2, String puesto, String telefono,
			String direccion, String descripcion, String pais, String ciudad, String codigoPostal, FotoUsuarios foto) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.puesto = puesto;
		this.telefono = telefono;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.pais = pais;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.foto = foto;
	}

	public Empleado(String nombre, String apellido1, String apellido2, String puesto, String telefono, String direccion,
			String descripcion, String pais, String ciudad, String codigoPostal, FotoUsuarios foto, Usuario usuario) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.puesto = puesto;
		this.telefono = telefono;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.pais = pais;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.foto = foto;
		this.usuario = usuario;
	}

	public Empleado(Integer id, String nombre, String apellido1, String apellido2, String puesto, String telefono,
			String direccion, String descripcion, String pais, String ciudad, String codigoPostal, FotoUsuarios foto,
			Usuario usuario) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.puesto = puesto;
		this.telefono = telefono;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.pais = pais;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.foto = foto;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getPuesto() {
		return puesto;
	}

	public void getPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public FotoUsuarios getFoto() {
		return foto;
	}

	public void setFoto(FotoUsuarios foto) {
		this.foto = foto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

}
