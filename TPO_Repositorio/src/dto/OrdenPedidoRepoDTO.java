// PENDIENTE
package dto;

import java.io.Serializable;
import java.util.Date;

public class OrdenPedidoRepoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numOrdenPR;
	private Date fechaGeneracion;
	private int numPedido;
	private ArticuloDTO articulo;
	private int cantRepo;
	// estado: "PENDIENTE", "OC EN PROCESO", "CUMPLIDA"
	private String estado;	

	public OrdenPedidoRepoDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getNumOrdenPR() {
		return numOrdenPR;
	}

	public void setNumOrdenPR(int numOrdenPR) {
		this.numOrdenPR = numOrdenPR;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public int getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}

	public ArticuloDTO getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}

	public int getCantRepo() {
		return cantRepo;
	}

	public void setCantRepo(int cantRepo) {
		this.cantRepo = cantRepo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
