package com.ortizzurita.druggelp2.models.services;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ortizzurita.druggelp2.models.dao.IDetalleReserva;
import com.ortizzurita.druggelp2.models.dao.IMedicamento;
import com.ortizzurita.druggelp2.models.dao.IReserva;
import com.ortizzurita.druggelp2.models.entities.Reserva;
import com.ortizzurita.druggelp2.models.reporting.RptFarmacoPrecio;
import com.ortizzurita.druggelp2.models.reporting.RptFarmacoReserva;
import com.ortizzurita.druggelp2.models.reporting.RptReservaUsuario;
import com.ortizzurita.druggelp2.models.entities.DetalleReserva;
import com.ortizzurita.druggelp2.models.entities.Medicamento;

@Service
public class ReservaService implements IReservaService{

	@Autowired 
	private IReserva dao;
	
	@Autowired 
	private IDetalleReserva daoMedicamento;
	
////Es la instancia de persistencia con la BDD
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void save(Reserva res) {
		try {
			dao.save(res);
			for(DetalleReserva dt: res.getFarmacos()){
				dt.setReserva(res);
				this.daoMedicamento.save(dt);
			}	
		}catch(Exception ex) {
			throw ex;
		}
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
	public List<RptFarmacoReserva> rptFarmacoReserva() {
		StoredProcedureQuery query = em.createStoredProcedureQuery("reservas_por_meses");
		query.execute();
		List<Object[]> datos = query.getResultList();		
		return datos.stream()
				.map(r -> new RptFarmacoReserva((String)r[0], (String)r[1], (BigInteger)r[2]))
				.collect(Collectors.toList());	
	}

	//comentar esto
	/*@Override
	public List<RptReservaUsuario> rptReservaUsuario() {
		StoredProcedureQuery query = em.createStoredProcedureQuery("reservas_por_usuario");
		query.execute();
		List<Object[]> datos = query.getResultList();		
		return datos.stream()
				.map(r -> new RptReservaUsuario((String)r[0], (BigInteger)r[1]))
				.collect(Collectors.toList());	
	}*/
	/////


}
