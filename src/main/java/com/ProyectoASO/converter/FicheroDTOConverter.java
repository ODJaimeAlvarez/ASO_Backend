package com.ProyectoASO.converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.FicheroDTO;
import com.ProyectoASO.entity.Fichero;
@Component
public class FicheroDTOConverter implements IConverter<FicheroDTO, Fichero>{
	@Value("${files.route}")
	String ruta;
	
	private ProyectoDTOConverter conveterProyectoDTO;
	
	
	public FicheroDTOConverter(ProyectoDTOConverter conveterProyectoDTO) {
		this.conveterProyectoDTO = conveterProyectoDTO;
	}


	@Override
	public Fichero apply(FicheroDTO t) {
		return t.getId()!=null ? new Fichero(ruta+"\\"+t.getProyecto().getNombre(), t.getName(), t.getFechaMod(), conveterProyectoDTO.convert(t.getProyecto())):
			new Fichero(t.getId(),ruta+"\\"+t.getProyecto().getNombre(), t.getName(), t.getFechaMod(), conveterProyectoDTO.convert(t.getProyecto()));
	}

}
