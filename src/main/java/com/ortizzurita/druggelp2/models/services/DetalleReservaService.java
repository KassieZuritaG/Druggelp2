package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ortizzurita.druggelp2.models.dao.IDetalleReserva;
import com.ortizzurita.druggelp2.models.entities.DetalleReserva;

@Service
public class DetalleReservaService implements IDetalleReservaService{

	@Autowired 
	private IDetalleReserva dao;
	
	@Override
	@Transactional
	public void save(DetalleReserva det) {
		dao.save(det);		
	}

	@Override
	@Transactional
	public DetalleReserva findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);	
	}

	@Override
	@Transactional
	public List<DetalleReserva> findAll() {
		return (List<DetalleReserva>) dao.findAll();
	}
	
}
