// LISTO PARA PROBAR
package dto;

import java.io.Serializable;

public class ItemOCDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArticuloDTO articulo;
	private int cantidad;

	public ItemOCDTO() {

	}

	public ArticuloDTO getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
