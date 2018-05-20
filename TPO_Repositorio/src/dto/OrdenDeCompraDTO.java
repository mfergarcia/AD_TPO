// LISTO PARA PROBAR
package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class OrdenDeCompraDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numOC;
	private Date fecha;
	private String proveedor;
	private Collection<ItemOCDTO> items;
	// estado: "PENDIENTE", "CUMPLIDA"
	private String estado;

	public OrdenDeCompraDTO() {
		this.items = new ArrayList<ItemOCDTO>();
	}

	public void agregarItem(ItemOCDTO itemOCDTO) {
		this.items.add(itemOCDTO);
	}

	public int getNumOC() {
		return numOC;
	}

	public void setNumOC(int numOC) {
		this.numOC = numOC;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Collection<ItemOCDTO> getItems() {
		return items;
	}

}
