// LISTO PARA PROBAR
package dto;

import java.io.Serializable;
import java.util.Date;

public class ArticuloEnStockDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String codigoBarras;
	private String codigoUbicacion;
	private int cantidad;
	private String lote;
	private Date fechaVencimiento;
	private Date fechaCompra;
	private String proveedor;
	private float precioCompra;
	
	public ArticuloEnStockDTO() {

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

}
