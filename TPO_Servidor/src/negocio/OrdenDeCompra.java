// @Facu: implementar metodo saveMe
package negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import dao.OrdenDeCompraDAO;
import dto.OrdenDeCompraDTO;
import entities.OrdenDeCompraEntity;

public class OrdenDeCompra {

	private int numOC;
	private Date fecha;
	private String proveedor;
	private Collection<OrdenPedidoRepo> ordenesPedidoRepo;
	private Collection<ItemOC> itemsOC;
	// estado: "PENDIENTE", "CUMPLIDA"
	private String estado;

	
	public OrdenDeCompra() {

	}

	// Crea una nueva OrdenDeCompra, con la fecha del día y setea el estado inicial
	public OrdenDeCompra(String proveedor) {
		this.setProveedor(proveedor);
		// Se genera con la fecha/hora del momento
		this.setFecha(Calendar.getInstance().getTime());
		this.ordenesPedidoRepo = new ArrayList<OrdenPedidoRepo>();
		this.itemsOC = new ArrayList<ItemOC>();
		this.setEstado("PENDIENTE");
	}

	public OrdenDeCompra(OrdenDeCompraEntity oce) {
		this.setEstado(oce.getEstado());
		this.setFecha(oce.getFecha());
		this.setNumOC(oce.getNumOC());
		this.setProveedor(oce.getProveedor());
	}

	// Valida que el objeto sea una determinada OrdenDeCompra
	public boolean sosOrdenC(int numOC) {
		return (this.getNumOC() == numOC);
	}
	
	public void agregarItem(ItemOC itemOC) {
		this.itemsOC.add(itemOC);		
	}

	public void agregarOrdenPedidoRepo(OrdenPedidoRepo ordenPedidoRepo) {
		this.ordenesPedidoRepo.add(ordenPedidoRepo);
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

	public Collection<OrdenPedidoRepo> getOrdenesPedidoRepo() {
		return ordenesPedidoRepo;
	}

	public Collection<ItemOC> getItemsOC() {
		return itemsOC;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public OrdenDeCompraDTO toDTO() {
		OrdenDeCompraDTO ordenDeCompraDTO = new OrdenDeCompraDTO();
		ordenDeCompraDTO.setNumOC(this.getNumOC());
		ordenDeCompraDTO.setFecha(this.getFecha());
		ordenDeCompraDTO.setProveedor(this.getProveedor());
		ordenDeCompraDTO.setEstado(this.getEstado());
		ItemOC aux;
		for (Iterator<ItemOC> i = this.getItemsOC().iterator(); i.hasNext(); ) {
			aux = i.next();
			ordenDeCompraDTO.agregarItem(aux.toDTO());
		}
		return ordenDeCompraDTO;
	}
	
	public void saveMe() {
		this.setNumOC(OrdenDeCompraDAO.getInstance().grabar(this));
	}	
	
}
