package com.ortizzurita.druggelp2.models.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="reservas")
public class Reserva implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_reserva")	
	private Integer idreserva;
	
	@Column(name = "fecha_reserva")
	private LocalDateTime fechaRecerva;
	
	@Column(name = "cod_reserva")
	private String codReserva;
	
	@Column(name = "total_reserva")
	private String totalReserva;
	
	@Column(name = "creado_en")
	private LocalDateTime creadoEn;

	@Column(name = "creado_por")
	private String creadoPor;

	@Column(name = "modificado_en")
	private LocalDateTime modificadoEn;

	@Column(name = "modificado_por")
	private String modificadoPor;
	
	@JsonIgnore
	@OneToMany(mappedBy="reserva", fetch=FetchType.LAZY) 
	private List<DetalleReserva> farmacos;
	
	public List<DetalleReserva> getFarmacos() {
		if(farmacos == null)
			farmacos = new ArrayList<DetalleReserva>();
		return farmacos;
	}

	public void setFarmacos(List<DetalleReserva> farmacos) {
		this.farmacos = farmacos;
	}

	public Reserva() {
		super();
	}

	public Reserva(Integer id) {
		super();
		this.idreserva = id;
	}

	public Integer getIdreserva() {
		return idreserva;
	}

	public void setIdreserva(Integer idreserva) {
		this.idreserva = idreserva;
	}
	
	@Override
	public String toString() {
		return this.getIdreserva() + " ";
	}

	@PrePersist
	public void prePersist() {
		String palabra = ""; 
		int caracteres = 8; 
		for (int i=0; i<caracteres; i++){ 
			int codigoAscii = (int)Math.floor(Math.random()*(122 -97)+97); 
			palabra = palabra + (char)codigoAscii; 
		} 
		creadoEn = LocalDateTime.now();
		fechaRecerva = LocalDateTime.now();
		codReserva = palabra;
		SecurityContext context = SecurityContextHolder.getContext();
        creadoPor = context.getAuthentication().getName();
	}

	@PreUpdate
	public void preUpdate() {
		modificadoEn = LocalDateTime.now();
		SecurityContext context = SecurityContextHolder.getContext();
        modificadoPor = context.getAuthentication().getName();
	}

	public String getCodReserva() {
		return codReserva;
	}

	public void setCodReserva(String codReserva) {
		this.codReserva = codReserva;
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

	public String getTotalReserva() {
		return totalReserva;
	}

	public void setTotalReserva(String totalReserva) {
		this.totalReserva = totalReserva;
	}

	public LocalDateTime getFechaRecerva() {
		return fechaRecerva;
	}

	public void setFechaRecerva(LocalDateTime fechaRecerva) {
		this.fechaRecerva = fechaRecerva;
	}
	
}
