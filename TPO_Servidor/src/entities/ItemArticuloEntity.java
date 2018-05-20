package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ItemsArticulo")
public class ItemArticuloEntity {
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name= "codigoBarras")
	private ArticuloEntity ae;
	private int cant;
	private float precioVta;
	private String estadoStock;
	
	public ItemArticuloEntity(ArticuloEntity ae, int cant, float precioVta, String estadoStock) {
		super();
		this.ae = ae;
		this.cant = cant;
		this.precioVta = precioVta;
		this.estadoStock = estadoStock;
	}
	public ItemArticuloEntity(){}
	
	public ArticuloEntity getAe() {
		return ae;
	}

	public void setAe(ArticuloEntity ae) {
		this.ae = ae;
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
