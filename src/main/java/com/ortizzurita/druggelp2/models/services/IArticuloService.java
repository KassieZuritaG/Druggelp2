package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import com.ortizzurita.druggelp2.models.entities.Articulo;

public interface IArticuloService {

	public void save(Articulo ar);
	public Articulo findById(Integer id);
	public void delete(Integer id);
	public List<Articulo>findAll();
	public List<Articulo> findByNombre(String nombre);
}
