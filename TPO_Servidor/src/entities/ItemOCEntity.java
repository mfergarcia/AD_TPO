package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import negocio.ItemOC;

@Entity
@Table(name= "itemsOC")
public class ItemOCEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
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
		this.setId(item.getId());
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

	public int getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
