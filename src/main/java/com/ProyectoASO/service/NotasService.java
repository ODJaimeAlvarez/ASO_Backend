package com.ProyectoASO.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ProyectoASO.converter.NotasConverter;
import com.ProyectoASO.converter.NotasDTOConverter;
import com.ProyectoASO.dao.INotasDao;
import com.ProyectoASO.dto.NotasDTO;
import com.ProyectoASO.entity.Notas;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.jwt.TokenDetails;

@Service
public class NotasService extends BaseService implements INotasService {

	private UserService userService;
	private INotasDao notasDao;
	private NotasConverter converter;
	private NotasDTOConverter converterDTO;

	public NotasService(TokenDetails token, UserService userService, INotasDao notasDao, NotasConverter converter,
			NotasDTOConverter converterDTO) {
		super(token);
		this.userService = userService;
		this.notasDao = notasDao;
		this.converter = converter;
		this.converterDTO = converterDTO;
	}

	@Override
	public List<NotasDTO> getAll() {
		Usuario user = userService.buscarPorcorreo(getToken().getEmail());
		return converter.convert(notasDao.findByUsuario(user));
	}

	@Override
	public NotasDTO save(NotasDTO note) {
		Usuario user = userService.buscarPorcorreo(getToken().getEmail());
		Notas noteSave = converterDTO.convert(note);
		noteSave.setUsuario(user);
		return converter.convert(notasDao.save(noteSave));
	}

	@Override
	public void delete(Integer id) {
		notasDao.deleteById(id);
	}

}
