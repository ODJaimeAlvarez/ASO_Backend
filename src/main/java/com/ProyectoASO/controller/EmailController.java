package com.ProyectoASO.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoASO.dto.EmailMessage;
import com.ProyectoASO.service.EmailService;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin("*")
public class EmailController {
	private EmailService emailService;

	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@PostMapping
	public void sendEmailToInfo(@RequestBody EmailMessage message) {
		emailService.sendMessage(message);
	}

}
