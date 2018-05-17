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
	//private String codigoBarras;--Si tenemos el codigo no es mejor directamente el objeto Articulo
	//private String codigoUbicacion;--igual, no es mejor el Stock?
	private int cantidad;
	private String lote;
	private Date fechaVencimiento;
	private Date fechaCompra;
	private String proveedor;
	private float precioCompra;
	
	public ArticuloEnStockEntity() {}
	
}
