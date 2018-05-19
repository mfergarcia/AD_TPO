package entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import negocio.CtaCte;
import negocio.Direccion;
import negocio.Pedido;
import negocio.Remito;

@Entity
@DiscriminatorColumn(name="tipo", discriminatorType= DiscriminatorType.CHAR)
@Table (name="Facturas")
public class FacturaEntity {
	@Id
	//@Column("idFactura")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int idFactura;
	
	@Column( name ="tipoFactura" )
	private char tipoFactura;
	
	@Column (name= "estadoFactura")
	private String estadoFactura;
	
	@Column( name ="idCtaCte" )
	private CtaCte idCtaCte;
	
	@Column( name ="montoAdeudado")
	private float montoAdeudado;
	
	@Column( name ="fechaFactura")
	private Date fechaFactura;

	public FacturaEntity(int idFactura, char tipoFactura, String estadoFactura, float montoAdeudado,
			Date fechaFactura) {
		super();
		this.idFactura = idFactura;
		this.tipoFactura = tipoFactura;
		this.estadoFactura = estadoFactura;
		//this.idCtaCte = idCtaCte;
		this.montoAdeudado = montoAdeudado;
		this.fechaFactura = fechaFactura;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public char getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(char tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public String getEstadoFactura() {
		return estadoFactura;
	}

	public void setEstadoFactura(String estadoFactura) {
		this.estadoFactura = estadoFactura;
	}

	public CtaCte getIdCtaCte() {
		return idCtaCte;
	}

	public void setIdCtaCte(CtaCte idCtaCte) {
		this.idCtaCte = idCtaCte;
	}

	public float getMontoAdeudado() {
		return montoAdeudado;
	}

	public void setMontoAdeudado(float montoAdeudado) {
		this.montoAdeudado = montoAdeudado;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	
	
}
