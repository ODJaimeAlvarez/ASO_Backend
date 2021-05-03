package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.ProyectoDTO;
import com.ProyectoASO.entity.Proyecto;
import com.ProyectoASO.enums.Progreso;
@Component
public class ProyectoDTOConverter implements IConverter<ProyectoDTO, Proyecto> {
	private ProgresoEnumBDConverter converterEnum;
	

	public ProyectoDTOConverter(ProgresoEnumBDConverter converterEnum) {
		this.converterEnum = converterEnum;
	}


	@Override
	public Proyecto apply(ProyectoDTO t) {
		return t.getId()==null ? new Proyecto(t.getNombre(),converterEnum.convertToEntityAttribute(t.getEstado().getCode()),t.getDescripcion()) 
				: new Proyecto(t.getId(),t.getNombre(),converterEnum.convertToEntityAttribute(t.getEstado().getCode()),t.getDescripcion());
	}
	
}
