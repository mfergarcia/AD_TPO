package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import negocio.OrdenDeCompra;

@Entity
@Table(name="OrdenesDeCompra")
public class OrdenDeCompraEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer numOC;
	private Date fecha;
	private String proveedor;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name= "numOc")
	List<OrdenPedidoRepoEntity> opre;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name= "numOc")
	List<ItemOCEntity> ioe;
	private String estado;
	
	public OrdenDeCompraEntity(OrdenDeCompra oc) {//Cargar Lista?(si es necesario, metodos a parte para convertir de normal a entity)
		this.setEstado(oc.getEstado());
		this.setFecha(oc.getFecha());
		this.setProveedor(oc.getProveedor());
	}
	
	
	public OrdenDeCompraEntity() {}



	public Integer getNumOC() {
		return numOC;
	}

	public void setNumOC(Integer numOC) {
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

	public List<OrdenPedidoRepoEntity> getOpre() {
		return opre;
	}

	public void setOpre(List<OrdenPedidoRepoEntity> opre) {
		this.opre = opre;
	}

	public List<ItemOCEntity> getIoe() {
		return ioe;
	}

	public void setIoe(List<ItemOCEntity> ioe) {
		this.ioe = ioe;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
