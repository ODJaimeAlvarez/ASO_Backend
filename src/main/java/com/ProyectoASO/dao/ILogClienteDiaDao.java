package com.ProyectoASO.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoASO.entity.LogClienteDia;

public interface ILogClienteDiaDao extends JpaRepository<LogClienteDia, Integer> {

	public List<LogClienteDia> findByFechaBetween(Date startDate,Date endDate);
 

}
