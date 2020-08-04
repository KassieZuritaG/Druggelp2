package com.ortizzurita.druggelp2.models.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class Persona {


	@Column(name = "nombres")
	@NotEmpty
	@Size(max=35)
	private String nombres;

	@Column(name = "apellidos")
	@NotEmpty
	@Size(max=35)
	private String apellidos;

	@Column(name = "cedula")
	@NotEmpty
	@Size(max=15)
	private String cedula;

	@Column(name = "lugar_nacimiento")
	private String lugarNacimiento;

	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Calendar fechaNacimiento;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "email")
	//@Email
	private String email;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "imagen")
	private String imagen;

	public Persona() {
		super();
	}
	
	public Persona(Integer id) {
		super();		
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return this.getApellidos() + " " + this.getNombres();
	}
	
	public String fechaNac() {
		if(this.fechaNacimiento == null) return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fechaNacimiento.getTime());
	}
	
	public String sexoDesc() {
		if(this.sexo.equals("M"))
			return "Mujer";
		
		return "Hombre";		
	}	
}
