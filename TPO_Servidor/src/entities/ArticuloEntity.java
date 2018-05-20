package entities;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Articulos")
public class ArticuloEntity {
	@Id
	private String codigoBarras;
	private String descripcion;
	private String presentacion;
	private int tamaño;
	private String unidad;
	private float precioVta;
	private int cantFijaCompra;
	private int cantMaxUbicacion;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name= "codigoBarras")
	private Set<ArticuloEnStockEntity> ase= new TreeSet<ArticuloEnStockEntity>();
	private char estado;
	
	public ArticuloEntity(String codigoBarras, String descripcion, String presentacion, int tamaño, String unidad,
			float precioVta, int cantFijaCompra, int cantMaxUbicacion, char estado) {
		this.codigoBarras = codigoBarras;
		this.descripcion = descripcion;
		this.presentacion = presentacion;
		this.tamaño = tamaño;
		this.unidad = unidad;
		this.precioVta = precioVta;
		this.cantFijaCompra = cantFijaCompra;
		this.cantMaxUbicacion = cantMaxUbicacion;
		this.estado = estado;
	}
	
	public ArticuloEntity(){}
	
	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public int getTamaño() {
		return tamaño;
	}

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public float getPrecioVta() {
		return precioVta;
	}

	public void setPrecioVta(float precioVta) {
		this.precioVta = precioVta;
	}

	public int getCantFijaCompra() {
		return cantFijaCompra;
	}

	public void setCantFijaCompra(int cantFijaCompra) {
		this.cantFijaCompra = cantFijaCompra;
	}

	public int getCantMaxUbicacion() {
		return cantMaxUbicacion;
	}

	public void setCantMaxUbicacion(int cantMaxUbicacion) {
		this.cantMaxUbicacion = cantMaxUbicacion;
	}

	public Set<ArticuloEnStockEntity> getAse() {
		return ase;
	}

	public void setAse(Set<ArticuloEnStockEntity> ase) {
		this.ase = ase;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}
	
	
}
