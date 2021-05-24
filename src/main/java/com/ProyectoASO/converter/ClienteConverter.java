package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.ClienteDTO;
import com.ProyectoASO.entity.Cliente;
@Component
public class ClienteConverter implements IConverter<Cliente, ClienteDTO> {

	@Override
	public ClienteDTO apply(Cliente t) {
		
		return new ClienteDTO(t.getId(), t.getNombre(), t.getApellido1(), t.getApellido2(), t.getEmpresa(),
				t.getUsuario().getActivo(),t.getUsuario().getCorreo(),t.getTelefono(),t.getDireccion(),t.getFoto());
	}

}
