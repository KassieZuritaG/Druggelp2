package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import com.ortizzurita.druggelp2.models.entities.Medicamento;

public interface IMedicamentoService {
	
	public void save(Medicamento m);
	public Medicamento findById(Integer id);
	public void delete(Integer id);
	public List<Medicamento>findAll();
	
}
