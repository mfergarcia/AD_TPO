//PENDIENTE: Programar todo
package negocio;

import java.util.Date;

import dto.ArticuloEnStockDTO;

public class ArticuloEnStock {

	//NOTAS_FG: Para que se necesita codigoBarras si el ArtEnStock solo se accede a traves
	//del articulo
	private int id;
	private String codigoBarras;
	private String codigoUbicacion;
	private int cantidad;
	private String lote;
	private Date fechaVencimiento;
	private Date fechaCompra;
	private String proveedor;
	private float precioCompra;

	public ArticuloEnStock() {
		// TODO Auto-generated constructor stub
	}
	
	public ArticuloEnStock(String codigoUbicacion, int cantidad, String lote, Date fechaVenc, Date fechaCompra, String proveedor, float precioCompra) {
		this.setCodigoUbicacion(codigoUbicacion);
		this.setCantidad(cantidad);
		this.setLote(lote);
		this.setFechaVencimiento(fechaVenc);
		this.setFechaCompra(fechaCompra);
		this.setProveedor(proveedor);
		this.setPrecioCompra(precioCompra);
	}
	
	// NOTAS_FG: Para qué sirve este metodo???
	public String verificarStockArticulo(String codBarras, int cantidad) {
		return null;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getCodigoUbicacion() {
		return codigoUbicacion;
	}

	public void setCodigoUbicacion(String codigoUbicacion) {
		this.codigoUbicacion = codigoUbicacion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public float getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public ArticuloEnStockDTO toDTO() {
		ArticuloEnStockDTO artEnStockDTO = new ArticuloEnStockDTO();
		artEnStockDTO.setId(this.getId());
		artEnStockDTO.setCodigoBarras(this.getCodigoBarras());
		artEnStockDTO.setCodigoUbicacion(this.getCodigoUbicacion());
		artEnStockDTO.setCantidad(this.getCantidad());
		artEnStockDTO.setLote(this.getLote());
		artEnStockDTO.setFechaVencimiento(this.getFechaVencimiento());
		artEnStockDTO.setFechaCompra(this.getFechaCompra());
		artEnStockDTO.setProveedor(this.getProveedor());
		artEnStockDTO.setPrecioCompra(this.getPrecioCompra());
		return artEnStockDTO;
	}
	
	//@Facu: implementar metodo
	public void saveMe() {

	}	
}
