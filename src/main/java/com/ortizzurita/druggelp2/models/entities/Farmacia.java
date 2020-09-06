package com.ortizzurita.druggelp2.models.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnore;



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
	
	@Column(name = "creado_en")
	private LocalDateTime creadoEn;

	@Column(name = "creado_por")
	private String creadoPor;

	@Column(name = "modificado_en")
	private LocalDateTime modificadoEn;

	@Column(name = "modificado_por")
	private String modificadoPor;

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
		return this.getNombre() + " " + this.getTelefono();
	}
	
	@JsonIgnore
	@OneToMany(mappedBy="farmacia", fetch=FetchType.LAZY) 
	private List<Articulo> articulos;
	
	public List<Articulo> getArticulos() {
		if(articulos == null)
			articulos = new ArrayList<Articulo>();
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	/*@OneToMany(mappedBy="farmacia",fetch=FetchType.LAZY)
		private List<Farmaco> farmacos;

	public List<Farmaco> getFarmacos() {
		return farmacos;
	}

	public void setFarmacos(List<Farmaco> farmacos) {
		this.farmacos = farmacos;
	}*/
	

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
