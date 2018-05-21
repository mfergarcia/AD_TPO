package entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;

import negocio.Factura;

@Entity
@Table (name="Facturas")
public class FacturaEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int idFactura;
	
	@Column( name ="tipoFactura" )
	private char tipoFactura;
	
	@Column (name= "estadoFactura")
	private String estadoFactura;
	
	@Column( name ="fechaFactura")
	private Date fechaFactura;
	
	private float montoAdeudado;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="numPedido")
	private PedidoEntity pe;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="idRemito")
	private RemitoEntity re;
	
	public FacturaEntity(int idFactura, char tipoFactura, String estadoFactura,
			Date fechaFactura, PedidoEntity pe, RemitoEntity re) {
		super();
		this.idFactura = idFactura;
		this.tipoFactura = tipoFactura;
		this.estadoFactura = estadoFactura;
		this.fechaFactura = fechaFactura;
		this.pe= pe;
		this.re= re;
	}
	
	public FacturaEntity(){}
	
	public FacturaEntity(Factura f) {
		this.setEstadoFactura(f.getEstadoFactura());
		this.setFechaFactura(f.getFechaFactura());
		this.setTipoFactura(f.getTipoFactura());
		this.setRe(new RemitoEntity (f.getRemito()));
		this.setPe(new PedidoEntity(f.getPedido()));
		this.setMontoAdeudado(f.getMontoAdeudado());
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

	public PedidoEntity getPe() {
		return pe;
	}

	public void setPe(PedidoEntity pe) {
		this.pe = pe;
	}

	public RemitoEntity getRe() {
		return re;
	}

	public void setRe(RemitoEntity re) {
		this.re = re;
	}
	
	
}
