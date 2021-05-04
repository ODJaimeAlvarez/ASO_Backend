package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.FicheroDTO;
import com.ProyectoASO.entity.Fichero;
@Component
public class FicheroConverter implements IConverter<Fichero, FicheroDTO> {
	private ProyectoConverter converterProyecto;
	
	public FicheroConverter(ProyectoConverter converterProyecto) {
		this.converterProyecto = converterProyecto;
	}

	@Override
	public FicheroDTO apply(Fichero t) {
		return new FicheroDTO(t.getId(), t.getNombre_fichero(), t.getFecha_mod(), converterProyecto.convert(t.getProyecto()));
	}

}
