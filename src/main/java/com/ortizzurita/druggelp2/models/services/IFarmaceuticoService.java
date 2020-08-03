package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import com.ortizzurita.druggelp2.models.entities.Farmaceutico;

public interface IFarmaceuticoService {

	public void save(Farmaceutico ftico);
	public Farmaceutico findById(Integer id);
	public void delete(Integer id);
	public List<Farmaceutico> findAll();
}
