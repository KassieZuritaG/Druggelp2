package com.ortizzurita.druggelp2.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ortizzurita.druggelp2.models.dao.IUsuario;
import com.ortizzurita.druggelp2.models.entities.Rol;
import com.ortizzurita.druggelp2.models.entities.Usuario;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private IUsuario dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = dao.findByNombre(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario " + username + " no encontrado");
		}
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		for(Rol rol: usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		
		if(roles.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + username + " no tiene roles asignados");
		}			
		return new User(usuario.getNombre(), usuario.getPassword(), usuario.getHabilitado(), true, true, true, roles);		
	}
	
	@Transactional	
	public List<Usuario> findAll(){		
		return (List<Usuario>) dao.findAll();
	}
	
	@Transactional
	public Usuario findById(Integer id) {		
		return dao.findById(id).get();
	}
	
	@Transactional
	public void save(Usuario u) {
		dao.save(u);		
	}

	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);				
	}
}
