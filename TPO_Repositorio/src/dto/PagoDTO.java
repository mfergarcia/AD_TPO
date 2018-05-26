package dto;

import java.io.Serializable;
import java.util.Date;

public class PagoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date fecha;
	private String tipoPago;
	private float importe;
	// estado: "RECIBIDO", "APLICADO"
	private String estado;

	public PagoDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
