//LISTO PARA PROBAR
package negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import dto.PedidoDTO;

public class Pedido {

	private int numPedido;
	//NOTA_FG: Entiendo que no se puede guardar el cliente por la herencia
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
		// TODO Auto-generated constructor stub
	}

	// Creación de un nuevo Pedido, inicializa la coleccion de items y setea estado inicial
	public Pedido(int numPedido, int idCliente, Direccion dirEntrega) {
		this.setNumPedido(numPedido);
		this.setIdCliente(idCliente);
		this.setDirEntrega(dirEntrega);
		// Se genera con la fecha/hora del momento
		this.setFechaGen(Calendar.getInstance().getTime());
		this.articulos = new ArrayList<ItemArticulo>();
		this.setEstado("A CONFIRMAR");
	}
	
	// Permite agregar los items del Pedido
	public void agregarItem(ItemArticulo itemArticulo) {
		this.getArticulos().add(itemArticulo);
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

}
