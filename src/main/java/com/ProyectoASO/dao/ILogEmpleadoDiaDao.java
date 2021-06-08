package com.ProyectoASO.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoASO.entity.LogEmpleadoDia;

public interface ILogEmpleadoDiaDao extends JpaRepository<LogEmpleadoDia, Integer> {

	public List<LogEmpleadoDia> findByFechaBetween(Date startDate,Date endDate);
 

}
