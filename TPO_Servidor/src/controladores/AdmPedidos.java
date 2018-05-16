//PENDIENTE: Completar programacion y desde VERRRR
package controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import dto.ItemArticuloDTO;
import dto.PedidoDTO;
import negocio.Articulo;
import negocio.Direccion;
import negocio.Factura;
import negocio.ItemArticulo;
import negocio.Pedido;

public class AdmPedidos {

	private static AdmPedidos instancia;
	// @Facu: remover la colección cuando se implemente la búsqueda en la BD
	private Collection<Pedido> pedidos;
	
	// Constructor privado (Patron Singleton)
	private AdmPedidos() {

	}

	// @Facu: reemplazar búsqueda en la colección por búsqueda en la BD
	// Devuelve los pedidos cuyo estado coincide con el estado solicitado
	private Collection<Pedido> obtenerPedidosPorEstado(String estado) {
		Collection<Pedido> pedidos = new ArrayList<Pedido>();
		Pedido aux;
		for (Iterator<Pedido> i = this.pedidos.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getEstado().equals(estado)) {
				pedidos.add(aux);
			}
		}
		return pedidos;
	}

	// @Facu: reemplazar búsqueda en la colección por búsqueda en la BD
	// Ubica un determinado Pedido dentro de la colleción pedidos
	private Pedido obtenerPedido(int numPedido) {
		Pedido aux;
		for (Iterator<Pedido> i = this.pedidos.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getNumPedido() == numPedido) {
				return aux;
			}
		}
		return null;
	}
	
	public static AdmPedidos getInstancia() {
		if (instancia == null) {
			instancia = new AdmPedidos();
		}
		return instancia;
	}	

	// @Facu: revisar implementación del saveMe (Pedido se guarda con los items)
	// Crea un nuevo Pedido con sus correspondientes items 
	public Pedido generarPedido(PedidoDTO pedidoDTO) {
		Direccion direccion = new Direccion();
		direccion.setCalle(pedidoDTO.getDirEntrega().getCalle());
		direccion.setNumero(pedidoDTO.getDirEntrega().getNumero());
		direccion.setCodigoPostal(pedidoDTO.getDirEntrega().getCodigoPostal());
		direccion.setLocalidad(pedidoDTO.getDirEntrega().getLocalidad());
		Pedido pedido = new Pedido(pedidoDTO.getIdCliente(), direccion);
		ItemArticuloDTO aux;
		for (Iterator<ItemArticuloDTO> i = pedidoDTO.getItems().iterator(); i.hasNext(); ) {
			aux = i.next();
			Articulo articulo = AdmStock.getInstancia().obtenerArticulo(aux.getArticuloDTO().getCodigoBarras());
			ItemArticulo itemArticulo = new ItemArticulo(articulo, aux.getCant(), aux.getPrecioVta());
			pedido.agregarItem(itemArticulo);
		}
		pedido.saveMe();
		return pedido;
	}
	
	// @Facu: reemplazar búsqueda en la colección por búsqueda en la BD
	// Devuelve una coleccion con los Pedidos correspondiente al idCliente dado
	public Collection<Pedido> obtenerPedidosPorCliente(int idCliente) {
		Collection<Pedido> pedidosCliente = new ArrayList<Pedido>();
		Pedido aux;
		for(Iterator<Pedido> i = this.pedidos.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getIdCliente() == idCliente)
				pedidosCliente.add(aux);
		}
		return pedidosCliente;
	}

	// Devuelve los pedidos en estado "A CONFIRMAR"
	public Collection<Pedido> obtenerPedidosAConfirmar() {
		return obtenerPedidosPorEstado("A CONFIRMAR");
	}
	
	public String aprobarPedido(int numPedido) {
		Pedido pedido = obtenerPedido(numPedido);
		String nuevoEstadoPedido;
		if (pedido != null) {
			nuevoEstadoPedido = AdmStock.getInstancia().reservarStockPedido(pedido);
			pedido.setEstado(nuevoEstadoPedido);
			return nuevoEstadoPedido;
		}
		else
			return null;
	}

	public String rechazarPedido(int numPedido, String motivo) {
		Pedido pedido = obtenerPedido(numPedido);
		if (pedido != null) {
			pedido.setEstado("RECHAZADO");
			pedido.setMotivoRechazo(motivo);
			return pedido.getMotivoRechazo();
		}
		else
			return null;
	}
	
	public Collection<Pedido> obtenerPedidosCompletos() {
		return obtenerPedidosPorEstado("COMPLETO");
	}
	
	public String solicitarPedido(int numPedido) {
		Pedido pedido = obtenerPedido(numPedido);
		if (pedido != null) {
			Factura facturaPedido = AdmFacturacion.getInstancia().facturar(pedido);
			if (facturaPedido != null) {
				pedido.setTipoFactura(facturaPedido.getTipoFactura());
				pedido.setNumFactura(facturaPedido.getNumFactura());
				pedido.setEstado("PENDIENTE DEPOSITO");
				return pedido.getEstado();
			}
			else
				return null;
		}
		else 
			return null;
	}

	public Collection<Pedido> obtenerPedidosADespachar() {
		return obtenerPedidosPorEstado("PENDIENTE DESPACHO");
	}
	
	public String registrarFechaEntrega(int numPedido, Date fechaEntrega) {
		Pedido pedido = obtenerPedido(numPedido);
		if (pedido != null) {
			pedido.setFechaEntrega(fechaEntrega);
			pedido.setEstado("DESPACHADO");
			return pedido.getEstado();
		}
		else
			return null;
	}
	
	public Collection<Pedido> obtenerPedidosPendDeposito() {
		return obtenerPedidosPorEstado("PENDIENTE DEPOSITO");
	}
}
