package com.ortizzurita.druggelp2.models.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
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
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Calendar fechaRecerva;
	
	/**** TRANSIENT ***/
	
	@Transient
	private int usuarioid;
	
	@JsonIgnore
	@OneToMany(mappedBy="reserva", fetch=FetchType.LAZY)
	private List<Medicamento> medicamentos;

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

	public Calendar getFechaRecerva() {
		return fechaRecerva;
	}

	public void setFechaRecerva(Calendar fechaRecerva) {
		this.fechaRecerva = fechaRecerva;
	}
	
	@Column(name = "creado_en")
	private LocalDateTime creadoEn;

	@Column(name = "creado_por")
	private String creadoPor;

	@Column(name = "modificado_en")
	private LocalDateTime modificadoEn;

	@Column(name = "modificado_por")
	private String modificadoPor;
	
	@Override
	public String toString() {
		return this.getIdreserva()+ " " + this.getFechaRecerva();
	}
	
	public String fechaRec() {
		if(this.fechaRecerva == null) return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fechaRecerva.getTime());
	}
	
	
	@JoinColumn(name="fk_cliente", referencedColumnName="pk_cliente")
	@ManyToOne
	private Cliente cliente;

	/*@OneToMany(mappedBy="reserva",fetch=FetchType.LAZY)
	private List<DetalleReserva> detalleReserva;
	
	public List<DetalleReserva> getDetalleReserva() {
		return detalleReserva;
	}

	public void setDetalleReserva(List<DetalleReserva> detalleReserva) {
		this.detalleReserva = detalleReserva;
	}*/

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@JoinColumn(name="fk_usuario", referencedColumnName="pk_usuario")
	@ManyToOne
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(int usuarioid) {
		this.usuarioid = usuarioid;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
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
	
	/////////////////////MAESTRO DETALLE RESERVA/////////////////////////
	
	
	@JsonIgnore
	@OneToMany(mappedBy="reserva", fetch=FetchType.LAZY) 
	private List<DetalleReserva> detallereservas;

	public List<DetalleReserva> getDetallereservas() {
		if(detallereservas == null)
			detallereservas = new ArrayList<DetalleReserva>();
		return detallereservas;
	}

	public void setDetallereservas(List<DetalleReserva> detallereservas) {
		this.detallereservas = detallereservas;
	}
	
	
}
