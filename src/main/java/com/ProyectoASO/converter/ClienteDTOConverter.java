package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.ClienteDTO;
import com.ProyectoASO.entity.Cliente;
@Component
public class ClienteDTOConverter implements IConverter<ClienteDTO, Cliente> {

	@Override
	public Cliente apply(ClienteDTO t) {

		return t.getId() != null
				? new Cliente(t.getId(), t.getNombre(), t.getApellido1(), t.getApellido2(), t.getEmpresa())
				: new Cliente(t.getNombre(), t.getApellido1(), t.getApellido2(), t.getEmpresa());
	}

}
