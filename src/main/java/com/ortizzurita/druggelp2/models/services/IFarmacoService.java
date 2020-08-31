package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import com.ortizzurita.druggelp2.models.entities.Farmaco;

public interface IFarmacoService {

	public void save(Farmaco fco);
	public Farmaco findById(Integer id);
	public void delete(Integer id);
	public List<Farmaco> findAll();
	public List<Farmaco> findByNombre(String nombre);
}
