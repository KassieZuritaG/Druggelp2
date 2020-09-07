package com.ortizzurita.druggelp2.models.reporting;

import java.io.Serializable;

public class RptFarmacoPrecio implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombrefarmacia;
	private String articulo;
	private Float precio;
	
	public RptFarmacoPrecio() {
		super();
	}

	public RptFarmacoPrecio(String nombrefarmacia, String articulo, Float precio) {
		super();
		this.nombrefarmacia = nombrefarmacia;
		this.articulo = articulo;
		this.precio = precio;
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

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
	
}
