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
@Table(name="farmaceuticos")
public class Farmaceutico extends Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "pk_farmaceutico")
	private Integer idfarmaceutico;
	

	@Column(name="titulo")
	//@NotEmpty
	//@Size(max=100)
	private String titulo;
	
	@Column(name = "fecha_ingreso")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Calendar fechaIngreso;
	
	@Column(name="tipo_contrato")
	//@NotEmpty
	//@Size(max=100)
	private String tipoContrato;

	public Farmaceutico() {
		super();
	}

	public Farmaceutico(Integer id) {
		super();
		this.idfarmaceutico = id;
	}

	public Integer getIdfarmaceutico() {
		return idfarmaceutico;
	}

	public void setIdfarmaceutico(Integer idfarmaceutico) {
		this.idfarmaceutico = idfarmaceutico;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public String fechaIng() {
		if(this.fechaIngreso == null) return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fechaIngreso.getTime());
	}

}
