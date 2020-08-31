package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ortizzurita.druggelp2.models.dao.IMedicamento;
import com.ortizzurita.druggelp2.models.entities.Medicamento;

@Service
public class MedicamentoService implements IMedicamentoService{

	@Autowired
	private IMedicamento dao;
	
	@Override
	@Transactional
	public void save(Medicamento m) {
		dao.save(m);
	}

	@Override
	@Transactional
	public Medicamento findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Medicamento> findAll() {
		return (List<Medicamento>) dao.findAll();
	}

}
