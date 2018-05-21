
package negocio;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import dao.RemitoDAO;
import dto.RemitoDTO;
import entities.RemitoEntity;

public class Remito {

	private int numRemito;
	private Date fechaRemito;
	// Se necesita conocer la Factura para acceder a los items que debe tener el Remito
	private Factura factura;
	
	public Remito() {

	}

	// Crea un Remito con la fecha del dia
	public Remito(Factura factura){
		this.setFactura(factura);
		// Se genera con la fecha/hora del momento
		this.setFechaRemito(Calendar.getInstance().getTime());
		
	}
	
	public Remito(RemitoEntity re){
		this.setNumRemito(re.getIdRemito());
		this.setFactura(new Factura(re.getFactura()));
		this.setFechaRemito(re.getFecha());
	}
	

	public int getNumRemito() {
		return numRemito;
	}

	public void setNumRemito(int numRemito) {
		this.numRemito = numRemito;
	}

	public Date getFechaRemito() {
		return fechaRemito;
	}

	public void setFechaRemito(Date fechaRemito) {
		this.fechaRemito = fechaRemito;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	public RemitoDTO toDTO() {
		RemitoDTO remitoDTO = new RemitoDTO();
		remitoDTO.setNumRemito(this.getNumRemito());
		remitoDTO.setFechaRemito(this.getFechaRemito());
		remitoDTO.setTipoFactura(this.getFactura().getTipoFactura());
		remitoDTO.setNumFactura(this.getFactura().getNumFactura());
		ItemArticulo aux;
		for(Iterator<ItemArticulo> i = (this.getFactura().getPedido().getArticulos()).iterator() ; i.hasNext(); ) {
			aux = i.next();
			remitoDTO.agregarItem(aux.toDTO());
		}
		return remitoDTO;
	}

	public void saveMe() {
		this.setNumRemito(RemitoDAO.getIntance().grabar(this));
	}	

	public void updateMe() {
		RemitoDAO.getIntance().update(this);
	}	

}
