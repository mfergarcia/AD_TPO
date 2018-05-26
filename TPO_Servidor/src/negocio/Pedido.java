package negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import dao.PedidoDAO;
import dto.PedidoDTO;
import entities.ItemArticuloEntity;
import entities.PedidoEntity;

public class Pedido {

	private int numPedido;
	private Cliente cliente;
	private Date fechaGen;
	private Direccion dirEntrega;
	private Date fechaEntrega;
	private List<ItemArticulo> articulos;
	private Factura factura;
	private String motivoRechazo;
	// estado: "A CONFIRMAR", "RECHAZADO", "PENDIENTE REPOSICION", "COMPLETO", "PENDIENTE DEPOSITO", "PENDIENTE DESPACHO", "DESPACHADO"
	private String estado;	
	
	public Pedido() {

	}

	// Creación de un nuevo Pedido, inicializa la coleccion de items y setea estado inicial
	public Pedido(Cliente cliente, Direccion dirEntrega) {
		this.setCliente(cliente);
		this.setDirEntrega(dirEntrega);
		// Se genera con la fecha/hora del momento
		this.setFechaGen(Calendar.getInstance().getTime());
		this.articulos = new ArrayList<ItemArticulo>();
		this.setEstado("A CONFIRMAR");
	}
	
	// @Facu: adaptar PedidoEntity, ahora guarda el objeto Cliente y el objeto Factura
	public Pedido(PedidoEntity pe) {
		// @Facu: adaptar PedidoEntity
		// this.setIdCliente(pe.getIdCliente());
		this.setDirEntrega(new Direccion(pe.getDirEntrega()));
		this.setEstado(pe.getEstado());
		this.setFechaEntrega(pe.getFechaEntrega());
		this.setFechaGen(pe.getFechaGen());
		this.setMotivoRechazo(pe.getMotivoRechazo());
		// @Facu: adaptar PedidoEntity
		// this.setNumFactura(pe.getNumFactura());
		if(pe.getNumPedido()>0)
			this.setNumPedido(pe.getNumPedido());
		// @Facu: adaptar PedidoEntity
		// this.setTipoFactura(pe.getTipoFactura());
		this.cargarList(pe.getIae());
	}
	
	
	private void cargarList(List<ItemArticuloEntity> ia){
		List<ItemArticulo> res= new ArrayList<ItemArticulo>();
		for(ItemArticuloEntity a: ia)
			res.add(new ItemArticulo(a));
		this.setArticulos(res);
	}
	
	// Permite agregar los items del Pedido
	public void agregarItem(ItemArticulo itemArticulo) {
		this.articulos.add(itemArticulo);
	}
	
	// Calcula el monto total del Pedido, sumarizando el monto de cada item
	public float calcularTotal() {
		float total = 0;
		ItemArticulo aux;
		for (Iterator<ItemArticulo> i = this.getArticulos().iterator(); i.hasNext(); ) {
			aux = i.next();
			total = total + aux.calcularTotal();
		}
		return total;
	}

	public int getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public void setArticulos(List<ItemArticulo> articulos) {
		this.articulos = articulos;
	}

	public Date getFechaGen() {
		return fechaGen;
	}

	public void setFechaGen(Date fechaGen) {
		this.fechaGen = fechaGen;
	}

	public Direccion getDirEntrega() {
		return dirEntrega;
	}

	public void setDirEntrega(Direccion dirEntrega) {
		this.dirEntrega = dirEntrega;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}	
	
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<ItemArticulo> getArticulos() {
		return articulos;
	}
	
	public PedidoDTO toDTO() {
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setNumPedido(this.getNumPedido());
		pedidoDTO.setIdCliente(this.getCliente().getIdCliente());
		pedidoDTO.setFechaGen(this.getFechaGen());
		pedidoDTO.setDirEntrega(this.getDirEntrega().toDTO());
		pedidoDTO.setFechaEntrega(this.getFechaEntrega());
		pedidoDTO.setFactura(this.getFactura().toDTO());
		pedidoDTO.setMotivoRechazo(this.getMotivoRechazo());
		pedidoDTO.setEstado(this.getEstado());
		ItemArticulo aux=new ItemArticulo();
		for(Iterator<ItemArticulo> i = this.getArticulos().iterator(); i.hasNext(); ) {
			aux = i.next();
			pedidoDTO.agregarItem(aux.toDTO());
		}
		return pedidoDTO;
	}
	
	public void saveMe() {
		this.setNumPedido(PedidoDAO.getIntance().grabar(this));
	}	
	

	public void updateMe() {
		PedidoDAO.getIntance().update(this);
	}	

}
