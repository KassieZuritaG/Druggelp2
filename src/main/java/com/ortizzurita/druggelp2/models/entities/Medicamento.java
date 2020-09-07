package com.ortizzurita.druggelp2.models.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="medicamentos")
public class Medicamento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_medicamento")	
	private Integer idmedicamento;
	
	@JoinColumn(name="fk_farmaco", referencedColumnName="pk_farmaco")
	@ManyToOne
	private  Farmaco farmaco;
	
	@JoinColumn(name="fk_farmacia", referencedColumnName="pk_farmacia")
	@ManyToOne
	private Farmacia farmacia;
	
	@Column(name="ciudad")
	private String ciudad;
	
	public Medicamento() {
		super();
	}

	public Medicamento(Integer id) {
		super();
		this.idmedicamento = id;
	}

	public Integer getIdmedicamento() {
		return idmedicamento;
	}

	public void setIdmedicamento(Integer idmedicamento) {
		this.idmedicamento = idmedicamento;
	}

	public Farmaco getFarmaco() {
		return farmaco;
	}

	public void setFarmaco(Farmaco farmaco) {
		this.farmaco = farmaco;
	}

	public Farmacia getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
/**** TRANSIENT ***/
	
	@Transient
	private int farmacoid;
	
	@Transient
	private int farmaciaid;

	public int getFarmacoid() {
		return farmacoid;
	}

	public void setFarmacoid(int farmacoid) {
		this.farmacoid = farmacoid;
	}

	public int getFarmaciaid() {
		return farmaciaid;
	}

	public void setFarmaciaid(int farmaciaid) {
		this.farmaciaid = farmaciaid;
	}
}
