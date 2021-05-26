package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.NotasDTO;
import com.ProyectoASO.entity.Notas;
@Component
public class NotasDTOConverter implements IConverter<NotasDTO, Notas> {

	@Override
	public Notas apply(NotasDTO t) {
		return t.getId() != null ? new Notas(t.getId(), t.getNombre(), t.getNota())
				: new Notas(t.getNombre(), t.getNota());
	}

}
