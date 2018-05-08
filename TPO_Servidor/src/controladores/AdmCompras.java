//PENDIENTE: Completar programacion y desde VERRRR
package controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import negocio.*;

public class AdmCompras {

	private static AdmCompras instancia;
	private int numeradorOrdenPR;
	private int numeradorOC;
	private Collection<OrdenPedidoRepo> ordenesPedidoRepo;
	private Collection<OrdenDeCompra> ordenesDeCompra;
	
	// Constructor privado (Patron Singleton)
	private AdmCompras() {
		// TODO Auto-generated constructor stub
		// Inicializar controlador
		cargarOrdenesPedidoRepo();
		cargarOrdenesDeCompra();
	}
	
	// Inicializa la coleccion ordenesPedidoRepo, invoca al DAO
	// y carga las OrdenesPedidoRepo existentes de la BD en dicha
	// colección. Tambien setea el numeradorOrdenPR = max(numOrdenPedidoRepo) + 1
	private void cargarOrdenesPedidoRepo() {
		
	}

	// Inicializa la coleccion ordenesCompra, invoca al DAO
	// y carga las OrdenesDeCompra existentes de la BD en la 
	// dicha colección. También setea el numeradorOC = max(numOC) + 1
	private void cargarOrdenesDeCompra() {
		
	}

	// Ubica las Ordenes de Pedido Reposicion de un determinado Articulo en estado "PENDIENTE"
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

	private Collection<OrdenPedidoRepo> buscarOrdenesPRPorEstado(String estado) {
		Collection<OrdenPedidoRepo> ordenesPRXEstado = new ArrayList<OrdenPedidoRepo>();
		OrdenPedidoRepo aux;
		for(Iterator<OrdenPedidoRepo> i = this.ordenesPedidoRepo.iterator(); i.hasNext() ;) {
			aux = i.next();
			if (aux.getEstado().equals(estado))
				ordenesPRXEstado.add(aux);
		}
		return ordenesPRXEstado;
	}
	
	public static AdmCompras getInstancia() {
		if (instancia == null) {
			instancia = new AdmCompras();
		}
		return instancia;
	}	

	// Crea un nuevo Articulo y le pide al controlador de Stock que lo agregue al catalogo
	public Articulo altaArticulo(String codBarras, String desc, String pres, int tamaño, String unidad, float precioVta, int cantFijaCompra, int cantMaxUbi) {
		Articulo art = new Articulo(codBarras, desc, pres, tamaño, unidad, precioVta, cantFijaCompra, cantMaxUbi);
		AdmStock.getInstancia().agregarArticulo(art);
		return art;
	}
	
	// Se actualizan las propiedades editables del Articulo
	public boolean modificarArticulo(String codBarras, String desc, String pres, int tamaño, String unidad, float precioVta, int cantFijaCompra, int cantMaxUbi) {
		Articulo art = AdmStock.getInstancia().buscarArticulo(codBarras);
		if (art != null) {
			art.setDescripcion(desc);
			art.setPresentacion(pres);
			art.setTamaño(tamaño);
			art.setUnidad(unidad);
			art.setPrecioVta(precioVta);
			art.setCantFijaCompra(cantFijaCompra);
			art.setCantMaxUbicacion(cantMaxUbi);
			return true;
		}
		else
			return false;
	}
	
	// Inactiva una articulo existente para que no sea publicado en el Catalogo para la venta
	public boolean bajaArticulo(String codBarras) {
		Articulo art = AdmStock.getInstancia().buscarArticulo(codBarras);
		if (art != null) { 
			art.setEstado('I');
			return true;
		}
		else
			return false;	
	}
	
	// Genera una nueva OrdenPedidoRepo y la agrega a la coleccion de ordenesPedidoRepo
	// Incrementa el numeradorOrdenPR
	public void generarOrdenPedidoRepo(int numPedido, Articulo articulo, int cantRepo) {
		OrdenPedidoRepo ordenPR = new OrdenPedidoRepo(numeradorOrdenPR, numPedido, articulo, cantRepo);
		this.numeradorOrdenPR ++;
		this.ordenesPedidoRepo.add(ordenPR);
	}
	
	// Genera una nueva OrdenDeCompra con sus items, le asocia las OrdenPedidoRepo que
	// se pueden cubrir con la cantidad comprada y la agrega a la coleccion ordenesDeCompra
	// También incrementa el numeradorOC
	public void generarOrdenCompra(String proveedor, Collection<Articulo> articulos) {
		OrdenDeCompra ordenDeCompra = new OrdenDeCompra(numeradorOC, proveedor);
		this.numeradorOC ++;
		Articulo auxArt;
		// Genera los items de la OC
		for (Iterator<Articulo> i = articulos.iterator(); i.hasNext(); ) {
			auxArt = i.next();
			ordenDeCompra.agregarItem(new ItemOC(auxArt, auxArt.getCantFijaCompra()));
			// Asigna la cantidad fija de compra determinada en el Articulo
			// Verifica qué Ordenes de Pedido de Reposicion se pueden cumplir con esta Orden de Compra
			Collection<OrdenPedidoRepo> ordenesPRPendientes = buscarOrdenesPRPendientePorArticulo(auxArt.getCodigoBarras());
			int cantPendiente = auxArt.getCantFijaCompra();
			if (ordenesPRPendientes != null) {
				OrdenPedidoRepo auxOrdenPR;
				for (Iterator<OrdenPedidoRepo> j = ordenesPRPendientes.iterator(); j.hasNext(); ) {
					auxOrdenPR = j.next();
					if (cantPendiente > auxOrdenPR.getCantRepo()) {
						auxOrdenPR.setEstado("OC EN PROCESO");
						ordenDeCompra.agregarOrdenPedidoRepo(auxOrdenPR);
						cantPendiente = cantPendiente - auxOrdenPR.getCantRepo();
					}
				}
			}
		}
		this.ordenesDeCompra.add(ordenDeCompra);
	}
	
	// Busca una determinada Orden de Compra en la coleccion
	public OrdenDeCompra buscarOrdenDeCompra(int numOC) {
		OrdenDeCompra aux;
		for(Iterator<OrdenDeCompra> i = this.ordenesDeCompra.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getNumOC() == numOC)
				return aux;
		}
		return null;
	}

	// Obtiene la OC de la coleccion ordenesDeCompra y ejecuta su cumplimiento
	// NOTAS_FG: Este metodo dispara una verificacion de si el pedido puede completarse 
	public boolean cumplirOrdenDeCompra(int numOC) {
		OrdenDeCompra ordenDeCompra = buscarOrdenDeCompra(numOC);
		if (ordenDeCompra != null) {
			OrdenPedidoRepo aux;
			for (Iterator<OrdenPedidoRepo> i = ordenDeCompra.getOrdenesPedidoRepo().iterator(); i.hasNext(); ) {
				aux = i.next();
				// String nuevoEstadoPedido = AdmPedidos.getInstancia().aprobarPedido(aux.getNumPedido());
				aux.setEstado("CUMPLIDA");
			}
			ordenDeCompra.setEstado("CUMPLIDA");
			return true;
		}
		return true;
	}
	
	//NOTAS_FG: Para que se necesita este metodo acá si la recepción de la orden de compra la maneja AdmStock
	public void cargarArtEnStock(int numOrden, String codB, String lote, int cant) {
	
	}
	
}
