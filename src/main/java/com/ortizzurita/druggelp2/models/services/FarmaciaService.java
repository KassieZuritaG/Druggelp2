package com.ortizzurita.druggelp2.models.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ortizzurita.druggelp2.models.dao.IArticulo;
import com.ortizzurita.druggelp2.models.dao.IFarmacia;
import com.ortizzurita.druggelp2.models.entities.Articulo;
import com.ortizzurita.druggelp2.models.entities.Farmacia;
import com.ortizzurita.druggelp2.models.reporting.RptFarmacoFarmacia;
import com.ortizzurita.druggelp2.models.reporting.RptFarmacoPrecio;

@Service
public class FarmaciaService implements IFarmaciaService{

	@Autowired 
	private  IFarmacia dao;
	
	@Autowired 
	private  IArticulo daoArticulo;
	
////Es la instancia de persistencia con la BDD
	@PersistenceContext
	private EntityManager em;
	
	@PersistenceContext
	private EntityManager em2;
	
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
	
	@Override
	public List<RptFarmacoFarmacia> rptFarmacoFarmacia() {
		StoredProcedureQuery query = em.createStoredProcedureQuery("articulos_por_farmacia");
		query.execute();
		List<Object[]> datos = query.getResultList();		
		return datos.stream()
				.map(r -> new RptFarmacoFarmacia((String)r[0], (String)r[1], (Integer)r[2]))
				.collect(Collectors.toList());	
	}

	@Override
	public List<RptFarmacoPrecio> rptFarmacoPrecio() {
		StoredProcedureQuery query = em2.createStoredProcedureQuery("farmacos_por_precio");
		query.execute();
		List<Object[]> datos = query.getResultList();		
		return datos.stream()
				.map(r -> new RptFarmacoPrecio((String)r[0], (String)r[1], (Float)r[2]))
				.collect(Collectors.toList());	
	}

}
