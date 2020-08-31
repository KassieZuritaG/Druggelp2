package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ortizzurita.druggelp2.models.dao.IFarmaco;
import com.ortizzurita.druggelp2.models.entities.Farmaco;

@Service
public class FarmacoService implements IFarmacoService{

	@Autowired 
	private IFarmaco dao;
	
	@Override
	@Transactional
	public void save(Farmaco fco) {
		dao.save(fco);
	}

	@Override
	@Transactional
	public Farmaco findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);	
	}

	@Override
	@Transactional
	public List<Farmaco> findAll() {
		return (List<Farmaco>) dao.findAll();
	}

	@Override
	@Transactional
	public List<Farmaco> findByNombre(String nombre) {
		return dao.findByNombreStartingWith(nombre);
	}

}
