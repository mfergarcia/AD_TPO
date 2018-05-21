package entities;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.Table;

import negocio.Remito;

@Entity
@Table (name="Remitos")
public class RemitoEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int idRemito;
	
	@Column (name = "fecha")
	private Date fecha;
	
	@OneToOne()
	@JoinColumn(name="idFactura")
	private FacturaEntity factura;
	
	public RemitoEntity(FacturaEntity fe, Date fecha){
		this.setFactura(fe);
		this.setFecha(fecha);
	}
	
	public RemitoEntity(){}
	
	public RemitoEntity(Remito remito) {
		this.setFecha(remito.getFechaRemito());
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

	public FacturaEntity getFactura() {
		return factura;
	}

	public void setFactura(FacturaEntity factura) {
		this.factura = factura;
	}

	
	
	

}
