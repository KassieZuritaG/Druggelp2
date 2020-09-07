package com.ortizzurita.druggelp2.models.reporting;

import java.io.Serializable;

public class RptFarmacoFarmacia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nombrefarmacia;
	private String articulo;
	private Integer cantidad;
	
	public RptFarmacoFarmacia() {
		super();
	}

	public RptFarmacoFarmacia(String nombrefarmacia, String articulo, Integer cantidad) {
		super();
		this.nombrefarmacia = nombrefarmacia;
		this.articulo = articulo;
		this.cantidad = cantidad;
	}

	public String getNombrefarmacia() {
		return nombrefarmacia;
	}

	public void setNombrefarmacia(String nombrefarmacia) {
		this.nombrefarmacia = nombrefarmacia;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	
	
}