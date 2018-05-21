package entities;

import java.util.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

import negocio.Pago;

@Entity
@Table (name="Pagos")
public class PagoEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)	
	private int idPago;
	
	@Column(name ="fecha")
	private Date fecha;
	
	@Column(name ="tipoPago")
	private String tipoPago;
	
	@Column(name ="importe")
	private float importe;
	
	@Column(name ="estado")
	private String estado;

	public PagoEntity(String tipoPago, float importe) {
		super();
		this.tipoPago = tipoPago;
		this.importe = importe;
		this.estado = "RECIBIDO";
		this.setFecha((Date) Calendar.getInstance().getTime());	
		this.setEstado("RECIBIDO");
	}
	
	public PagoEntity(){}
	
	public PagoEntity(Pago p) {
		this.setEstado(p.getEstado());
		this.setImporte(p.getImporte());
		this.setFecha((Date) Calendar.getInstance().getTime());	
		this.setTipoPago(p.getTipoPago());
	}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
