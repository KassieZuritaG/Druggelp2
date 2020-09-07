package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import com.ortizzurita.druggelp2.models.entities.Reserva;
import com.ortizzurita.druggelp2.models.reporting.RptReservaUsuario;

public interface IReservaService {

	public void save(Reserva res);
	public Reserva findById(Integer id);
	public void delete(Integer id);
	public List<Reserva> findAll();
	public List<Reserva> findByUsuario(Integer id);
	//comentar la linea de codigo de abajo
	//public List<RptReservaUsuario> rptReservaUsuario();

}
