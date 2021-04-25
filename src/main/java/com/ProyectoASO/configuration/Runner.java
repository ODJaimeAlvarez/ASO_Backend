package com.ProyectoASO.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ProyectoASO.dao.IUsuarioDao;
import com.ProyectoASO.entity.Usuario;

@Configuration
public class Runner {

	@Autowired
	IUsuarioDao user;
	
	@Profile("Testing")
	@Bean
	public ApplicationRunner meterUser() {

		return arg -> meterUsuarioRunner();
	}

	private void meterUsuarioRunner() {

		List<Usuario> list_user = new ArrayList<>();

		list_user.add(new Usuario("Martos", "Lopez", "adri@hotmail.com", "uwuwu", true, "Adrian"));
		list_user.add(new Usuario("De Jaime", "Alvarez", "oscar@hotmail.com", "uwito", true, "Oscar"));
		list_user.add(new Usuario("Pelayo", "Mellas", "salma@hotmail.com", "awitademadrid", true, "Salma"));
		list_user.add(new Usuario("Garcia", "Muro", "jose@hotmail.com", "shreckriko", true, "Jose"));
		list_user.add(new Usuario("Perez", "Gonzales", "david@hotmail.com", "violinistacion", true, "David"));

		user.saveAll(list_user);

	}

}
