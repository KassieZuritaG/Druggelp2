package com.ortizzurita.druggelp2.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ortizzurita.druggelp2.models.entities.Usuario;

public interface IUsuario extends CrudRepository<Usuario, Integer> {

	public Usuario findByNombre(String nombre);	
}
