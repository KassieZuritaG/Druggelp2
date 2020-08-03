package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import com.ortizzurita.druggelp2.models.entities.DetalleReserva;

public interface IDetalleReservaService {

	public void save(DetalleReserva det);
	public DetalleReserva findById(Integer id);
	public void delete(Integer id);
	public List<DetalleReserva> findAll();
}