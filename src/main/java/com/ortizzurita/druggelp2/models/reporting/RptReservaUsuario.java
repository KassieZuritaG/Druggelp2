package com.ortizzurita.druggelp2.models.reporting;

import java.io.Serializable;
import java.math.BigInteger;

public class RptReservaUsuario implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String usuario;
	//si no vale cambia por biginteger
	private BigInteger numero;
	
	//Integer numero;
	
	public RptReservaUsuario() {
		super();
	}

	public RptReservaUsuario(String usuario, BigInteger numero) {
		super();
		this.usuario = usuario;
		this.numero = numero;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public BigInteger getNumero() {
		return numero;
	}

	public void setNumero(BigInteger numero) {
		this.numero = numero;
	}
	
}
