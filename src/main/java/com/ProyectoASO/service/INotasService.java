package com.ProyectoASO.service;

import java.util.List;

import com.ProyectoASO.dto.NotasDTO;

public interface INotasService {
	
	public List<NotasDTO> getAll();
	
	public NotasDTO save(NotasDTO note);
	
	public void delete(Integer id);
	
}
