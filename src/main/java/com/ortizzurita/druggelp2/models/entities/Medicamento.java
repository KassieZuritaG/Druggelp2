package com.ortizzurita.druggelp2.models.entities;

import java.io.Serializable;
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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="medicamentos")
public class Medicamento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_medicamento")	
	private Integer idmedicamento;
	
	@JoinColumn(name="fk_farmaco", referencedColumnName="pk_farmaco")
	@ManyToOne
	private  Farmaco farmaco;
	
	@JoinColumn(name="fk_reserva", referencedColumnName="pk_reserva")
	@ManyToOne
	private  Reserva reserva;
	
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
	
	/**** TRANSIENT ****/
	
	@Transient
	private int farmacoid;
	
	@Transient
	private int reservaid;

	public int getFarmacoid() {
		return farmacoid;
	}

	public void setFarmacoid(int farmacoid) {
		this.farmacoid = farmacoid;
	}

	public int getReservaid() {
		return reservaid;
	}

	public void setReservaid(int reservaid) {
		this.reservaid = reservaid;
	}

	public Medicamento() {
		super();
	}
	
	public Medicamento(Integer id) {
		super();
		this.idmedicamento = id;
	}

	public Integer getIdmedicamento() {
		return idmedicamento;
	}

	public void setIdmedicamento(Integer idmedicamento) {
		this.idmedicamento = idmedicamento;
	}

	public Farmaco getFarmaco() {
		return farmaco;
	}

	public void setFarmaco(Farmaco farmaco) {
		this.farmaco = farmaco;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
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
}
