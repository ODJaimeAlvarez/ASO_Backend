package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.JornadaDTO;
import com.ProyectoASO.entity.Jornada;
@Component
public class JornadaConverter implements IConverter<Jornada, JornadaDTO>{

	@Override
	public JornadaDTO apply(Jornada t) {
		return new JornadaDTO(t.getIdJornada(), t.getFechaJornada(), t.getHoraInicio(), t.getHoraFin());
	}

}
