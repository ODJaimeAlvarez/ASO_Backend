package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.NotasDTO;
import com.ProyectoASO.entity.Notas;
@Component
public class NotasConverter implements IConverter<Notas, NotasDTO> {

	@Override
	public NotasDTO apply(Notas t) {
		
		return new NotasDTO(t.getId(), t.getNombre(), t.getNota());
	}
	

}
