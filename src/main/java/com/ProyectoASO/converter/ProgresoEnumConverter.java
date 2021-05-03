package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.ProgresoDTO;
import com.ProyectoASO.enums.Progreso;
@Component
public class ProgresoEnumConverter implements IConverter<Progreso, ProgresoDTO>{

	@Override
	public ProgresoDTO apply(Progreso t) {
		return new ProgresoDTO(t.getEstado(),t.getCodigo_bd());
	}

}
