package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ortizzurita.druggelp2.models.dao.ICliente;
import com.ortizzurita.druggelp2.models.entities.Cliente;

@Service
public class ClienteService implements IClienteService{

	@Autowired 
	private ICliente dao;
	
	@Override
	@Transactional
	public void save(Cliente cli) {
		dao.save(cli);
	}

	@Override
	@Transactional
	public Cliente findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);	
	}

	@Override
	@Transactional
	public List<Cliente> findAll() {
		return (List<Cliente>) dao.findAll();
	}

}
