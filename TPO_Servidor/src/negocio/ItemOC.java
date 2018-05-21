
package negocio;

import dao.ItemOCDAO;
import dto.ItemOCDTO;
import entities.ItemOCEntity;

public class ItemOC {
	private int id;
	private Articulo articulo;
	private int cantidad;
	
	public ItemOC() {

	}

	public ItemOC(Articulo articulo, int cantidad) {
		this.setArticulo(articulo);
		this.setCantidad(cantidad);
	}
	
	
	public ItemOC(ItemOCEntity a) {
		this.setId(a.getId());
		this.setArticulo(new Articulo(a.getArticulo()));
		this.setCantidad(a.getCantidad());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public void saveMe() {
		this.setId(ItemOCDAO.getInstance().grabar(this));
	}	

	public void updateMe() {
		ItemOCDAO.getInstance().update(this);
	}	

}
