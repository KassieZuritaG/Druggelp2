package com.ortizzurita.druggelp2.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="farmacias")
public class Farmacia implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_farmacia")	
	private Integer idfarmacia;
	
	@Column(name="nombre")
	//@NotEmpty
	//@Size(max=100)
	private String nombre;
	
	@Column(name="telefono")
	//@NotEmpty
	//@Size(max=10)
	private String telefono;
	
	@Column(name="direccion")
	//@NotEmpty
	//@Size(max=100)
	private String direccion;

	public Farmacia() {
		super();
	}

	public Farmacia(Integer id) {
		super();
		this.idfarmacia = id;
	}

	public Integer getIdfarmacia() {
		return idfarmacia;
	}

	public void setIdfarmacia(Integer idfarmacia) {
		this.idfarmacia = idfarmacia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public String toString() {
		return this.getNombre() + " " + this.getTelefono();
	}
	
	@OneToMany(mappedBy="farmacia",fetch=FetchType.LAZY)
		private List<Farmaco> farmacos;
}
