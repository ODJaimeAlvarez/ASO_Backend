package com.ProyectoASO.service;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ProyectoASO.converter.JornadaConverter;
import com.ProyectoASO.dao.IJornadaDao;
import com.ProyectoASO.dto.JornadaDTO;
import com.ProyectoASO.entity.Jornada;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.jwt.JwtUtility;
@Service
public class JornadaService implements IJornadaService {

	private IJornadaDao jornadaDao;
	private JornadaConverter jornadaConverter;
	private UserService usuarioService;
	private JwtUtility jwtUtils;

	public JornadaService(IJornadaDao jornadaDao, JornadaConverter jornadaConverter, UserService usuarioService,JwtUtility jwtUtils) {
		this.jornadaDao = jornadaDao;
		this.jornadaConverter = jornadaConverter;
		this.usuarioService = usuarioService;
		this.jwtUtils=jwtUtils;
	}

	@Override
	public List<JornadaDTO> getAll() {
		return jornadaConverter.convert(jornadaDao.findAll());
	}

	@Override
	public List<JornadaDTO> getAllByUser(Integer userID) {
		Usuario userFind = usuarioService.getUserByIdEntity(userID);
		return jornadaConverter.convert(jornadaDao.findByUser(userFind));
	}

	@Override
	public String jornadaManager(String token) {
		String  email = jwtUtils.getEmailFronToken(token.substring(7));
		Usuario user = usuarioService.buscarPorcorreo(email);
		Optional<Jornada> findJornada = jornadaDao.findByUserAndIniciada(user, true);
		Jornada jornada;
		String estado;
		if (findJornada.isPresent()) {
			// Cierra Jornada
			jornada = findJornada.get();
			jornada.setHoraFin(Time.valueOf(LocalTime.now()));
			jornada.setIniciada(false);
			estado="La jornada ha sido finalizada.";
		} else {
			// Abre Jornada
			jornada = new Jornada(Date.from(Instant.now()), Time.valueOf(LocalTime.now()), null, user);
			jornada.setIniciada(true);
			estado="La jornada ha sido iniciada.";
		}
		jornadaDao.save(jornada);
		return estado;
	}

	@Override
	public JornadaDTO getInicioJornada(String token) {
		String email = jwtUtils.getEmailFronToken(token.substring(7));
		Usuario user = usuarioService.buscarPorcorreo(email);
		return jornadaConverter.convert(jornadaDao.findByUserAndIniciada(user, true).orElse(null));
	}

}
