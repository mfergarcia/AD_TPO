
package negocio;

import java.util.Calendar;
import java.util.Date;

import dao.ArticuloEnStockDAO;
import dto.ArticuloEnStockDTO;
import entities.ArticuloEnStockEntity;

public class ArticuloEnStock {

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

	}
	
	public ArticuloEnStock(ArticuloEnStockEntity ase) {
		this.setId(ase.getId());
		this.setCodigoBarras(ase.getCodigoBarras());
		this.setCodigoUbicacion(ase.getCodigoUbicacion());
		this.setCantidad(ase.getCantidad());
		this.setLote(ase.getLote());
		this.setFechaVencimiento(ase.getFechaVencimiento());
		this.setFechaCompra(ase.getFechaCompra());
		this.setPrecioCompra(ase.getPrecioCompra());
		this.setProveedor(ase.getProveedor());
		this.setPrecioCompra(ase.getPrecioCompra());
	}
	
	public ArticuloEnStock(String codigoUbicacion, int cantidad, ArticuloEnStockDTO artEnStockDTO) {
		this.setCodigoBarras(artEnStockDTO.getCodigoBarras());
		this.setCodigoUbicacion(codigoUbicacion);
		this.setCantidad(cantidad);
		this.setLote(artEnStockDTO.getLote());
		this.setFechaVencimiento(artEnStockDTO.getFechaVencimiento());
		this.setFechaCompra(Calendar.getInstance().getTime());
		this.setProveedor(artEnStockDTO.getProveedor());
		this.setPrecioCompra(artEnStockDTO.getPrecioCompra());
	}
	
	public ArticuloEnStock(String codBarras) {
		this.codigoBarras=codBarras;
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
	
	public void saveMe() {
		this.setId(ArticuloEnStockDAO.getInstance().grabar(this));
	}	

	public void updateMe() {
		ArticuloEnStockDAO.getInstance().update(this);
	}	

}
