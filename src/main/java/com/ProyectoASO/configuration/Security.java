package com.ProyectoASO.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ProyectoASO.jwt.JwtEntryPoint;
import com.ProyectoASO.jwt.JwtFilter;
import com.ProyectoASO.service.LogInService;
import com.ProyectoASO.service.UserService;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {
	@Autowired
	private LogInService logInService;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManagerBean();
	}
	
//	@Bean
//	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(logInService).passwordEncoder(NoOpPasswordEncoder.getInstance());//TODO realizar encriptacion de contrasenyas
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and()//Habilitamos CORS para que el front se pueda comunicar con el Back, desactivamos CSRF
		
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()//Determinamos que el servidor funcione sin sesion
		
		.exceptionHandling().authenticationEntryPoint(new JwtEntryPoint()).and()//Handling de error en la autenticacion
		.authorizeRequests()
			.antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()//Metodos autorizado sin login.
			.and().authorizeRequests()
			.anyRequest().authenticated().and();//todas las demas necesitan token JWT para acceder.
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);//Filtro que permite autenticacion y autorizacion a traves de token JWT
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}
}

