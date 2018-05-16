// Pendiente:
// @Facu: cambiar para que la herencia se guarde en una sola tabla y 
// adaptar los contructores. Ver como resolver: La creacion del cliente implica tambien la cracion
// de la cuenta corriente
package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import negocio.CtaCte;
import negocio.Direccion;

@Entity
@Table(name="Clientes")
public class ClienteEmpresaEntity {
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
	private String cuit;
	private String razonSocial;
	
	public ClienteEmpresaEntity() {
		
	}

	//Facu: ver como dar de alta tambien la cuenta corriente
	public ClienteEmpresaEntity(CtaCte ctaCte, char tipoFactura, String condicionesEspeciales, Direccion direccionFacturacion, char tipo, char estado, String cuit, String razonSocial) {
		// this.setCtaCte(ctaCte);
		this.setTipoFactura(tipoFactura);
		this.setCondicionesEspeciales(condicionesEspeciales);
		this.setDireccionFacturacion(direccionFacturacion);
		this.setTipo(tipo);
		this.setEstado(estado);
		this.setCuit(cuit);
		this.setRazonSocial(razonSocial);
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

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
}
