package com.ortizzurita.druggelp2.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ortizzurita.druggelp2.models.entities.Farmaco;

public interface IFarmaco extends CrudRepository<Farmaco, Integer>{
	
	public List<Farmaco> findByNombreStartingWith(String nombre);
	
}
