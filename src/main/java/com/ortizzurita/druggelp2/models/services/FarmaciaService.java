package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ortizzurita.druggelp2.models.dao.IArticulo;
import com.ortizzurita.druggelp2.models.dao.IFarmacia;
import com.ortizzurita.druggelp2.models.entities.Articulo;
import com.ortizzurita.druggelp2.models.entities.Farmacia;

@Service
public class FarmaciaService implements IFarmaciaService{

	@Autowired 
	private  IFarmacia dao;
	
	@Autowired 
	private  IArticulo daoArticulo;
	
	@Override
	@Transactional
	public void save(Farmacia fcia) {
		try {
			dao.save(fcia);
			for(Articulo ar : fcia.getArticulos()) {
				ar.setFarmacia(fcia);
				this.daoArticulo.save(ar);			
			}
		}
		catch(Exception ex) {
			throw ex;
		}
	}

	@Override
	@Transactional
	public Farmacia findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);	
	}

	@Override
	@Transactional
	public List<Farmacia> findAll() {
		return (List<Farmacia>) dao.findAll();
	}

}
