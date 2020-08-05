package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ortizzurita.druggelp2.models.dao.IReserva;
import com.ortizzurita.druggelp2.models.entities.Reserva;

@Service
public class ReservaService implements IReservaService{

	@Autowired 
	private IReserva dao;
	
	@Override
	@Transactional
	public void save(Reserva res) {
		dao.save(res);
	}

	@Override
	@Transactional
	public Reserva findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Reserva> findAll() {
		return (List<Reserva>) dao.findAll();
	}

	@Override
	public List<Reserva> findByUsuario(Integer id) {
		try {
			List<Reserva> resultado = dao.findByUsuario(id);
			return resultado;
		}catch(Exception ex) {
			System.out.println("Error =>" + ex.getMessage());
			return null;
		}
	}

}
