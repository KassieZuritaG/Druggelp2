package com.ortizzurita.druggelp2.models.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="articulos")
public class Articulo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_articulo")	
	private Integer idarticulo;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
	@JoinColumn(name="fk_farmaco", referencedColumnName="pk_farmaco")
	@ManyToOne
	private Farmaco farmaco;
	
	@JoinColumn(name="fk_farmacia", referencedColumnName="pk_farmacia")
	@ManyToOne
	private Farmacia farmacia;

	public Articulo() {
		super();
	}

	public Articulo(Integer idarticulo) {
		super();
		this.idarticulo = idarticulo;
	}
	
	
	public Integer getIdarticulo() {
		return idarticulo;
	}

	public void setIdarticulo(Integer idarticulo) {
		this.idarticulo = idarticulo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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
