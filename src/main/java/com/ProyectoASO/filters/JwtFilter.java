package com.ProyectoASO.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.Filter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ProyectoASO.configuration.JwtUtility;
import com.ProyectoASO.service.LogInService;
import com.ProyectoASO.service.UserService;

public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	JwtUtility jwtUtil;
	@Autowired
	LogInService logInService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String cabeceraAuth= request.getHeader("Authorization");
		System.out.println("do Filter..........................................");
		String email= null;
		String jwt = null;
		
		if(cabeceraAuth!=null && cabeceraAuth.startsWith("Bearer ")) {
			jwt= cabeceraAuth.substring(7);
			email = jwtUtil.getEmailFronToken(jwt);
		}
		if(email!= null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails user = logInService.loadUserByUsername(email);
			if(jwtUtil.validateToken(jwt, user)) {
				UsernamePasswordAuthenticationToken upat= new UsernamePasswordAuthenticationToken(jwt, null, user.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upat);
			}
			filterChain.doFilter(request, response);
		}
	}
	

}
