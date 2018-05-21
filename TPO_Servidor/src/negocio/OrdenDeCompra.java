
package negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import dao.OrdenDeCompraDAO;
import dto.OrdenDeCompraDTO;
import entities.ItemOCEntity;
import entities.OrdenDeCompraEntity;
import entities.OrdenPedidoRepoEntity;

public class OrdenDeCompra {

	private int numOC;
	private Date fecha;
	private String proveedor;
	private List<OrdenPedidoRepo> ordenesPedidoRepo;
	private List<ItemOC> itemsOC;
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
		this.cargarListOC(oce.getIoe());
		this.cargarListOPRE(oce.getOpre());
	}
	
	private void cargarListOC(List<ItemOCEntity> ia){
		List<ItemOC> res= new ArrayList<ItemOC>();
		for(ItemOCEntity a: ia)
			res.add(new ItemOC(a));
		this.setItemsOC(res);
	}
	
	private void cargarListOPRE(List<OrdenPedidoRepoEntity> ia){
		List<OrdenPedidoRepo> res= new ArrayList<OrdenPedidoRepo>();
		for(OrdenPedidoRepoEntity a: ia)
			res.add(new OrdenPedidoRepo(a));
		this.setOrdenesPedidoRepo(res);
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
	
	
	
	public void setOrdenesPedidoRepo(List<OrdenPedidoRepo> ordenesPedidoRepo) {
		this.ordenesPedidoRepo = ordenesPedidoRepo;
	}

	public void setItemsOC(List<ItemOC> itemsOC) {
		this.itemsOC = itemsOC;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public List<OrdenPedidoRepo> getOrdenesPedidoRepo() {
		return ordenesPedidoRepo;
	}

	public List<ItemOC> getItemsOC() {
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
	
	public void updateMe() {
		OrdenDeCompraDAO.getInstance().update(this);
	}	

}
