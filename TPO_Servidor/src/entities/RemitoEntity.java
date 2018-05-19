package entities;


import java.sql.Date;
import java.util.Calendar;

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
import negocio.Factura;

@Entity
@Table (name="Remitos")
public class RemitoEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int idRemito;
	
	@Column (name = "fecha")
	private Date fecha;
	
	@Column (name = "idFactura")
	private int idFactura;

	public RemitoEntity(int Rem ) {
		super();
		this.idRemito = Rem;
		this.setFecha((Date)Calendar.getInstance().getTime());;
	}

	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	
	
	

}
