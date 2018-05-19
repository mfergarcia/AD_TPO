// LISTO PARA PROBAR
package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class PedidoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numPedido;
	private int idCliente;
	private Date fechaGen;
	private DireccionDTO dirEntrega;
	private Date fechaEntrega;
	private Collection<ItemArticuloDTO> items;
	private char tipoFactura;
	private int numFactura;
	private String motivoRechazo;
	private String estado;	
	
	public PedidoDTO() {
		this.items = new ArrayList<ItemArticuloDTO>();
	}

	public void agregarItem(ItemArticuloDTO itemDTO) {
		this.items.add(itemDTO);
	}
	
	public int getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Date getFechaGen() {
		return fechaGen;
	}

	public void setFechaGen(Date fechaGen) {
		this.fechaGen = fechaGen;
	}

	public DireccionDTO getDirEntrega() {
		return dirEntrega;
	}

	public void setDirEntrega(DireccionDTO dirEntrega) {
		this.dirEntrega = dirEntrega;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Collection<ItemArticuloDTO> getArticulos() {
		return items;
	}

	public void setArticulos(Collection<ItemArticuloDTO> articulos) {
		this.items = articulos;
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
	
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Collection<ItemArticuloDTO> getItems() {
		return items;
	}
	
}
