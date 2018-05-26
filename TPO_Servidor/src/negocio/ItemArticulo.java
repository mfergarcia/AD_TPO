
package negocio;

import dao.ItemArticuloDAO;
import dto.ItemArticuloDTO;
import entities.ItemArticuloEntity;

public class ItemArticulo {
	
	private int id;
	private Articulo articulo;
	private int cant;
	private float precioVta;
	// estadoStock: "A CONFIRMAR", "PENDIENTE OPR", "RESERVADO", "DESCONTADO"
	private String estadoStock;
	
	public ItemArticulo() {

	}
	
	// Cuando se crea un Item Articulo, se crea en el estado "A CONFIRMAR"
	public ItemArticulo(Articulo articulo, int cantidad, float precioVta) {
		this.setArticulo(articulo);
		this.setCant(cantidad);
		this.setPrecioVta(precioVta);
		this.setEstadoStock("A CONFIRMAR");
	}
	
	public ItemArticulo(ItemArticuloEntity a) {
		this.setId(a.getId());
		this.setArticulo(new Articulo(a.getAe()));
		this.setCant(a.getCant());
		this.setPrecioVta(a.getPrecioVta());
		this.setEstadoStock(a.getEstadoStock());
	}

	public float calcularTotal() {
		return (this.getCant() * this.getPrecioVta());
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
	
	public ItemArticuloDTO toDTO() {
		ItemArticuloDTO itemArticuloDTO = new ItemArticuloDTO();
		itemArticuloDTO.setArticuloDTO(this.getArticulo().toDTO());
		itemArticuloDTO.setCant(this.getCant());
		itemArticuloDTO.setPrecioVta(this.getPrecioVta());
		itemArticuloDTO.setEstadoStock(this.getEstadoStock());
		return itemArticuloDTO;
	}
	
	public void saveMe() {
		this.setId(ItemArticuloDAO.getInstance().grabar(this));
	}	
	
	public void updateMe() {
		ItemArticuloDAO.getInstance().update(this);
	}	

}
