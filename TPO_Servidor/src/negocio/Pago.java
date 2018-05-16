// PENDIENTE: Ver si hace falta agregar el metodo toDTO
package negocio;

import java.util.Calendar;
import java.util.Date;

public class Pago {

	private Date fecha;
	private String tipoPago;
	private float importe;
	// estado: "RECIBIDO", "APLICADO"
	private String estado;

	public Pago() {
		// TODO Auto-generated constructor stub
	}
	
	// Crea un pago para un Cliente con la fecha del dia y setea el estado inicial
	public Pago(String tipoPago, float importe){
		this.setTipoPago(tipoPago);
		this.setImporte(importe);
		// Se genera con la fecha/hora del momento
		this.setFecha(Calendar.getInstance().getTime());	
		this.setEstado("RECIBIDO");
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
	
	//@Facu: implementar metodo
	public void saveMe() {

	}	
	
}