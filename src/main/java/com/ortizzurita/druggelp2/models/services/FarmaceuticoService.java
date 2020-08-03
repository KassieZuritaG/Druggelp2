package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ortizzurita.druggelp2.models.dao.IFarmaceutico;
import com.ortizzurita.druggelp2.models.entities.Farmaceutico;

@Service
public class FarmaceuticoService implements IFarmaceuticoService{

	@Autowired 
	private IFarmaceutico dao;
	
	@Override
	@Transactional
	public void save(Farmaceutico ftico) {
		dao.save(ftico);
	}

	@Override
	@Transactional
	public Farmaceutico findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);	
	}

	@Override
	@Transactional
	public List<Farmaceutico> findAll() {
		return (List<Farmaceutico>) dao.findAll();
	}

}
