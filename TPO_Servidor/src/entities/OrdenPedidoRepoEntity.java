package entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import negocio.OrdenPedidoRepo;

@Entity
@Table(name= "OrdenesPedidoRepo")
public class OrdenPedidoRepoEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer numOrdenPR;
	private Date fechaGeneracion;
	private Integer numPedido;
	@OneToOne
	@JoinColumn(name= "codigoBarras")
	private ArticuloEntity articulo;
	private int cantRepo;
	private String estado;
	
	public OrdenPedidoRepoEntity() {}
	
	
	public OrdenPedidoRepoEntity(OrdenPedidoRepo opr) {
		this.setArticulo(new ArticuloEntity(opr.getArticulo()));
		this.setCantRepo(opr.getCantRepo());
		this.setFechaGeneracion(this.getFechaGeneracion());
		this.setEstado(opr.getEstado());
		this.setNumOrdenPR(opr.getNumOrdenPR());
		this.setNumPedido(opr.getNumPedido());
	}


	public Integer getNumOrdenPR() {
		return numOrdenPR;
	}


	public void setNumOrdenPR(Integer numOrdenPR) {
		this.numOrdenPR = numOrdenPR;
	}


	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}


	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}


	public Integer getNumPedido() {
		return numPedido;
	}


	public void setNumPedido(Integer numPedido) {
		this.numPedido = numPedido;
	}


	public ArticuloEntity getArticulo() {
		return articulo;
	}


	public void setArticulo(ArticuloEntity articulo) {
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
