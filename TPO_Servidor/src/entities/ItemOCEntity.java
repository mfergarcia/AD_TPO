package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import negocio.ItemOC;

@Entity
@Table(name= "itemsOC")
public class ItemOCEntity {
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name= "codigoBarras")
	private ArticuloEntity articulo;
	private int cantidad;
	
	public ItemOCEntity(ArticuloEntity articulo, int cantidad) {
		super();
		this.articulo = articulo;
		this.cantidad = cantidad;
	}
	
	public ItemOCEntity() {
		super();
	}
	
	public ItemOCEntity(ItemOC item) {
		this.setArticulo(new ArticuloEntity(item.getArticulo()));
		this.setCantidad(item.getCantidad());
	}
	
	public ArticuloEntity getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
