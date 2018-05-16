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
	
	
}
