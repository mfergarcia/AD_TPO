package controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import dao.PedidoDAO;
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
	// @Facu: remover la colecci�n cuando se implemente la b�squeda en la BD
	// private Collection<Pedido> pedidos;
	
	// Constructor privado (Patron Singleton)
	private AdmPedidos() {
		// @Facu: remover llamada cuando funcione la b�squeda en la BD
		// this.pedidos = new ArrayList<Pedido>();
	}

	// Ubica un determinado Pedido dentro de la colleci�n pedidos
	private Pedido obtenerPedido(int numPedido) {
		return PedidoDAO.getIntance().findByID(numPedido);
	}
	
	// Devuelve los pedidos cuyo estado coincide con el estado solicitado
	private Collection<Pedido> obtenerPedidosPorEstado(String estado) {
		return PedidoDAO.getIntance().AllByEstado(estado);
	}
	
	public static AdmPedidos getInstancia() {
		if (instancia == null) {
			instancia = new AdmPedidos();
		}
		return instancia;
	}	

	// @Facu: revisar implementaci�n del saveMe (Pedido se guarda con los items, mismo caso que 
	// que Cliente y Cta Cte)
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
			ItemArticulo itemArticulo = new ItemArticulo(articulo, aux.getCant(), articulo.getPrecioVta());
			pedido.agregarItem(itemArticulo);
		}
		pedido.saveMe();
		return pedido;
	}
	
	// Devuelve una coleccion con los Pedidos correspondiente al idCliente dado
	public Collection<Pedido> obtenerPedidosPorCliente(int idCliente) {
		return PedidoDAO.getIntance().AllByCliente(idCliente);
	}

	// Devuelve los pedidos en estado "A CONFIRMAR"
	public Collection<Pedido> obtenerPedidosAConfirmar() {
		return this.obtenerPedidosPorEstado("A CONFIRMAR");
	}
	
	// Dispara la aprobaci�n del Pedido, intentado reservar el Stock
	public String aprobarPedido(int numPedido) {
		Pedido pedido = this.obtenerPedido(numPedido);
		if (pedido != null && pedido.getEstado().equals("A CONFIRMAR")) {
			String nuevoEstadoPedido = AdmStock.getInstancia().reservarStockPedido(pedido);
			pedido.setEstado(nuevoEstadoPedido);
			pedido.updateMe();
			return nuevoEstadoPedido;
		}
		else
			return null;
	}

	// Registra el rechazo del Pedido
	public String rechazarPedido(int numPedido, String motivo) {
		Pedido pedido = obtenerPedido(numPedido);
		if (pedido != null) {
			pedido.setEstado("RECHAZADO");
			pedido.setMotivoRechazo(motivo);
			pedido.updateMe();
			return pedido.getEstado();
		}
		else
			return null;
	}
	
	public Collection<Pedido> obtenerPedidosCompletos() {
		return this.obtenerPedidosPorEstado("COMPLETO");
	}
	
	// La solicitud del Pedido al Dep�sito dispara la facturaci�n del Pedido
	public String solicitarPedido(int numPedido) {
		Pedido pedido = this.obtenerPedido(numPedido);
		if (pedido != null) {
			Factura factura = AdmFacturacion.getInstancia().facturar(pedido);
			if (factura != null) {
				pedido.setTipoFactura(factura.getTipoFactura());
				pedido.setNumFactura(factura.getNumFactura());
				pedido.setEstado("PENDIENTE DEPOSITO");
				pedido.updateMe();
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
	
	// La preparaaci�n del Pedido devuelve la colecci�n de Articulos En Stock que satisfacen
	// el Pedido. Los Articulos En Stock contienen las ubicaciones de los mismos
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
	
	// Refleja la actualizaci�n del Stock una vez que se descargaron los Art�culos del Pedido
	// de sus correspondientes ubicaciones
	public String actualizarStockPorVenta(int numPedido, Collection<ArticuloEnStockDTO> artEnStockDTO) {
		Pedido pedido = this.obtenerPedido(numPedido);
		if (AdmStock.getInstancia().actualizarStockPorVenta(pedido, artEnStockDTO)) {
			pedido.setEstado("PENDIENTE DESPACHO");
			pedido.updateMe();
			return pedido.getEstado();
		}
		else
			return null;
	}


	public Collection<Pedido> obtenerPedidosADespachar() {
		return this.obtenerPedidosPorEstado("PENDIENTE DESPACHO");
	}
	
	// Registra la fecha de entrega provista por el transportista y actualiza el estado
	// del Pedido a "DESPACHADO"
	public String registrarFechaEntrega(int numPedido, Date fechaEntrega) {
		Pedido pedido = this.obtenerPedido(numPedido);
		if (pedido != null) {
			pedido.setFechaEntrega(fechaEntrega);
			pedido.setEstado("DESPACHADO");
			pedido.updateMe();
			return pedido.getEstado();
		}
		else
			return null;
	}
	
}
