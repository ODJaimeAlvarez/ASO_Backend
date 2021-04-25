package com.ProyectoASO.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.Filter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ProyectoASO.configuration.JwtUtility;
import com.ProyectoASO.service.UserService;

public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	JwtUtility jwtUtil;
	@Autowired
	UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String cabeceraAuth= request.getHeader("Authorization");
		
		String email= null;
		String jwt = null;
		
		if(cabeceraAuth!=null && cabeceraAuth.startsWith("Bearer ")) {
			jwt= cabeceraAuth.substring(7);
			email = jwtUtil.getEmailFronToken(jwt);
		}
		if(email!= null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails user = userService.loadUserByUsername(email);
			
		}
	}
	

}
