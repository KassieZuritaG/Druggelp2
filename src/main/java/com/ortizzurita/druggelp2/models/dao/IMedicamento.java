package com.ortizzurita.druggelp2.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.ortizzurita.druggelp2.models.entities.Medicamento;

public interface IMedicamento extends CrudRepository<Medicamento, Integer>{
	
}
