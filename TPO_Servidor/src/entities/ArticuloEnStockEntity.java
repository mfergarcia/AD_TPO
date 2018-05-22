package entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import negocio.ArticuloEnStock;

@Entity
@Table(name= "ArticulosEnStock")
public class ArticuloEnStockEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String codigoBarras;
	private String codigoUbicacion;
	private int cantidad;
	private String lote;
	private Date fechaVencimiento;
	private Date fechaCompra;
	private String proveedor;
	private float precioCompra;
	
	public ArticuloEnStockEntity() {}
	
	public ArticuloEnStockEntity(Integer id, String codigoBarras, String codigoUbicacion, int cantidad, String lote,
								Date fechaVencimiento, Date fechaCompra, String proveedor, float precioCompra) {
		this.setId(id);
		this.setCodigoBarras(codigoBarras);
		this.setCodigoUbicacion(codigoUbicacion);
		this.setCantidad(cantidad);
		this.setLote(lote);
		this.setFechaVencimiento(fechaVencimiento);
		this.setFechaCompra(fechaCompra);
		this.setProveedor(proveedor);
		this.setPrecioCompra(precioCompra);
	}
	
	public ArticuloEnStockEntity(ArticuloEnStock artEnStock) {
		if(artEnStock.getId()!=0)
			this.setId(artEnStock.getId());
		this.setCodigoBarras(artEnStock.getCodigoBarras());
		this.setCodigoUbicacion(artEnStock.getCodigoUbicacion());
		this.setCantidad(artEnStock.getCantidad());
		this.setLote(artEnStock.getLote());
		this.setFechaVencimiento(artEnStock.getFechaVencimiento());
		this.setFechaCompra(artEnStock.getFechaCompra());
		this.setProveedor(artEnStock.getProveedor());
		this.setPrecioCompra(artEnStock.getPrecioCompra());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
