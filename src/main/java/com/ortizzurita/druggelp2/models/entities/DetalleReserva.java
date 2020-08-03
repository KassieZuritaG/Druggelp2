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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="detallesreserva")
public class DetalleReserva implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_detalle")	
	private Integer iddetalle;
	
	@Column(name = "fecha_reserva")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Calendar fechaRecerva;
	
	@Column(name="subtotal")
	private Float subtotal;

	@Column(name="total")
	private Float total;
	
	@Column(name="cantidad")
	private Integer cantidad;

	public DetalleReserva() {
		super();
	}

	public DetalleReserva(Integer id) {
		super();
		this.iddetalle = id;
	}

	public Integer getIddetalle() {
		return iddetalle;
	}

	public void setIddetalle(Integer iddetalle) {
		this.iddetalle = iddetalle;
	}

	public Calendar getFechaRecerva() {
		return fechaRecerva;
	}

	public void setFechaRecerva(Calendar fechaRecerva) {
		this.fechaRecerva = fechaRecerva;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	@Override
	public String toString() {
		return this.getIddetalle() + " " + this.getFechaRecerva();
	}
	
	public String fechaRecerva() {
		if(this.fechaRecerva == null) return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fechaRecerva.getTime());
	}
	
	@JoinColumn(name="fk_farmaco", referencedColumnName="pk_farmaco")
	@ManyToOne
	private  Farmaco farmaco;
	
	@JoinColumn(name="fk_reserva", referencedColumnName="pk_reserva")
	@ManyToOne
	private  Reserva reserva;
}
