// LISTO PARA PROBAR
package dto;

import java.io.Serializable;

public class ItemArticuloDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArticuloDTO articulo;
	private int cant;
	private float precioVta;
	// estadoStock: "PENDIENTE OPR", "RESERVADO", "DESCONTADO"
	private String estadoStock;
	
	public ItemArticuloDTO() {

	}

	public ArticuloDTO getArticuloDTO() {
		return articulo;
	}

	public void setArticuloDTO(ArticuloDTO articuloDTO) {
		this.articulo = articuloDTO;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public float getPrecioVta() {
		return precioVta;
	}

	public void setPrecioVta(float precioVta) {
		this.precioVta = precioVta;
	}

	public String getEstadoStock() {
		return estadoStock;
	}

	public void setEstadoStock(String estadoStock) {
		this.estadoStock = estadoStock;
	}

}
