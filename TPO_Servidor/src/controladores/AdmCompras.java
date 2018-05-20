// @Facu: Revisar usos del saveMe y completar búsquedas en la BD
package controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import dto.ArticuloDTO;
import negocio.*;

public class AdmCompras {

	private static AdmCompras instancia;
	// @Facu: remover las colecciones cuando funcionen las búsquedas en la BD
	private Collection<OrdenPedidoRepo> ordenesPedidoRepo;
	private Collection<OrdenDeCompra> ordenesDeCompra;
	
	// Constructor privado (Patron Singleton)
	private AdmCompras() {
		// Inicializar controlador
		// @Facu: eliminar estas llamadas cuando funcionen las búsquedas en la BD
		this.ordenesPedidoRepo = new ArrayList<OrdenPedidoRepo>();
		this.ordenesDeCompra = new ArrayList<OrdenDeCompra>();
	}

	// @Facu: reemplazar búsqueda en la colección por búsqueda en la BD
	private Collection<OrdenPedidoRepo> buscarOrdenesPRPorEstado(String estado) {
		Collection<OrdenPedidoRepo> ordenesPRPorEstado = new ArrayList<OrdenPedidoRepo>();
		OrdenPedidoRepo aux;
		for(Iterator<OrdenPedidoRepo> i = this.ordenesPedidoRepo.iterator(); i.hasNext() ;) {
			aux = i.next();
			if (aux.getEstado().equals(estado))
				ordenesPRPorEstado.add(aux);
		}
		return ordenesPRPorEstado;
	}

	// @Facu: reemplazar la búsqueda en la colección por búsqueda en la BD
	// Ubica las Ordenes de Pedido Reposicion de un determinado Articulo en estado "PENDIENTE"
	// Debe volver ordenada por fecha de creación.
	private Collection<OrdenPedidoRepo> buscarOrdenesPRPendientePorArticulo(String codBarras) {
		Collection<OrdenPedidoRepo> ordenesPendientes = new ArrayList<OrdenPedidoRepo>();
		OrdenPedidoRepo aux;
		for (Iterator<OrdenPedidoRepo> i = this.ordenesPedidoRepo.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getArticulo().getCodigoBarras().equals(codBarras) && aux.getEstado().equals("PENDIENTE")) {
				ordenesPendientes.add(aux);
			}
		}
		return ordenesPendientes;
	}	
	
	public static AdmCompras getInstancia() {
		if (instancia == null) {
			instancia = new AdmCompras();
		}
		return instancia;
	}	

	// @Facu: revisar la implementación del saveMe
	// Crea un nuevo Articulo y le pide al controlador de Stock que lo agregue al catalogo
	public Articulo altaArticulo(ArticuloDTO articuloDTO) {
		Articulo articulo = new Articulo(articuloDTO);
		articulo.saveMe();
		return articulo;
	}
	
	// @Facu: revisar la implementación del saveMe
	// Se actualizan las propiedades editables del Articulo
	public Articulo modificarArticulo(ArticuloDTO articuloDTO) {
		Articulo articulo = AdmStock.getInstancia().obtenerArticulo(articuloDTO.getCodigoBarras());
		articulo.setDescripcion(articuloDTO.getDescripcion());
		articulo.setPresentacion(articuloDTO.getPresentacion());
		articulo.setTamaño(articuloDTO.getTamaño());
		articulo.setUnidad(articuloDTO.getUnidad());
		articulo.setPrecioVta(articuloDTO.getPrecioVta());
		articulo.setCantFijaCompra(articuloDTO.getCantFijaCompra());
		articulo.setCantMaxUbicacion(articuloDTO.getCantMaxUbicacion());
		articulo.saveMe();
		return articulo;
	}
	
	// @Facu: revisar la implementación del saveMe
	// Inactiva una articulo existente para que no sea publicado en el Catalogo para la venta
	public boolean bajaArticulo(String codBarras) {
		Articulo art = AdmStock.getInstancia().obtenerArticulo(codBarras);
		if (art != null) { 
			art.setEstado('I');
			art.saveMe();
			return true;
		}
		else
			return false;	
	}
	
	// @Facu: revisar uso del saveMe
	// Genera una nueva Orden de Pedido de Resposición
	public void generarOrdenPedidoRepo(int numPedido, Articulo articulo, int cantRepo) {
		OrdenPedidoRepo ordenPR = new OrdenPedidoRepo(numPedido, articulo, cantRepo);
		ordenPR.saveMe();
	}
	
	public Collection<OrdenPedidoRepo> obtenerOPRPendientes() {
		return this.buscarOrdenesPRPorEstado("PENDIENTE");
	}
	
	// @Facu: revisar uso del saveMe
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
			// Verifica qué Ordenes de Pedido de Reposicion se pueden cumplir con esta Orden de Compra
			Collection<OrdenPedidoRepo> ordenesPRPendientes = this.buscarOrdenesPRPendientePorArticulo(auxArtDTO.getCodigoBarras());
			int cantCompra = auxArtDTO.getCantFijaCompra();
			if (ordenesPRPendientes != null && !ordenesPRPendientes.isEmpty()) {
				OrdenPedidoRepo auxOrdenPR;
				Iterator<OrdenPedidoRepo> j = ordenesPRPendientes.iterator();
				// Mientras quede cantidad de compra y Ordenes de Resposición Pendientes para el Artículo
				while (cantCompra > 0 && j.hasNext()) {
					auxOrdenPR = j.next();
					if (cantCompra > auxOrdenPR.getCantRepo()) {
						// Se puede cubrir la Orden de Pedido Repo
						auxOrdenPR.setEstado("OC EN PROCESO");
						auxOrdenPR.saveMe();
						ordenDeCompra.agregarOrdenPedidoRepo(auxOrdenPR);
						cantCompra = cantCompra - auxOrdenPR.getCantRepo();
					}
				}
			}
		}
		ordenDeCompra.saveMe();
		return ordenDeCompra;
	}
	
	// @Facu: reemplazar búsqueda en la colección por búsqueda en la BD
	// Busca una determinada Orden de Compra en la coleccion. Devuelve null si no la encuentra
	public OrdenDeCompra obtenerOrdenDeCompra(int numOC) {
		OrdenDeCompra aux;
		for(Iterator<OrdenDeCompra> i = this.ordenesDeCompra.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getNumOC() == numOC)
				return aux;
		}
		return null;
	}

	// Obtiene la OC de la coleccion ordenesDeCompra, actualiza el estado de las Ordenes de Pedido Repo
	// y vuelve a disparar el proceso de aprobación del Pedido para intentar completarlo. 
	public String cumplirOrdenDeCompra(int numOC) {
		OrdenDeCompra ordenDeCompra = this.obtenerOrdenDeCompra(numOC); 
		if (ordenDeCompra != null) {
			OrdenPedidoRepo aux;
			for (Iterator<OrdenPedidoRepo> i = ordenDeCompra.getOrdenesPedidoRepo().iterator(); i.hasNext(); ) {
				aux = i.next();
				String nuevoEstadoPedido = AdmPedidos.getInstancia().aprobarPedido(aux.getNumPedido());
				aux.setEstado("CUMPLIDA");
				aux.saveMe();
			}
			ordenDeCompra.setEstado("CUMPLIDA");
			ordenDeCompra.saveMe();
			return ordenDeCompra.getEstado();
		}
		else
			return null;
	}

}
