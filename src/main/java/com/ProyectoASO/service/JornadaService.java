package com.ProyectoASO.service;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ProyectoASO.dao.IJornadaDao;
import com.ProyectoASO.dto.JornadaDTO;
import com.ProyectoASO.entity.Empleado;
import com.ProyectoASO.entity.Jornada;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.jwt.JwtUtility;
import com.ProyectoASO.jwt.TokenDetails;
import com.ProyectoASO.responses.JornadaManagerResponse;
import com.ProyectoASO.responses.MethodResponse;

import javassist.expr.NewArray;

@Service
public class JornadaService extends BaseService implements IJornadaService {

	private IJornadaDao jornadaDao;
	private final EmpleadoService empleadoService;
	private UserService usuarioService;
	private JwtUtility jwtUtils;

	public JornadaService(TokenDetails token, IJornadaDao jornadaDao, UserService usuarioService, JwtUtility jwtUtils,
			EmpleadoService empleadoService) {
		super(token);
		this.jornadaDao = jornadaDao;
		this.usuarioService = usuarioService;
		this.jwtUtils = jwtUtils;
		this.empleadoService = empleadoService;
	}

	@Override
	public List<JornadaDTO> getAll() {
		checkAuthority(List.of("DIRECTOR"));
		final List<JornadaDTO> listFormatted = new ArrayList();
		for (Jornada jor : jornadaDao.findAll()) {
			listFormatted.add(new JornadaDTO(jor.getFechaJornada(), jor.getHoraInicio().toString(),
					(jor.getFechaJornada() != null ? jor.getHoraInicio().toString() : "-"), null));
		}
		return listFormatted;
	}

	@Override
	public List<JornadaDTO> getAllByUser(Integer userID) {
		checkAuthority(List.of("DIRECTOR"));
		Usuario userFind = usuarioService.getUserByIdEntity(userID);
		Empleado emp = empleadoService.getEmpleadoByUser(userFind);
		final List<Jornada> listJornada = jornadaDao.findByEmpleado(emp);
		final List<JornadaDTO> listJornadaResul = new ArrayList<>();

		for (Jornada jor : listJornada) {
			if (jor.getHoraFin() != null) {
				Long timediffLong = jor.getHoraFin().getTime() - jor.getHoraInicio().getTime();
				double timediff = ((double) Math.round((double) timediffLong / 36000) / 100);
				int horas = (int) timediff;
				int minutos = ((int) Math.round(((double) ((double) timediff - horas) * 60)));
				String total = horas + "h y " + minutos + "min";
				listJornadaResul.add(new JornadaDTO(jor.getIdJornada(), jor.getFechaJornada(),
						jor.getHoraInicio().toString(), jor.getHoraFin().toString(), total));
			} else {
				listJornadaResul.add(new JornadaDTO(jor.getIdJornada(), jor.getFechaJornada(),
						jor.getHoraInicio().toString(), "-", "-"));
			}
		}
		return listJornadaResul;
	}

	@Override
	public ResponseEntity<JornadaManagerResponse> jornadaManager(String token) {
		checkAuthority(List.of("EMPLEADO", "DIRECTOR"));
		String email = jwtUtils.getEmailFronToken(token.substring(7));
		Usuario user = usuarioService.buscarPorcorreo(email);
		Empleado emp = empleadoService.getEmpleadoByUser(user);
		Optional<Jornada> findJornada = jornadaDao.findByEmpleadoAndIniciada(emp, true);
		Jornada jornada;
		String estado;
		if (findJornada.isPresent()) {
			// Cierra Jornada
			jornada = findJornada.get();
			jornada.setHoraFin(Time.valueOf(LocalTime.now()));
			jornada.setIniciada(false);
			estado = "La jornada ha sido finalizada.";
		} else {
			// Abre Jornada
			jornada = new Jornada(Date.from(Instant.now()), Time.valueOf(LocalTime.now()), null, emp);
			jornada.setIniciada(true);
			estado = "La jornada ha sido iniciada.";
		}
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
		Jornada jor = jornadaDao.save(jornada);
		if (jor.getHoraFin() != null)
			return new ResponseEntity<>(new JornadaManagerResponse(estado, formatter.format(jor.getFechaJornada()),
					jor.getHoraInicio().toString(), jor.getHoraFin().toString()), HttpStatus.OK);
		else
			return new ResponseEntity<>(new JornadaManagerResponse(estado, formatter.format(jor.getFechaJornada()),
					jor.getHoraInicio().toString(), "-"), HttpStatus.OK);
	}

	@Override
	public JornadaDTO getInicioJornada(String token) {
		checkAuthority(List.of("EMPLEADO", "DIRECTOR"));
		String email = jwtUtils.getEmailFronToken(token.substring(7));
		Usuario user = usuarioService.buscarPorcorreo(email);
		Empleado emp = empleadoService.getEmpleadoByUser(user);
		Jornada jor = jornadaDao.findByEmpleadoAndIniciada(emp, true).orElse(null);
		return jor != null
				? new JornadaDTO(jor.getIdJornada(), jor.getFechaJornada(), jor.getHoraInicio().toString(),
						(jor.getFechaJornada() != null ? jor.getHoraInicio().toString() : "-"), "-")
				: null;
	}

}
