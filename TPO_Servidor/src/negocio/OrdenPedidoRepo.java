//PENDIENTE: Programar método toDTO
package negocio;

import java.util.Calendar;
import java.util.Date;

import dto.OrdenPedidoRepoDTO;

public class OrdenPedidoRepo {

	private int numOrdenPR;
	private Date fechaGeneracion;
	private int numPedido;
	private Articulo articulo;
	private int cantRepo;
	// estado: "PENDIENTE", "OC EN PROCESO", "CUMPLIDA"
	private String estado;	
	
	public OrdenPedidoRepo() {
		// TODO Auto-generated constructor stub
	}

	// Crea una nueva Orden de Pedido Repo con la fecha del día y setea estado inicial
	public OrdenPedidoRepo(int numOrdenPR, int numPedido, Articulo articulo, int cantidad) {
		this.setNumOrdenPR(numOrdenPR);
		this.setNumPedido(numPedido);
		this.setArticulo(articulo);
		this.setCantRepo(cantidad);
		// Se genera con la fecha/hora del momento
		this.setFechaGeneracion(Calendar.getInstance().getTime());
		this.setEstado("PENDIENTE");
	}

	// Valida que el objeto sea determinada OrdenDePedido
	public boolean sosOrdenPedidoRepo(int numOrdenPR) {
		return (this.getNumOrdenPR() == numOrdenPR);
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

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
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
	
	public OrdenPedidoRepoDTO toDTO() {
		return null;
	}

}
