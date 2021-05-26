package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.EmpleadoDTO;
import com.ProyectoASO.entity.Empleado;
@Component
public class EmpleadoDTOConverter implements IConverter<EmpleadoDTO, Empleado> {

	@Override
	public Empleado apply(EmpleadoDTO t) {

		return t.getId() != null
				? new Empleado(t.getId(), t.getNombre(), t.getApellido1(), t.getApellido2(), t.getCargo(),t.getTelefono(),t.getDireccion(),t.getDescripcion(),t.getPais(),t.getCiudad(),t.getCodigoPostal(),t.getFotoPerfil())
				: new Empleado(t.getNombre(), t.getApellido1(), t.getApellido2(),t.getCargo(),t.getTelefono(),t.getDireccion(),t.getDescripcion(),t.getPais(),t.getCiudad(),t.getCodigoPostal(),t.getFotoPerfil());
	}

}
