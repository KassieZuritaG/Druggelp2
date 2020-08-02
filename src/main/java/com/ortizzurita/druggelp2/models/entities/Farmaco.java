package com.ortizzurita.druggelp2.models.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="farmacos")
public class Farmaco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_farmaco")	
	private Integer idfarmaco;
	
	@Column(name="nombre")
	//@NotEmpty
	//@Size(max=100)
	private String nombre;
	
	@Column(name="costo")
	private Float costo;
	
	@Column(name = "fecha_fabricacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Calendar fechaFabricacion;
	
	@Column(name = "fecha_expiracion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Calendar fechaExpiracion;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
	@Column(name="tipo_medicamento")
	private Integer tipoMedicamento;

	public Farmaco() {
		super();
	}

	public Farmaco(Integer id) {
		super();
		this.idfarmaco = id;
	}

	public Integer getIdfarmaco() {
		return idfarmaco;
	}

	public void setIdfarmaco(Integer idfarmaco) {
		this.idfarmaco = idfarmaco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getCosto() {
		return costo;
	}

	public void setCosto(Float costo) {
		this.costo = costo;
	}

	public Calendar getFechaFabricacion() {
		return fechaFabricacion;
	}

	public void setFechaFabricacion(Calendar fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public Calendar getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(Calendar fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getTipoMedicamento() {
		return tipoMedicamento;
	}

	public void setTipoMedicamento(Integer tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}
	
	@Override
	public String toString() {
		return this.getNombre() + " " + this.getFechaExpiracion();
	}
	
	public String fechaExp() {
		if(this.fechaExpiracion == null) return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fechaExpiracion.getTime());
	}
	
	public String fechaFab() {
		if(this.fechaFabricacion == null) return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fechaFabricacion.getTime());
	}
}
