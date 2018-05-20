package entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "ArticulosEnStock")
public class ArticuloEnStockEntity {
	@Id
	Integer id;
	String codigoBarras;
	String codigoUbicacion;
	private int cantidad;
	private String lote;
	private Date fechaVencimiento;
	private Date fechaCompra;
	private String proveedor;
	private float precioCompra;
	
	public ArticuloEnStockEntity() {}
	
}
