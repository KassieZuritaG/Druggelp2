package com.ortizzurita.druggelp2.models.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
	@NotEmpty
	@Size(max=100)
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
	private String tipoMedicamento;
	
	@Column(name = "imagen")
	private String imagen;
	
	@Column(name = "creado_en")
	private LocalDateTime creadoEn;

	@Column(name = "creado_por")
	private String creadoPor;

	@Column(name = "modificado_en")
	private LocalDateTime modificadoEn;

	@Column(name = "modificado_por")
	private String modificadoPor;
	
	@JsonIgnore
	@OneToMany(mappedBy="medicamento", fetch=FetchType.LAZY) 
	private List<DetalleReserva> medicamentos;
	
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
	
	public String getTipoMedicamento() {
		return tipoMedicamento;
	}

	public void setTipoMedicamento(String tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
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
	
	public List<DetalleReserva> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<DetalleReserva> medicamentos) {
		this.medicamentos = medicamentos;
	}

	@Override
	public String toString() {
		return this.getNombre() + " " + this.getTipoMedicamento();
	}
	
	public String fechaExp() {
		if(this.fechaExpiracion == null) return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
		return sdf.format(fechaExpiracion.getTime());
	}
	
	public String fechaFab() {
		if(this.fechaFabricacion == null) return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
		return sdf.format(fechaFabricacion.getTime());
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
