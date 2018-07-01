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
import negocio.ClienteEmpresa;
import negocio.ClientePersona;
import negocio.Direccion;
import negocio.Factura;
import negocio.ItemArticulo;
import negocio.Pedido;

public class AdmPedidos {

	private static AdmPedidos instancia;
	
	// Constructor privado (Patron Singleton)
	private AdmPedidos() {

	}

	// Ubica un determinado Pedido
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

	// @Facu: revisar implementación del saveMe (Pedido se guarda con los items, mismo caso que 
	// que Cliente y Cta Cte)
	// Crea un nuevo Pedido con sus correspondientes items 
	public Pedido generarPedido(PedidoDTO pedidoDTO) {
		Pedido pedido;
		Direccion direccion = new Direccion(pedidoDTO.getDirEntrega());
		char tipoCliente = AdmClientes.getInstancia().obtenerTipoCliente(pedidoDTO.getIdCliente());
		if (tipoCliente == 'E') {
			ClienteEmpresa cteEmpresa = AdmClientes.getInstancia().obtenerClienteEmpresa(pedidoDTO.getIdCliente());
			pedido = new Pedido(cteEmpresa, direccion);	
		}
		else {
			ClientePersona ctePersona = AdmClientes.getInstancia().obtenerClientePersona(pedidoDTO.getIdCliente());
			pedido = new Pedido(ctePersona, direccion);	

		}	
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
	
	// Dispara la aprobación del Pedido, intentado reservar el Stock
	public String aprobarPedido(int numPedido) {
		Pedido pedido = this.obtenerPedido(numPedido);
		if (pedido != null) {
			if (pedido.getEstado().equals("A CONFIRMAR") || pedido.getEstado().equals("PENDIENTE REPOSICION")) {
				String nuevoEstadoPedido = pedido.reservarStockPedido();
				pedido.setEstado(nuevoEstadoPedido);
				pedido.updateMe();
				return nuevoEstadoPedido;
			}
			else
				return null;
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
	
	// La solicitud del Pedido al Depósito dispara la facturación del Pedido
	public String solicitarPedido(int numPedido) {
		Pedido pedido = this.obtenerPedido(numPedido);
		if (pedido != null) {
			Factura factura = AdmFacturacion.getInstancia().facturar(pedido);
			if (factura != null) {
				pedido.setFactura(factura);
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
	
	// La preparaación del Pedido devuelve la colección de Articulos En Stock que satisfacen
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
	
	// Refleja la actualización del Stock una vez que se descargaron los Artículos del Pedido
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
