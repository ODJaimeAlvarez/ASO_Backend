package com.ProyectoASO.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ProyectoASO.dto.JornadaDTO;
import com.ProyectoASO.responses.JornadaManagerResponse;

public interface IJornadaService {
	
	public List<JornadaDTO> getAll();
	
	public List<JornadaDTO> getAllByUser(Integer userID);
	
	public ResponseEntity<JornadaManagerResponse> jornadaManager(String token);
	
	public JornadaDTO getInicioJornada(String token);
	

}
