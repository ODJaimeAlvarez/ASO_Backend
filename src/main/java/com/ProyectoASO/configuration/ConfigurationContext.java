package com.ProyectoASO.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class ConfigurationContext {
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("email-prop.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		JavaMailSenderImpl mailSender= new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(p.getProperty("mail.user"));
		mailSender.setPassword(p.getProperty("mail.password"));
		Properties prop = mailSender.getJavaMailProperties();
		prop.put("mail.transport.protocol", "smtp");
	    prop.put("mail.smtp.auth", "true");
	    prop.put("mail.smtp.starttls.enable", "true");
	    prop.put("mail.debug", "true");
	    return mailSender;
	}

}
