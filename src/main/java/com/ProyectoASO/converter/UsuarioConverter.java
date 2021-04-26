package com.ProyectoASO.converter;

import org.springframework.stereotype.Component;

import com.ProyectoASO.dto.UsuarioDTO;
import com.ProyectoASO.entity.Usuario;
@Component
public class UsuarioConverter implements IConverter<Usuario, UsuarioDTO> {

	@Override
	public UsuarioDTO apply(Usuario usuario) {
			return new UsuarioDTO(usuario.getId(),usuario.getNombre(),usuario.getApellido1(),usuario.getApellido2(),usuario.getCorreo(),usuario.getActivo());
	}
	
	

}
