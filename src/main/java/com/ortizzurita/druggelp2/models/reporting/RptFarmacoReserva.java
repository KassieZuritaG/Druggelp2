package com.ortizzurita.druggelp2.models.reporting;

import java.io.Serializable;
import java.math.BigInteger;

public class RptFarmacoReserva implements Serializable{

	private static final long serialVersionUID = 1L;
	private String mes;
	private String articulo;
	private BigInteger numero;
	
	public RptFarmacoReserva() {
		super();
	}

	

	public RptFarmacoReserva(String mes, String articulo, BigInteger numero) {
		super();
		this.mes = mes;
		this.articulo = articulo;
		this.numero = numero;
	}



	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public BigInteger getNumero() {
		return numero;
	}



	public void setNumero(BigInteger numero) {
		this.numero = numero;
	}

		
	
}
