package com.ProyectoASO.service;

import java.util.List;

import com.ProyectoASO.dto.JornadaDTO;

public interface IJornadaService {
	
	public List<JornadaDTO> getAll();
	
	public List<JornadaDTO> getAllByUser(Integer userID);
	
	public String jornadaManager(String token);
	
	public JornadaDTO getInicioJornada(String token);
	

}
