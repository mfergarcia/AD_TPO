package entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import negocio.Direccion;

@MappedSuperclass
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", discriminatorType= DiscriminatorType.CHAR)
public class ClienteEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer idCliente;
	@OneToOne()
	@JoinColumn(name="idCtaCte")
	private CtaCteEntity ctaCte;
	private char tipoFactura;
	private String condicionesEspeciales;
	@OneToOne()
	@JoinColumn(name="idDireccion")
	private Direccion direccionFacturacion;
	// tipo 'E' (Empresa), 'P' (Persona)
	private char tipo;
	private char estado;
	
	
	public ClienteEntity(CtaCteEntity ctaCte, char tipoFactura, String condicionesEspeciales,
			Direccion direccionFacturacion, char tipo, char estado) {
		super();
		this.ctaCte = ctaCte;
		this.tipoFactura = tipoFactura;
		this.condicionesEspeciales = condicionesEspeciales;
		this.direccionFacturacion = direccionFacturacion;
		this.tipo = tipo;
		this.estado = estado;
	}


	public Integer getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}


	public CtaCteEntity getCtaCte() {
		return ctaCte;
	}


	public void setCtaCte(CtaCteEntity ctaCte) {
		this.ctaCte = ctaCte;
	}


	public char getTipoFactura() {
		return tipoFactura;
	}


	public void setTipoFactura(char tipoFactura) {
		this.tipoFactura = tipoFactura;
	}


	public String getCondicionesEspeciales() {
		return condicionesEspeciales;
	}


	public void setCondicionesEspeciales(String condicionesEspeciales) {
		this.condicionesEspeciales = condicionesEspeciales;
	}


	public Direccion getDireccionFacturacion() {
		return direccionFacturacion;
	}


	public void setDireccionFacturacion(Direccion direccionFacturacion) {
		this.direccionFacturacion = direccionFacturacion;
	}


	public char getTipo() {
		return tipo;
	}


	public void setTipo(char tipo) {
		this.tipo = tipo;
	}


	public char getEstado() {
		return estado;
	}


	public void setEstado(char estado) {
		this.estado = estado;
	}
	
	
	
	
}