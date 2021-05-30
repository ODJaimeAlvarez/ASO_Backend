package com.ProyectoASO.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.ProyectoASO.dto.EmailMessage;

@Service
public class EmailService {
	
	private JavaMailSender emailSender;
	
	public EmailService(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	public void sendMessageToRegister(String email, String URL) {
		SimpleMailMessage mensaje= new SimpleMailMessage();
		mensaje.setFrom(((JavaMailSenderImpl)emailSender).getUsername());
		mensaje.setTo(email);
		mensaje.setSubject("Confirma tu registro");
		mensaje.setText("Haz click en este enlace para verificar tu cuenta :" + URL);
		emailSender.send(mensaje);
		
		
	}
	
	public void sendMessage(EmailMessage email) {
		SimpleMailMessage mensaje= new SimpleMailMessage();
		mensaje.setFrom(((JavaMailSenderImpl)emailSender).getUsername());
		mensaje.setTo(((JavaMailSenderImpl)emailSender).getUsername());
		mensaje.setSubject("[Contáctanos PortalASO] "+email.getAsunto());
		mensaje.setText(email.getMensaje()+"\n\nEmail de contacto: "+email.getCorreo());
		emailSender.send(mensaje);
		
		
	}
}
