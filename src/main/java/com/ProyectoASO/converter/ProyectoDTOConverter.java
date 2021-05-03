package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.ProyectoDTO;
import com.ProyectoASO.entity.Proyecto;
import com.ProyectoASO.enums.Progreso;
@Component
public class ProyectoDTOConverter implements IConverter<ProyectoDTO, Proyecto> {

	@Override
	public Proyecto apply(ProyectoDTO t) {
		return t.getId()==null ? new Proyecto(t.getNombre(),Progreso.INICIADO,t.getDescripcion()): new Proyecto(t.getId(),t.getNombre(),Progreso.INICIADO,t.getDescripcion());
	}
	
	//TODO Esperando conversor Progreso
	

}
