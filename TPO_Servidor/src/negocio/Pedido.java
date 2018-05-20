//@Facu: implementar metodo saveMe
package negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import dao.PedidoDAO;
import dto.PedidoDTO;
import entities.PedidoEntity;

public class Pedido {

	private int numPedido;
	private int idCliente;
	private Date fechaGen;
	private Direccion dirEntrega;
	private Date fechaEntrega;
	private Collection<ItemArticulo> articulos;
	private char tipoFactura;
	private int numFactura;
	private String motivoRechazo;
	// estado: "A CONFIRMAR", "RECHAZADO", "PENDIENTE REPOSICION", "COMPLETO", "PENDIENTE DEPOSITO", "PENDIENTE DESPACHO", "DESPACHADO"
	private String estado;	
	
	public Pedido() {

	}

	// Creación de un nuevo Pedido, inicializa la coleccion de items y setea estado inicial
	public Pedido(int idCliente, Direccion dirEntrega) {
		this.setIdCliente(idCliente);
		this.setDirEntrega(dirEntrega);
		// Se genera con la fecha/hora del momento
		this.setFechaGen(Calendar.getInstance().getTime());
		this.articulos = new ArrayList<ItemArticulo>();
		this.setEstado("A CONFIRMAR");
	}
	
	public Pedido(PedidoEntity pe) {
		this.setIdCliente(pe.getIdCliente());
		this.setDirEntrega(new Direccion(pe.getDirEntrega()));
		this.setEstado(pe.getEstado());
		this.setFechaEntrega(pe.getFechaEntrega());
		this.setFechaGen(pe.getFechaGen());
		this.setMotivoRechazo(pe.getMotivoRechazo());
		this.setNumFactura(pe.getNumFactura());
		this.setNumPedido(pe.getNumPedido());
		this.setTipoFactura(pe.getTipoFactura());
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

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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

	public char getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(char tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public int getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(int numFactura) {
		this.numFactura = numFactura;
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

	public Collection<ItemArticulo> getArticulos() {
		return articulos;
	}

	public PedidoDTO toDTO() {
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setNumPedido(this.getNumPedido());
		pedidoDTO.setIdCliente(this.getIdCliente());
		pedidoDTO.setFechaGen(this.getFechaGen());
		pedidoDTO.setDirEntrega(this.getDirEntrega().toDTO());
		pedidoDTO.setFechaEntrega(this.getFechaEntrega());
		pedidoDTO.setTipoFactura(this.getTipoFactura());
		pedidoDTO.setNumFactura(this.getNumFactura());
		pedidoDTO.setMotivoRechazo(this.getMotivoRechazo());
		pedidoDTO.setEstado(this.getEstado());
		ItemArticulo aux;
		for(Iterator<ItemArticulo> i = this.getArticulos().iterator(); i.hasNext(); ) {
			aux = i.next();
			pedidoDTO.agregarItem(aux.toDTO());
		}
		return pedidoDTO;
	}
	
	public void saveMe() {
		PedidoDAO.getIntance().grabar(this);
	}	
	
	
}
