package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ClientesEmpresa")
public class ClienteEmpresaEntity {
	@Id
	private Integer idCliente;
	@OneToOne()
	@JoinColumn(name="idCtaCte")
	private CtaCteEntity cc;
	private String condicionesEspeciales;
	private char estado;
	private String cuit;
	private String razonSocial;
	
	public ClienteEmpresaEntity(){}

	public ClienteEmpresaEntity(Integer idCliente, String condicionesEspeciales, char estado,
			String cuit, String razonSocial) {
		super();
		this.idCliente = idCliente;
		this.condicionesEspeciales = condicionesEspeciales;
		this.estado = estado;
		this.cuit = cuit;
		this.razonSocial = razonSocial;
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
