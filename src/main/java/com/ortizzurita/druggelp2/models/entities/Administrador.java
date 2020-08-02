package com.ortizzurita.druggelp2.models.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="administradores")
public class Administrador extends Persona implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "pk_administrador")
	private Integer idadministrador;

	public Administrador() {
		super();
	}

	public Administrador(Integer id) {
		super();
		this.idadministrador = id;
	}

	public Integer getIdadministrador() {
		return idadministrador;
	}

	public void setIdadministrador(Integer idadministrador) {
		this.idadministrador = idadministrador;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
