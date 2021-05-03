package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.ProyectoDTO;
import com.ProyectoASO.entity.Proyecto;
@Component
public class ProyectoConverter implements IConverter<Proyecto, ProyectoDTO>{
	
	private ProgresoEnumConverter converterEnum;
	
	
	public ProyectoConverter(ProgresoEnumConverter converterEnum) {
		this.converterEnum = converterEnum;
	}


	@Override
	public ProyectoDTO apply(Proyecto t) {
		return new ProyectoDTO(t.getId(),t.getNombre_proyecto(),converterEnum.convert(t.getProgreso()), t.getDescripcion());
	}

}
