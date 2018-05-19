//PENDIENTE: Revisar la parte de Articulo en Stock
package negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
		// TODO Auto-generated constructor stub
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
	

	// NOTAS_FG: El Articulo En Stock debería crearse con la ubicacion, sino no hay forma de trackearlo despues
	// en el stock
	public void agregarArtEnStock(String codUbicacion, int cantidad, String lote, Date fecVenc, Date fecCompra, String proveedor, float precioCompra) {
		ArticuloEnStock artEnStock = new ArticuloEnStock();
		artEnStock.setCodigoUbicacion(codUbicacion);
		artEnStock.setCantidad(cantidad);
		artEnStock.setLote(lote);
		artEnStock.setFechaVencimiento(fecVenc);
		artEnStock.setFechaCompra(fecCompra);
		artEnStock.setProveedor(proveedor);
		artEnStock.setPrecioCompra(precioCompra);
		this.getArticulosEnStock().add(artEnStock);
	}

	// Debe recorrer la coleccion de ArticulosEnStock y ordenarla por Fecha de Vencimiento del Lote
	// NOTA_FG: Creo que aquí es más fácil tirar una consulta a la BD que vuelva ordenada por 
	// fecha de vencimiento y que se rearme la colección con dicho resultado
	public void ordenarArtEnStockPorFechVenc() {
		
	}

	public void eliminarArtEnStock(ArticuloEnStock artEnStock){
		this.getArticulosEnStock().remove(artEnStock);
	}
	
	// Debe recorrer la coleccion de ArticulosEnStock y acumular los ultimos 3 proveedores
	public String[] consultarUltimosProv() {
		return null;
	}

	//NOTAS_FG: Este metodo para qué sirve? Imposible identificar un articulo por un lote (lote no es identificador univoco)
	public ArticuloEnStock buscarArtEnS(String lote) {
		return null;
	}
	
	//NOTAS_FG: Este metodo para qué sirve? las propiedades del ArticuloEnStock las hace la clase ArticuloEnStock
	public void modificarArticuloEnStock(String lote, Date venc, Date fCompra, String proov, float preCom) {
		
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
