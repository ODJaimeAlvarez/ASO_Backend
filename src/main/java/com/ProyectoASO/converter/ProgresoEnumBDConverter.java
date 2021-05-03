package com.ProyectoASO.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.enums.Progreso;
@Component
@Converter(autoApply = true)
public class ProgresoEnumBDConverter implements AttributeConverter<Progreso,String> {

	@Override
	public String convertToDatabaseColumn(Progreso attribute) {
		if(attribute==null) {
			return null;
		}
		return attribute.getCodigo_bd();
	}

	@Override
	public Progreso convertToEntityAttribute(String dbData) {
		if(dbData==null) {
			return null;
		}
		return Stream.of(Progreso.values()).filter(f -> f.getCodigo_bd().equals(dbData)).findFirst().get();
	}

}
