// @Facu: revisar búsquedas en BD e implementar metodo saveMe
package negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import dao.ArticuloDAO;
import dto.ArticuloDTO;

public class Articulo {

	private String codigoBarras;
	private String descripcion;
	private String presentacion;
	private int tamaño;
	private String unidad;
	private float precioVta;
	private int cantFijaCompra;
	private int cantMaxUbicacion;
	private Collection<ArticuloEnStock> articulosEnStock;
	// estado: 'A' (activo), 'I' (inactivo)
	private char estado;
	
	public Articulo() {

	}

	public Articulo(ArticuloDTO articuloDTO) {
		this.setCodigoBarras(articuloDTO.getCodigoBarras());
		this.setDescripcion(articuloDTO.getDescripcion());
		this.setPresentacion(articuloDTO.getPresentacion());
		this.setTamaño(articuloDTO.getTamaño());
		this.setUnidad(articuloDTO.getUnidad());
		this.setPrecioVta(articuloDTO.getPrecioVta());
		this.setCantFijaCompra(articuloDTO.getCantFijaCompra());
		this.setCantMaxUbicacion(articuloDTO.getCantMaxUbicacion());
		this.articulosEnStock = new ArrayList<ArticuloEnStock>();
		this.setEstado('A');
	}
	
	// Valida que el objeto sea un determinado articulo
	public boolean sosArticulo(String codBarra) {
		return (codBarra == this.getCodigoBarras());
	}

	// Agrega un Articulo en Stock
	public void agregarArtEnStock(ArticuloEnStock artEnStock) {
		this.articulosEnStock.add(artEnStock);
	}

	// @Facu: Reemplazar esta búsqueda por búsqueda en la BD. La búsqueda tiene 
	// que levantar todos los Articulo En Stock de este Articulo cuyo codUbicación sea <> null
	// El resultado tiene que volver ordenado por Fecha de Vencimiento (primero los más viejos)
	public Collection<ArticuloEnStock> obtenerArtEnStockOrdenFV() {
		return this.getArticulosEnStock();
	}
	
	// @Facu: reemplazar búsqueda en la colección por búsqueda en la BD
	public ArticuloEnStock obtenerArtEnStock(int id) {
		ArticuloEnStock aux;
		for (Iterator<ArticuloEnStock> i = this.getArticulosEnStock().iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getId() == id)
				return aux;
		}
		return null;
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

	public Collection<ArticuloEnStock> getArticulosEnStock() {
		return this.articulosEnStock;
	}
	
	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}
	
	public ArticuloDTO toDTO() {
		ArticuloDTO articuloDTO = new ArticuloDTO();
		articuloDTO.setCodigoBarras(this.getCodigoBarras());
		articuloDTO.setDescripcion(this.getDescripcion());
		articuloDTO.setPresentacion(this.getPresentacion());
		articuloDTO.setTamaño(this.getTamaño());
		articuloDTO.setUnidad(this.getUnidad());
		articuloDTO.setPrecioVta(this.getPrecioVta());
		articuloDTO.setCantFijaCompra(this.getCantFijaCompra());
		articuloDTO.setCantMaxUbicacion(this.getCantMaxUbicacion());
		articuloDTO.setEstado(this.getEstado());
		return articuloDTO;
		
	}
	
	//@Facu: implementar metodo
	public void saveMe() {
		ArticuloDAO.getInstance().grabar(this);
	}	

}
