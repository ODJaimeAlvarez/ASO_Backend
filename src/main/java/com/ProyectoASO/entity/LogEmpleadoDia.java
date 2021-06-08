package com.ProyectoASO.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log_cliente_dia")
public class LogEmpleadoDia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_log_empleado_dia")
	private Integer id;
	@Column(name = "fecha", nullable = false)
	private Date fecha;
	@Column(name = "cantidad_clientes", nullable = false)
	private Integer clientes;

	public LogEmpleadoDia() {
	}

	public LogEmpleadoDia(Integer id, Date fecha_mod, Integer clientes) {
		this.id = id;
		this.fecha = fecha_mod;
		this.clientes = clientes;
	}

	public Integer getId() {
		return id;
	}

	public Date getFecha() {
		return fecha;
	}

	public Integer getClientes() {
		return clientes;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setClientes(Integer clientes) {
		this.clientes = clientes;
	}

}
