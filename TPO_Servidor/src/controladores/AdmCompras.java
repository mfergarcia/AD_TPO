package controladores;

import java.util.Collection;
import java.util.Iterator;

import dao.OrdenDeCompraDAO;
import dao.OrdenPedidoRepoDAO;
import dto.ArticuloDTO;
import negocio.*;

public class AdmCompras {

	private static AdmCompras instancia;
	
	// Constructor privado (Patron Singleton)
	private AdmCompras() {

	}

	// Devuelve todas las Ordenes de Pedido Repo en el estado dado
	private Collection<OrdenPedidoRepo> buscarOrdenesPRPorEstado(String estado) {
		return OrdenPedidoRepoDAO.getInstance().AllByEstado(estado);
	}

	// Ubica las Ordenes de Pedido Reposicion de un determinado Articulo en estado "PENDIENTE"
	// Debe volver ordenada por fecha de creación.
	private Collection<OrdenPedidoRepo> buscarOrdenesPRPendientePorArticulo(String codBarras) {
		return OrdenPedidoRepoDAO.getInstance().AllByArtPENDIENTE(codBarras);
	}	
	
	public static AdmCompras getInstancia() {
		if (instancia == null) {
			instancia = new AdmCompras();
		}
		return instancia;
	}	

	// Crea un nuevo Articulo para el catálogo
	public Articulo altaArticulo(ArticuloDTO articuloDTO) {
		Articulo articulo = new Articulo(articuloDTO);
		articulo.saveMe();
		return articulo;
	}
	
	// Actualiza las propiedades editables del Articulo
	public Articulo modificarArticulo(ArticuloDTO articuloDTO) {
		Articulo articulo = AdmStock.getInstancia().obtenerArticulo(articuloDTO.getCodigoBarras());
		articulo.modificarArticulo(articuloDTO);
		articulo.updateMe();
		return articulo;
	}
	
	// Inactiva un Articulo existente para que no sea publicado en el Catalogo para la venta
	public boolean bajaArticulo(String codBarras) {
		Articulo art = AdmStock.getInstancia().obtenerArticulo(codBarras);
		if (art != null) { 
			art.setEstado('I');
			art.updateMe();
			return true;
		}
		else
			return false;	
	}
	
	// Genera una nueva Orden de Pedido de Resposición
	public void generarOrdenPedidoRepo(int numPedido, Articulo articulo, int cantRepo) {
		OrdenPedidoRepo ordenPR = new OrdenPedidoRepo(numPedido, articulo, cantRepo);
		ordenPR.saveMe();
	}
	
	public Collection<OrdenPedidoRepo> obtenerOPRPendientes() {
		return this.buscarOrdenesPRPorEstado("PENDIENTE");
	}
	
	// @TODOS: Ver si este proceso se puede hacer dentro de OrdenDeCompra
	// Genera una nueva OrdenDeCompra con sus items y le asocia las OrdenPedidoRepo que
	// se pueden cubrir con la cantidad comprada
	public OrdenDeCompra generarOrdenCompra(String proveedor, Collection<ArticuloDTO> articulos) {
		OrdenDeCompra ordenDeCompra = new OrdenDeCompra(proveedor);
		ArticuloDTO auxArtDTO;
		// Recorre los Articulos recibidos para generar los items de la OC
		for (Iterator<ArticuloDTO> i = articulos.iterator(); i.hasNext(); ) {
			auxArtDTO = i.next();
			Articulo art = AdmStock.getInstancia().obtenerArticulo(auxArtDTO.getCodigoBarras());
			// Crea el item de la OrdenDeCompra, asignando la cantidad fija de compra determinada en el Articulo
			ItemOC item = new ItemOC(art, art.getCantFijaCompra());
			ordenDeCompra.agregarItem(item);
			// Verifica qué Ordenes de Pedido de Reposicion se pueden cumplir con esta Orden
			// de Compra para este Artículo
			Collection<OrdenPedidoRepo> ordenesPRPendientes = this.buscarOrdenesPRPendientePorArticulo(auxArtDTO.getCodigoBarras());
			int cantCompra = art.getCantFijaCompra();
			if (ordenesPRPendientes != null && !ordenesPRPendientes.isEmpty()) {
				OrdenPedidoRepo auxOrdenPR;
				Iterator<OrdenPedidoRepo> j = ordenesPRPendientes.iterator();
				// Mientras quede cantidad de compra y Ordenes de Resposición Pendientes para el Artículo
				while (cantCompra > 0 && j.hasNext()) {
					auxOrdenPR = j.next();
					if (cantCompra > auxOrdenPR.getCantRepo()) {
						// Se puede cubrir la Orden de Pedido Repo
						auxOrdenPR.setEstado("OC EN PROCESO");
						auxOrdenPR.updateMe();
						ordenDeCompra.agregarOrdenPedidoRepo(auxOrdenPR);
						cantCompra = cantCompra - auxOrdenPR.getCantRepo();
					}
				}
			}
		}
		ordenDeCompra.saveMe();
		return ordenDeCompra;
	}
	
	// Busca una determinada Orden de Compra
	public OrdenDeCompra obtenerOrdenDeCompra(int numOC) {
		return OrdenDeCompraDAO.getInstance().findById(numOC);
	}

	// Obtiene la Orden de Compra, actualiza el estado de las Ordenes de Pedido Repo
	// y vuelve a disparar el proceso de aprobación del Pedido para intentar completarlo. 
	public String cumplirOrdenDeCompra(int numOC) {
		OrdenDeCompra ordenDeCompra = this.obtenerOrdenDeCompra(numOC); 
		if (ordenDeCompra != null) {
			OrdenPedidoRepo aux;
			for (Iterator<OrdenPedidoRepo> i = ordenDeCompra.getOrdenesPedidoRepo().iterator(); i.hasNext(); ) {
				aux = i.next();
				aux.setEstado("CUMPLIDA");
				aux.updateMe();
				String nuevoEstadoPedido = AdmPedidos.getInstancia().aprobarPedido(aux.getNumPedido());
				System.out.println("El estado del pedido " + aux.getNumPedido() + " ahora es: " + nuevoEstadoPedido);
			}
			ordenDeCompra.setEstado("CUMPLIDA");
			ordenDeCompra.updateMe();
			return ordenDeCompra.getEstado();
		}
		else
			return null;
	}

}
