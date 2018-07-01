package entities;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import negocio.Cliente;
import negocio.Direccion;

@Entity
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tCliente", discriminatorType= DiscriminatorType.CHAR)
@DiscriminatorValue("C")
@Table(name="Clientes")
public class ClienteEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer idCliente;
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="idCtaCte")
	private CtaCteEntity ctaCte;
	private char tipoFactura;
	private String condicionesEspeciales;
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="idDireccion")
	private DireccionEntity direccionFacturacion;
	// tipo 'E' (Empresa), 'P' (Persona)
	private char tipo;
	private char estado;
	
	public ClienteEntity(){}
	public ClienteEntity(Integer idCliente, CtaCteEntity ctaCte, char tipoFactura, String condicionesEspeciales,
			DireccionEntity direccionFacturacion, char tipo, char estado) {
		super();
		this.idCliente=idCliente;
		this.ctaCte = ctaCte;
		this.tipoFactura = tipoFactura;
		this.condicionesEspeciales = condicionesEspeciales;
		this.direccionFacturacion = direccionFacturacion;
		this.tipo = tipo;
		this.estado = estado;
	}


	public ClienteEntity(Cliente cliente) {
		if(cliente.getIdCliente()!=0)
			this.setIdCliente(cliente.getIdCliente());
		this.setCondicionesEspeciales(cliente.getCondicionesEspeciales());
		this.setCtaCte(new CtaCteEntity(cliente.getCtaCte()));
		this.setTipoFactura(cliente.getTipoFactura());
		this.setDireccionFacturacion(new DireccionEntity(cliente.getDireccionFacturacion()));
		this.setEstado(cliente.getEstado());
		this.setTipo(cliente.getTipo());
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


	public DireccionEntity getDireccionFacturacion() {
		return direccionFacturacion;
	}


	public void setDireccionFacturacion(DireccionEntity direccionFacturacion) {
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
