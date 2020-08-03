package com.ortizzurita.druggelp2.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ortizzurita.druggelp2.models.entities.Cliente;

public interface ICliente extends CrudRepository<Cliente, Integer>{

}
