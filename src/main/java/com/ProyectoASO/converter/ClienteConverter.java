package com.ProyectoASO.converter;

import com.ProyectoASO.dto.ClienteDTO;
import com.ProyectoASO.entity.Cliente;

public class ClienteConverter implements IConverter<Cliente, ClienteDTO> {

	@Override
	public ClienteDTO apply(Cliente t) {
		
		return new ClienteDTO(t.getId(), t.getNombre(), t.getApellido1(), t.getApellido2(), t.getEmpresa());
	}

}
