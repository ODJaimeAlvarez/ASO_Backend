package com.ProyectoASO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ProyectoASO.dao.IRolDao;
import com.ProyectoASO.dao.IUsuarioDao;
import com.ProyectoASO.entity.Rol;
import com.ProyectoASO.entity.Usuario;

@SpringBootTest
class AsoBackendApplicationTests {
	@Autowired
	IUsuarioDao userRepo;

	@Autowired
	IRolDao rolRepo;
	
	@Test
	void contextLoads() {
		userRepo.save(new Usuario("a","a","a","a",false,"a"));
		rolRepo.save(new Rol("Hola"));
		
	}

}
