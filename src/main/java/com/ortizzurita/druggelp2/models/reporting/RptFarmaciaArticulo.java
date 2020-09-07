package com.ortizzurita.druggelp2.models.reporting;

import java.io.Serializable;

public class RptFarmaciaArticulo  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String articulo;
	private String nombrefarmacia;
	private Integer cantidad;
	
	public RptFarmaciaArticulo() {
		super();
	}

	public RptFarmaciaArticulo(String articulo, String nombrefarmacia, Integer cantidad) {
		super();
		this.articulo = articulo;
		this.nombrefarmacia = nombrefarmacia;
		this.cantidad = cantidad;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getNombrefarmacia() {
		return nombrefarmacia;
	}

	public void setNombrefarmacia(String nombrefarmacia) {
		this.nombrefarmacia = nombrefarmacia;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
