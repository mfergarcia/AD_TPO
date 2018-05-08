//OK
package dto;

import java.io.Serializable;

public class ItemArticuloDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArticuloDTO articuloDTO;
	private int cant;
	private float precioVta;
	// estadoStock: "PENDIENTE OPR", "RESERVADO", "DESCONTADO"
	private String estadoStock;
	
	public ItemArticuloDTO() {
		// TODO Auto-generated constructor stub
	}

	public ArticuloDTO getArticuloDTO() {
		return articuloDTO;
	}

	public void setArticuloDTO(ArticuloDTO articuloDTO) {
		this.articuloDTO = articuloDTO;
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
