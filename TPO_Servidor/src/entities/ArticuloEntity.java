package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import negocio.Articulo;
import negocio.ArticuloEnStock;
import negocio.ItemArticulo;

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
	private List<ArticuloEnStockEntity> ase= new ArrayList<ArticuloEnStockEntity>();
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
	
	public ArticuloEntity(Articulo a) {
		this.codigoBarras = a.getCodigoBarras();
		this.descripcion = a.getDescripcion();
		this.presentacion = a.getPresentacion();
		this.tamaño = a.getTamaño();
		this.unidad = a.getUnidad();
		this.precioVta = a.getPrecioVta();
		this.cantFijaCompra = a.getCantFijaCompra();
		this.cantMaxUbicacion = a.getCantMaxUbicacion();
		this.estado = a.getEstado();
		this.cargarList(a.getArticulosEnStock());
	}

	private void cargarList(Collection<ArticuloEnStock> articulosEnStock) {
		List<ArticuloEnStockEntity> res= new ArrayList<ArticuloEnStockEntity>();
		for(ArticuloEnStock a: articulosEnStock)
			res.add(new ArticuloEnStockEntity(a));
		this.setAse(res);
	}

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

	public List<ArticuloEnStockEntity> getAse() {
		return ase;
	}

	public void setAse(List<ArticuloEnStockEntity> res) {
		this.ase = res;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}
	
	
}
