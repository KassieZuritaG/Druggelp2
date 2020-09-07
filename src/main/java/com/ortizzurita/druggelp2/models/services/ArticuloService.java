package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ortizzurita.druggelp2.models.dao.IArticulo;
import com.ortizzurita.druggelp2.models.entities.Articulo;

@Service
public class ArticuloService implements IArticuloService{

	@Autowired
	private IArticulo dao;
	
	@Override
	@Transactional
	public void save(Articulo ar) {
		dao.save(ar);
	}

	@Override
	@Transactional
	public Articulo findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Articulo> findAll() {
		return (List<Articulo>) dao.findAll();
	}

	
}
