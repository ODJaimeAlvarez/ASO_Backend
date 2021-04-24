package com.ProyectoASO.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ProyectoASO.dao.IUsuarioDao;
import com.ProyectoASO.dto.UsuarioDTO;
import com.ProyectoASO.entity.Usuario;
@Service
public class UserService implements IUserService,UserDetailsService {
	@Autowired
	IUsuarioDao usuarioRepository;

	@Override
	public List<UsuarioDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDTO getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDTO saveUser(UsuarioDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDTO updateUser(Long id, UsuarioDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user= usuarioRepository.findByCorreo(username);
		return new User(user.getCorreo(),user.getPasswd(),new ArrayList<>());
	}

}
