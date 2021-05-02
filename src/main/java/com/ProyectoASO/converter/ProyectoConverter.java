package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.ProyectoDTO;
import com.ProyectoASO.entity.Proyecto;
@Component
public class ProyectoConverter implements IConverter<Proyecto, ProyectoDTO>{
//TODO esperando ProgresoDTO y conversor
	@Override
	public ProyectoDTO apply(Proyecto t) {
		return new ProyectoDTO(t.getId(),t.getNombre_proyecto(), t.getDescripcion());
	}

}
