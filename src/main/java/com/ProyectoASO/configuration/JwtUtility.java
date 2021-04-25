package com.ProyectoASO.configuration;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtility implements Serializable{

	private static final long serialVersionUID = 8901966693934998645L;
	
	public static final long VALIDEZ = 5*60*60;
	
	@Value("${jwt.secret}")
	private String clave;
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(clave).parseClaimsJws(token).getBody();
	}
	/*
	 * Conseguir email del token
	 */
	public String getEmailFronToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	/*
	 * Conseguir fecha de caducidad del token
	 */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	/*
	 * Comprobar si el token a expirado
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	/*
	 * Generar token no los valores recibido de userDetails
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + VALIDEZ * 1000))
				.signWith(SignatureAlgorithm.HS512, clave).compact();
	}
	/*
	 * Validar token
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String email = getEmailFronToken(token);
		return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}