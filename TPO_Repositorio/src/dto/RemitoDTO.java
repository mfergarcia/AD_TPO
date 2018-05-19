// LISTO PARA PROBAR
package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class RemitoDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int numRemito;
	private Date fechaRemito;
	private char tipoFactura;
	private int numFactura;
	private Collection<ItemArticuloDTO> items;
	
	public RemitoDTO() {
		this.items = new ArrayList<ItemArticuloDTO>();
	}

	public void agregarItem(ItemArticuloDTO itemDTO) {
		this.items.add(itemDTO);
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

	public char getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(char tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public int getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(int numFactura) {
		this.numFactura = numFactura;
	}

	public Collection<ItemArticuloDTO> getItems() {
		return items;
	}

}
