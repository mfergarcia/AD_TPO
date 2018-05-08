//PENDIENTE: Ver si hace falta método toDTO
package negocio;

public class ItemOC {

	private Articulo articulo;
	private int cantidad;
	
	public ItemOC() {
		// TODO Auto-generated constructor stub
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
	
}
