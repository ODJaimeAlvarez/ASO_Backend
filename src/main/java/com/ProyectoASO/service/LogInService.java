package com.ProyectoASO.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ProyectoASO.entity.Rol;
import com.ProyectoASO.entity.Usuario;
import com.ProyectoASO.exceptions.AuthoritiesException;
@Service
public class LogInService implements UserDetailsService {
	@Autowired
	IUserService userService;
	
	@Autowired
	IRolUsuarioService rolUserService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user= userService.buscarPorcorreo(email);
		
		if(!user.getActivo())
			throw new AuthoritiesException("El usuario se encuentra deshabilitado, verifique su cuenta o hable con el administador del sitio web.", HttpStatus.FORBIDDEN);
		List<SimpleGrantedAuthority> sga= new ArrayList<>();
		
		List<Rol> roles= rolUserService.getRolesByUser(user);
		
		for(Rol r : roles) {
			sga.add(new SimpleGrantedAuthority(r.getRol()));
		}
		
		return new User(user.getCorreo(),user.getPasswd(),sga);
	}
}
