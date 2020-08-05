package com.ortizzurita.druggelp2.models.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@Override
	public String toString() {
		return this.getIdreserva()+ " " + this.getFechaRecerva();
	}
	
	public String fechaRec() {
		if(this.fechaRecerva == null) return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fechaRecerva.getTime());
	}
	
	@OneToMany(mappedBy="reserva",fetch=FetchType.LAZY)
	private List<DetalleReserva> detalleReserva;
	
	@JoinColumn(name="fk_cliente", referencedColumnName="pk_cliente")
	@ManyToOne
	private Cliente cliente;

	public List<DetalleReserva> getDetalleReserva() {
		return detalleReserva;
	}

	public void setDetalleReserva(List<DetalleReserva> detalleReserva) {
		this.detalleReserva = detalleReserva;
	}

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
	
}
