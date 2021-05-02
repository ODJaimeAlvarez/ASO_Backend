package com.ProyectoASO.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ProyectoASO.entity.Usuario;
@Service
public class LogInService implements UserDetailsService {
	@Autowired
	IUserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user= userService.buscarPorcorreo(email);
		
		return new User(user.getCorreo(),user.getPasswd(),new ArrayList<>());
	}
}
