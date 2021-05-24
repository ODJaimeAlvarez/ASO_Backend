package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.EmpleadoDTO;
import com.ProyectoASO.entity.Empleado;
@Component
public class EmpleadoConverter implements IConverter<Empleado, EmpleadoDTO> {

	@Override
	public EmpleadoDTO apply(Empleado t) {
		return new EmpleadoDTO(t.getId(), t.getNombre(), t.getApellido1(), t.getApellido2(), t.getPuesto(),
				t.getUsuario().getActivo(),t.getUsuario().getCorreo(),t.getTelefono(),t.getDireccion(),t.getDescripcion(),t.getFoto());
	}

}
