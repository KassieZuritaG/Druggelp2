package com.ortizzurita.druggelp2.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ortizzurita.druggelp2.models.entities.Articulo;

public interface IArticulo extends CrudRepository<Articulo, Integer>{

	public List<Articulo> findByNombreStartingWith(String nombre);
}
