package negocio;

import java.util.Calendar;
import java.util.Date;

import dao.OrdenPedidoRepoDAO;
import dto.OrdenPedidoRepoDTO;
import entities.OrdenPedidoRepoEntity;

public class OrdenPedidoRepo {

	private int numOrdenPR;
	private Date fechaGeneracion;
	private int numPedido;
	private Articulo articulo;
	private int cantRepo;
	// estado: "PENDIENTE", "OC EN PROCESO", "CUMPLIDA"
	private String estado;	
	
	public OrdenPedidoRepo() {

	}

	// Crea una nueva Orden de Pedido Repo con la fecha del día y setea estado inicial
	public OrdenPedidoRepo(int numPedido, Articulo articulo, int cantidad) {
		this.setNumPedido(numPedido);
		this.setArticulo(articulo);
		this.setCantRepo(cantidad);
		// Se genera con la fecha/hora del momento
		this.setFechaGeneracion(Calendar.getInstance().getTime());
		this.setEstado("PENDIENTE");
	}

	public OrdenPedidoRepo(OrdenPedidoRepoEntity o) {
		this.setArticulo(new Articulo(o.getArticulo()));
		this.setCantRepo(o.getCantRepo());
		this.setEstado(o.getEstado());
		this.setFechaGeneracion(o.getFechaGeneracion());
		this.setNumOrdenPR(o.getNumOrdenPR());
		this.setNumPedido(o.getNumPedido());
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
		OrdenPedidoRepoDTO ordenPRDTO = new OrdenPedidoRepoDTO();
		ordenPRDTO.setNumOrdenPR(this.getNumOrdenPR());
		ordenPRDTO.setFechaGeneracion(this.getFechaGeneracion());
		ordenPRDTO.setNumPedido(this.getNumPedido());
		ordenPRDTO.setArticulo(this.getArticulo().toDTO());
		ordenPRDTO.setCantRepo(this.getCantRepo());
		ordenPRDTO.setEstado(this.getEstado());
		return ordenPRDTO;
	}

	public void saveMe() {
		this.setNumOrdenPR(OrdenPedidoRepoDAO.getInstance().grabar(this));
	}	
	
	public void updateMe() {
		OrdenPedidoRepoDAO.getInstance().update(this);
	}	

}
