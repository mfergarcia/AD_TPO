package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ClientesPersona")
public class ClientePersonaEntity {
	@Id
	private Integer idCliente;
	@OneToOne()
	@JoinColumn(name="idCtaCte")
	private CtaCteEntity cc;
	private String condicionesEspeciales;
	private char estado;
	private String dni;
	private String apellido;
	private String nombre;
	
	public ClientePersonaEntity() {}
	
	public ClientePersonaEntity(Integer idCliente, String condicionesEspeciales,char estado, String dni, String apellido, String nombre) {
		this.idCliente= idCliente;
		this.condicionesEspeciales=condicionesEspeciales;
		this.estado=estado;
		this.dni=dni;
		this.apellido= apellido;
		this.nombre=nombre;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public CtaCteEntity getCc() {
		return cc;
	}

	public void setCc(CtaCteEntity cc) {
		this.cc = cc;
	}

	public String getCondicionesEspeciales() {
		return condicionesEspeciales;
	}

	public void setCondicionesEspeciales(String condicionesEspeciales) {
		this.condicionesEspeciales = condicionesEspeciales;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
