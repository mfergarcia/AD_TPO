
package negocio;

import java.util.Calendar;
import java.util.Date;

import dao.PagoDAO;
import dto.PagoDTO;
import entities.PagoEntity;

public class Pago {

	private int id;
	private Date fecha;
	private String tipoPago;
	private float importe;
	// estado: "RECIBIDO", "APLICADO"
	private String estado;

	public Pago() {

	}
	
	public Pago(PagoEntity p) {
		this.setId(p.getIdPago());
		this.setEstado(p.getEstado());
		this.setFecha(p.getFecha());
		this.setImporte(p.getImporte());
		this.setTipoPago(p.getTipoPago());
	}
	
	// Crea un pago con la fecha del dia y setea el estado inicial
	public Pago(String tipoPago, float importe){
		this.setTipoPago(tipoPago);
		this.setImporte(importe);
		// Se genera con la fecha/hora del momento
		this.setFecha(Calendar.getInstance().getTime());	
		this.setEstado("RECIBIDO");
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
	
	public PagoDTO toDTO() {
		PagoDTO pagoDTO = new PagoDTO();
		pagoDTO.setId(this.getId());
		pagoDTO.setFecha(this.getFecha());
		pagoDTO.setTipoPago(this.getTipoPago());
		pagoDTO.setImporte(this.getImporte());
		pagoDTO.setEstado(this.getEstado());
		return pagoDTO;
	}
	
	public void saveMe() {
		this.setId(PagoDAO.getInstance().grabar(this));
	}	
	
	
	public void updateMe() {
		PagoDAO.getInstance().update(this);
	}	

}