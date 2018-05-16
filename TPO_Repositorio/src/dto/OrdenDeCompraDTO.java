package dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class OrdenDeCompraDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numOC;
	private Date fecha;
	private String proveedor;
	private String[] codBarrasItems;
	private int[] cantidades;
	// estado: "PENDIENTE", "CUMPLIDA"
	private String estado;

	public OrdenDeCompraDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getNumOC() {
		return numOC;
	}

	public void setNumOC(int numOC) {
		this.numOC = numOC;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String[] getCodBarrasItem() {
		return codBarrasItems;
	}

	public void setCodBarrasItem(String[] codBarrasItem) {
		this.codBarrasItems = codBarrasItem;
	}

	public int[] getCantidades() {
		return cantidades;
	}

	public void setCantidades(int[] cantidades) {
		this.cantidades = cantidades;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
