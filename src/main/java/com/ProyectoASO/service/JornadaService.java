package com.ProyectoASO.service;

import java.sql.Time;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ProyectoASO.dao.IJornadaDao;
import com.ProyectoASO.dto.JornadaDTO;
import com.ProyectoASO.entity.Jornada;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.jwt.JwtUtility;
import com.ProyectoASO.jwt.TokenDetails;
@Service
public class JornadaService extends BaseService implements IJornadaService {

	private IJornadaDao jornadaDao;
	private UserService usuarioService;
	private JwtUtility jwtUtils;

	
	public JornadaService(TokenDetails token, IJornadaDao jornadaDao,
			UserService usuarioService, JwtUtility jwtUtils) {
		super(token);
		this.jornadaDao = jornadaDao;
		this.usuarioService = usuarioService;
		this.jwtUtils = jwtUtils;
	}

	@Override
	public List<JornadaDTO> getAll() {
		checkAuthority(List.of("DIRECTOR"));
		final List<JornadaDTO> listFormatted= new ArrayList();
		for(Jornada jor: jornadaDao.findAll()) {
			listFormatted.add(new JornadaDTO(jor.getFechaJornada(), jor.getHoraInicio().toString(), (jor.getFechaJornada()!= null ? jor.getHoraInicio().toString() : "-"), null));
		}
		return listFormatted;
	}

	@Override
	public List<JornadaDTO> getAllByUser(Integer userID) {
		checkAuthority(List.of("DIRECTOR"));
		Usuario userFind = usuarioService.getUserByIdEntity(userID);
		final List<Jornada> listJornada= jornadaDao.findByUser(userFind);
		final List<JornadaDTO> listJornadaResul=new ArrayList<>();
		
		for(Jornada jor : listJornada) {
			if(jor.getHoraFin()!=null) {
				Long timediffLong=jor.getHoraFin().getTime()-jor.getHoraInicio().getTime();
				double timediff=((double)Math.round((double)timediffLong/36000)/100);
				int horas= (int)timediff;
				int minutos= ((int)Math.round(((double)((double)timediff-horas)*60)));
				String total=horas+"h y "+minutos+"min";
				listJornadaResul.add(new JornadaDTO(jor.getFechaJornada(),jor.getHoraInicio().toString(),jor.getHoraFin().toString(),total));
			}else {
				listJornadaResul.add(new JornadaDTO(jor.getFechaJornada(),jor.getHoraInicio().toString(),"-","-"));
			}
		}
		return listJornadaResul;
	}

	@Override
	public String jornadaManager(String token) {
		checkAuthority(List.of("EMPLEADO","DIRECTOR"));
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
		checkAuthority(List.of("EMPLEADO","DIRECTOR"));
		String email = jwtUtils.getEmailFronToken(token.substring(7));
		Usuario user = usuarioService.buscarPorcorreo(email);
		Jornada jor = jornadaDao.findByUserAndIniciada(user, true).orElse(null); 
		return jor != null ? new JornadaDTO(jor.getFechaJornada(), jor.getHoraInicio().toString(), (jor.getFechaJornada()!= null ? jor.getHoraInicio().toString() : "-"), "-") : null ;
	}

}
