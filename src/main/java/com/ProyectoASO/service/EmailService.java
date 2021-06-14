package com.ProyectoASO.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.ProyectoASO.dto.EmailMessage;

@Service
public class EmailService extends Thread{
	
	private JavaMailSender emailSender;
	
	public EmailService(JavaMailSender emailSender) {
		this.emailSender = emailSender;
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
