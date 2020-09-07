package com.ortizzurita.druggelp2.models.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Table(name="detalles")
public class DetalleReserva implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_detalle")	
	private Integer iddetalle;
	
	@Column(name="subtotal")
	private Float subtotal;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
	@JoinColumn(name="fk_farmaco", referencedColumnName="pk_farmaco")
	@ManyToOne
	private Farmaco medicamento;
	
	@JoinColumn(name="fk_reserva", referencedColumnName="pk_reserva")
	@ManyToOne
	private Reserva reserva;
	
	/**** TRANSIENT ***/
	
	@Transient
	private int medicamentoid;
	
	@Transient
	private int reservaid;
	
	@Column(name = "creado_en")
	private LocalDateTime creadoEn;

	@Column(name = "creado_por")
	private String creadoPor;

	@Column(name = "modificado_en")
	private LocalDateTime modificadoEn;

	@Column(name = "modificado_por")
	private String modificadoPor;

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

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Farmaco getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Farmaco medicamento) {
		this.medicamento = medicamento;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	public int getMedicamentoid() {
		return medicamentoid;
	}

	public void setMedicamentoid(int medicamentoid) {
		this.medicamentoid = medicamentoid;
	}

	public int getReservaid() {
		return reservaid;
	}

	public void setReservaid(int reservaid) {
		this.reservaid = reservaid;
	}

	public LocalDateTime getCreadoEn() {
		return creadoEn;
	}

	public void setCreadoEn(LocalDateTime creadoEn) {
		this.creadoEn = creadoEn;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public LocalDateTime getModificadoEn() {
		return modificadoEn;
	}

	public void setModificadoEn(LocalDateTime modificadoEn) {
		this.modificadoEn = modificadoEn;
	}

	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	@Override
	public String toString() {
		return this.getIddetalle() + " " + this.getSubtotal();
	}
	
	@PrePersist
	public void prePersist() {
		creadoEn = LocalDateTime.now();
		SecurityContext context = SecurityContextHolder.getContext();
        creadoPor = context.getAuthentication().getName();
	}

	@PreUpdate
	public void preUpdate() {
		modificadoEn = LocalDateTime.now();
		SecurityContext context = SecurityContextHolder.getContext();
        modificadoPor = context.getAuthentication().getName();
	}
	
}
