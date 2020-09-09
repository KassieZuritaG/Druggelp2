package com.ortizzurita.druggelp2.models.services;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ortizzurita.druggelp2.models.dao.IDetalleReserva;

import com.ortizzurita.druggelp2.models.dao.IReserva;
import com.ortizzurita.druggelp2.models.entities.Reserva;
import com.ortizzurita.druggelp2.models.entities.DetalleReserva;
/*import com.ortizzurita.druggelp2.models.reporting.RptReservaUsuario;
import com.ortizzurita.druggelp2.models.dao.IMedicamento;
import com.ortizzurita.druggelp2.models.entities.Medicamento;*/

@Service
public class ReservaService implements IReservaService{

	@Autowired 
	private IReserva dao;
	
	/*@Autowired 
	private IMedicamento daoMedicamento;*/
	
	@Autowired 
	private IDetalleReserva daoDetalleReserva;
	
////Es la instancia de persistencia con la BDD
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void save(Reserva res) {
		try {
			dao.save(res);
			for(DetalleReserva dr: res.getDetallereservas()){
				dr.setReserva(res);
				this.daoDetalleReserva.save(dr);
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
	public List<Reserva> findByUsuario(Integer id) {
		try {
			List<Reserva> resultado = dao.findByUsuario(id);
			return resultado;
		}catch(Exception ex) {
			System.out.println("Error =>" + ex.getMessage());
			return null;
		}
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
