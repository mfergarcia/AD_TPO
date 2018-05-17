//PENDIENTE: Completar programacion y desde VERRRR
package controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import dto.ArticuloEnStockDTO;
import dto.ItemArticuloDTO;
import dto.PedidoDTO;
import negocio.Articulo;
import negocio.ArticuloEnStock;
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
		//@Facu: remover llamada cuando funcione la búsqueda en la BD
		this.pedidos = new ArrayList<Pedido>();
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
		return this.obtenerPedidosPorEstado("A CONFIRMAR");
	}
	
	// @Facu validar si está ok el saveMe
	public String aprobarPedido(int numPedido) {
		Pedido pedido = this.obtenerPedido(numPedido);
		if (pedido != null) {
			String nuevoEstadoPedido = AdmStock.getInstancia().reservarStockPedido(pedido);
			pedido.setEstado(nuevoEstadoPedido);
			pedido.saveMe();
			return nuevoEstadoPedido;
		}
		else
			return null;
	}

	// @Facu validar si está ok el saveMe	
	public String rechazarPedido(int numPedido, String motivo) {
		Pedido pedido = obtenerPedido(numPedido);
		if (pedido != null) {
			pedido.setEstado("RECHAZADO");
			pedido.setMotivoRechazo(motivo);
			pedido.saveMe();
			return pedido.getEstado();
		}
		else
			return null;
	}
	
	public Collection<Pedido> obtenerPedidosCompletos() {
		return this.obtenerPedidosPorEstado("COMPLETO");
	}
	
	// @Facu: revisar el uso del saveMe.
	public String solicitarPedido(int numPedido) {
		Pedido pedido = this.obtenerPedido(numPedido);
		if (pedido != null) {
			Factura factura = AdmFacturacion.getInstancia().facturar(pedido);
			if (factura != null) {
				pedido.setTipoFactura(factura.getTipoFactura());
				pedido.setNumFactura(factura.getNumFactura());
				pedido.setEstado("PENDIENTE DEPOSITO");
				pedido.saveMe();
				return pedido.getEstado();
			}
			else
				return null;
		}
		else 
			return null;
	}

	public Collection<Pedido> obtenerPedidosPendDeposito() {
		return this.obtenerPedidosPorEstado("PENDIENTE DEPOSITO");
	}
	
	public Collection<ArticuloEnStock> prepararPedido(int numPedido) {
		Collection<ArticuloEnStock> artEnStock = new ArrayList<ArticuloEnStock>();
		Pedido pedido = this.obtenerPedido(numPedido);
		if (pedido!= null) {
			ItemArticulo aux;
			for (Iterator<ItemArticulo> i = pedido.getArticulos().iterator(); i.hasNext(); ) {
				aux = i.next();
				artEnStock.addAll(AdmStock.getInstancia().localizarStockArticulo(aux.getArticulo(), aux.getCant()));
			}
			return artEnStock;
		}
		return null;
	}
	
	// NOTAS_FG: Pendiente de programar
	public String actualizarStockPorVenta(int numPedido, Collection<ArticuloEnStockDTO> artEnStockDTO) {
		Pedido pedido = this.obtenerPedido(numPedido);
		if (pedido != null) {
			// Aquí se llama a la actualización del stock
			pedido.setEstado("PENDIENTE DESPACHO");
			pedido.saveMe();
			return pedido.getEstado();
		}
		else
			return null;
	}


	public Collection<Pedido> obtenerPedidosADespachar() {
		return this.obtenerPedidosPorEstado("PENDIENTE DESPACHO");
	}
	
	public String registrarFechaEntrega(int numPedido, Date fechaEntrega) {
		Pedido pedido = this.obtenerPedido(numPedido);
		if (pedido != null) {
			pedido.setFechaEntrega(fechaEntrega);
			pedido.setEstado("DESPACHADO");
			pedido.saveMe();
			return pedido.getEstado();
		}
		else
			return null;
	}
	
}
