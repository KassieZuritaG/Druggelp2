package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import com.ortizzurita.druggelp2.models.entities.Reserva;

public interface IReservaService {

	public void save(Reserva res);
	public Reserva findById(Integer id);
	public void delete(Integer id);
	public List<Reserva> findAll();
}
