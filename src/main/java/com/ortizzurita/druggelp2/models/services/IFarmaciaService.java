package com.ortizzurita.druggelp2.models.services;

import java.util.List;

import com.ortizzurita.druggelp2.models.entities.Farmacia;
import com.ortizzurita.druggelp2.models.reporting.RptFarmacoFarmacia;

public interface IFarmaciaService {

	public void save(Farmacia fcia);
	public Farmacia findById(Integer id);
	public void delete(Integer id);
	public List<Farmacia> findAll();
	public List<RptFarmacoFarmacia> rptFarmacoFarmacia();
}
