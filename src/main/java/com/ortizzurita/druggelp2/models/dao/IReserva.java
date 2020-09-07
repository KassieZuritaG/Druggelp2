package com.ortizzurita.druggelp2.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ortizzurita.druggelp2.models.entities.Reserva;

public interface IReserva extends CrudRepository<Reserva, Integer>{
}
