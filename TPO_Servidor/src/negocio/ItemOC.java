// @Facu: implementar saveMe
package negocio;

import dto.ItemOCDTO;

public class ItemOC {

	private Articulo articulo;
	private int cantidad;
	
	public ItemOC() {

	}

	public ItemOC(Articulo articulo, int cantidad) {
		this.setArticulo(articulo);
		this.setCantidad(cantidad);
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public ItemOCDTO toDTO() {
		ItemOCDTO itemOCDTO = new ItemOCDTO();
		itemOCDTO.setArticulo(this.getArticulo().toDTO());
		itemOCDTO.setCantidad(this.getCantidad());
		return itemOCDTO;
	}
	
	//@Facu: implementar metodo
	public void saveMe() {

	}	

}
