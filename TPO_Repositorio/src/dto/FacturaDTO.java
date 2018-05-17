//PENDIENTE
package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class FacturaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char tipoFactura;
	private int numFactura;
	private Date fechaFactura;
	private int numPedido;
	private RemitoDTO remito;
	private float montoAdeudado;
	private Collection<ItemArticuloDTO> items;
	// estadoFactura: "IMPAGA", "PAGO PARCIAL", "PAGADA"
	private String estadoFactura;
	
	public FacturaDTO() {
		// TODO Auto-generated constructor stub
		this.items = new ArrayList<ItemArticuloDTO>();
	}

	public void agregarItem(ItemArticuloDTO itemDTO) {
		this.items.add(itemDTO);
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

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public int getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}

	public RemitoDTO getRemitoDTO() {
		return remito;
	}

	public void setRemitoDTO(RemitoDTO remitoDTO) {
		this.remito = remitoDTO;
	}

	public float getMontoAdeudado() {
		return montoAdeudado;
	}

	public void setMontoAdeudado(float montoAdeudado) {
		this.montoAdeudado = montoAdeudado;
	}

	public String getEstadoFactura() {
		return estadoFactura;
	}

	public void setEstadoFactura(String estadoFactura) {
		this.estadoFactura = estadoFactura;
	}
	

	public Collection<ItemArticuloDTO> getItems() {
		return items;
	}

}
