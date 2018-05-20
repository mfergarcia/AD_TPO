// @Marce: Completar programaci�n de mov de stock
// @Facu: Revisar usos del saveMe y completar b�squedas en la BD
package controladores;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import dao.ArticuloDAO;
import dao.ArticuloEnStockDAO;
import dao.StockDAO;
import dto.ArticuloEnStockDTO;
import excepciones.ExcepcionSistema;
import negocio.Articulo;
import negocio.ArticuloEnStock;
import negocio.ItemArticulo;
import negocio.MovimientoStock;
import negocio.Pedido;
import negocio.Stock;

public class AdmStock {

	private static AdmStock instancia;
	//@Facu: remover colecciones cuando est�n implementadas las b�squedas en la BD
	private Collection<Articulo> articulos;
	private Collection<Stock> stock;
	private Collection <MovimientoStock> movimientosStock;	
	
	// Constructor privado (Patron Singleton)
	private AdmStock() {
		// @Facu: removar llamadas cuando funcione la b�squeda en la BD
		this.articulos = new ArrayList<Articulo>();
		this.stock = new ArrayList<Stock>();
		this.movimientosStock = new ArrayList<MovimientoStock>();
	}
	
	// @Facu: Reemplazar esta b�squeda por b�squeda en la BD.
	// Devuelve el Stock para un codigo de ubicacion dado
	private Stock obtenerStock(String codUbicacion) {
		return StockDAO.getInstance().findByID(codUbicacion);
	}
	
	// @Facu: reemplazar b�squeda en la colecci�n por b�squeda en la BD
	// Recupera una ubicaci�n libre para que sea asignada a un nuevo Articulo En Stock
	// La ubicaci�n se bloquea hasta que sea asignado el stock
	private Stock obtenerUbicacionLibre() {
	/*	Stock aux;
		for (Iterator<Stock> i = stock.iterator(); i.hasNext() ; ) { 
			aux = i.next();
			if (aux.getEstado().equals("LIBRE")) {
				aux.actualizarEstado("BLOQUEADA");
				aux.saveMe();
				return aux;
			}	
		}
		 */
		return StockDAO.getInstance().findByEstadoLibre();
	}
	
	public static AdmStock getInstancia() {
		if (instancia == null) {
			instancia = new AdmStock();
		}
		return instancia;
	}	
	
	// @Facu: modificar b�squeda para recuperar el articulo de la BD y no de la
	// coleccion
	public Articulo obtenerArticulo(String codBarras) {
		/*
		Articulo aux;
		for (Iterator<Articulo> i = this.articulos.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getCodigoBarras().equals(codBarras))
				return aux;
		}
		*/
		return ArticuloDAO.getInstance().findByID(codBarras);
	}
	
	// @Facu: modificar b�squeda para recuperar los articulos de la BD
	// Devuelve todos los articulos activos (estado = 'A') 
	public Collection<Articulo> obtenerCatalogo() {
		/*
		Collection<Articulo> catalogo = new ArrayList<Articulo>();
		Articulo aux;
		for (Iterator<Articulo> i = this.articulos.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getEstado() == 'A')
				catalogo.add(aux);
		}*/
		return ArticuloDAO.getInstance().showAll();
	}
	
	// Verifica la existencia de Stock de cada item del Pedido. Si detecta
	// que hay stock faltante, se genera una Orden de Pedido de Reposici�n
	public String reservarStockPedido(Pedido pedido) {
		String estadoPedido = "COMPLETO";
		Collection<ArticuloEnStock> artEnStock;
		ItemArticulo auxItemArt;
		// Recorre los articulos del pedido para tratar de reservar el stock
		for (Iterator<ItemArticulo> i = pedido.getArticulos().iterator(); i.hasNext(); ) {
			auxItemArt = i.next();
			if (!auxItemArt.getEstadoStock().equals("RESERVADO")) {
				// El �tem a�n no fue reservado
				// Obtiene los Articulos en Stock ordenado por Fecha de Vencimiento
				artEnStock = auxItemArt.getArticulo().obtenerArtEnStockOrdenFV();
				int cantRequerida = auxItemArt.getCant();
				int cantReservable;
				ArticuloEnStock auxArtEnStock;
				// Recorre los Articulos en Stock para ver si se puede reservar la cantidad
				// requerida del Articulo
				Iterator<ArticuloEnStock> j = artEnStock.iterator();
				while (cantRequerida > 0 && j.hasNext()) {
					auxArtEnStock = j.next();
					// Obtiene el Stock del Articulo En Stock
					Stock stock = this.obtenerStock(auxArtEnStock.getCodigoUbicacion());
					if (stock != null) {
						cantReservable = stock.cantidadReservableEnStock();
						if (cantReservable > 0) {
							// Hay stock para reservar, necesita comparar cuanto hay vs lo que se necesita
							if (cantReservable >= cantRequerida) {
								// Reserva solo lo que se necesita
								if (stock.reservarStock(cantRequerida)) {
									stock.saveMe();
									cantRequerida = 0;
								}	
							}
							else {
								// Reserva todo lo que hay y sigue buscando m�s cantidad en el siguiente 
								// Articulo En Stock
								if (stock.reservarStock(cantReservable)) {
									stock.saveMe();
									cantRequerida = cantRequerida - cantReservable;
								}	
							}
						}
					}
				}
				// Verifica si qued� cantidad pendiente por reservar o si se acabaron los Articulos En Stock
				if (cantRequerida > 0) {
					// Ya recorri� todos los Articulos En Stock y a�n queda cantidad pendiente por reservar
					// Genera entonces una Orden de Pedido de Reposicion por la cantidad faltante
					AdmCompras.getInstancia().generarOrdenPedidoRepo(pedido.getNumPedido(), auxItemArt.getArticulo(), cantRequerida);
					auxItemArt.setEstadoStock("PENDIENTE OPR");
					estadoPedido = "PENDIENTE REPOSICION";
				}
				else
					auxItemArt.setEstadoStock("RESERVADO");
				auxItemArt.saveMe();
			}
		}
		return estadoPedido;
	}


	public Collection<ArticuloEnStock> localizarStockArticulo(Articulo articulo, int cantidad) {
		// Coleccion para almacenar la seleccion de Articulos En Stock que se necesitan para cumplir este Item del Pedido
		Collection<ArticuloEnStock> stockLocalizado = new ArrayList<ArticuloEnStock>();
		Stock stock;
		int cantRequerida = cantidad;
		// Obtiene todos los Articulos en Stock del Art�culo, ordenado por Fecha de Vencimiento
		Collection<ArticuloEnStock> artEnStockAll = articulo.obtenerArtEnStockOrdenFV();
		ArticuloEnStock aux;
		Iterator<ArticuloEnStock> i = artEnStockAll.iterator();
		while (cantRequerida > 0 && i.hasNext()) {
			aux = i.next();
			// Recupera el stock de cada Articulo en Stock
			stock = this.obtenerStock(aux.getCodigoUbicacion());
			// Verifica si la ubicaci�n no se encuentra bloqueada por otro Pedido en curso
			if(stock.getEstado().equals("OCUPADA")) {
				stockLocalizado.add(aux);
				// Bloquea la ubicaci�n hasta que se actualice el stock
				stock.actualizarEstado("BLOQUEADA");
				stock.saveMe();
				if (cantRequerida <= stock.getCantidadReal())
					// Si la posicion tiene m�s stock de la cantidad requerida, se corta el ciclo
					cantRequerida = 0;
				else
					// Si la posici�n tiene menos stock del requerido, se continua buscando la diferencia
					cantRequerida = cantRequerida - stock.getCantidadReal();
			}
		}
		return stockLocalizado;
	}
	
	// @Marce: agregar la generaci�n del movimiento de stock por venta
	// @Facu: Revisar si est� ok el uso del saveMe//Si, probar
	// NOTAS_FG: Revisar si est� ok la ejecuci�n de los ciclos
	public boolean actualizarStockPorVenta(Pedido pedido, Collection<ArticuloEnStockDTO> artEnStockDTO) {
		int cantNecesaria;
		int cantADescontar;
		Stock stock;
		ItemArticulo aux;
		Iterator<ArticuloEnStockDTO> j = artEnStockDTO.iterator();
		ArticuloEnStockDTO auxDTO;
		for (Iterator<ItemArticulo> i = pedido.getArticulos().iterator(); i.hasNext(); ) {
			aux = i.next();
			// Almacena la cantidad requerida para este Art�culo
			cantNecesaria = aux.getCant();
			while (cantNecesaria > 0 && j.hasNext()) {
				auxDTO = j.next();
				if (aux.getArticulo().getCodigoBarras().equals(auxDTO.getCodigoBarras())) {
					stock = this.obtenerStock(auxDTO.getCodigoUbicacion());
					if (cantNecesaria > stock.getCantidadReal()) {
						// No hay stock suficiente en esta posici�n para descontar todo lo que se necesita
						cantADescontar = stock.getCantidadReal();
						cantNecesaria = cantNecesaria - cantADescontar;
					}
					else {
						// El stock en esta ubicaci�n alcanza para cubrir lo que se necesita
						cantADescontar = cantNecesaria;
						cantNecesaria = 0;
					}
					// Actualiza la cantidad real de la posici�n con la nueva cantidad
					stock.actualizarCantidadReal(stock.getCantidadReal() - cantADescontar);
					stock.saveMe();
					aux.setEstadoStock("DESCONTADO");
					aux.saveMe();
					if (stock.getCantidadReal() == 0) {
						// Ya no queda m�s stock de este Art�culo en Stock, se desasigna el c�digo de ubicaci�n
						ArticuloEnStock artEnStock = aux.getArticulo().obtenerArtEnStock(auxDTO.getId());
						artEnStock.setCodigoUbicacion(null);
						artEnStock.saveMe();
					}
					// Aqu� deber�a generarse el movimiento de stock por venta.
				}
			}
			
		}
		boolean ok = true;
		for (Iterator<ItemArticulo> k = pedido.getArticulos().iterator(); k.hasNext(); ) {
			aux = k.next();
			if (!aux.getEstadoStock().equals("DESCONTADO"))
				ok = false;
		}
		return ok;
	}
	
	// @Facu: preparar b�squeda de los �ltimos 3 proveedores de un Art�culo
	// Obtiene los �ltimos 3 proveedores del Art�culo y los concatena en un solo String("Proveedores anteriores: " + proveedor1 + " " + proveedor2 + " " proveedor3)
	// Si no encuentra ni un solo proveedor, el string deber� contener el mensaje "No se registran proveedores de este Art�culo"
	public String obtenerProveedores(String codBarras) throws RemoteException, ExcepcionSistema {
		List<ArticuloEnStock> aee= ArticuloEnStockDAO.getInstance().showAllbylote(codBarras);
		String ulttresprov= "";
		int i=0;
		while (aee.size()>0 && i<3){
			if(aee.size()>= i){
				ulttresprov= ulttresprov+aee.get(i).getProveedor();
				ulttresprov= ulttresprov+" ";
			}
			i++;
		}
		if(ulttresprov.equals(""))
			return new String("No se registran proveedores de este Art�culo");
		
		return ulttresprov;
	}

	//@Marce: agregar creaci�n del movimiento de stock
	//@Facu: revisar el uso del saveMe
	//NOTA_FG: Completar con el movimiento de ajuste por Compra
	public Collection<ArticuloEnStock> cargarArticuloEnStock(int numOC, ArticuloEnStockDTO artEnStockDTO) {
		Articulo articulo = this.obtenerArticulo(artEnStockDTO.getCodigoBarras());
		Collection<ArticuloEnStock> articulosEnStock = new ArrayList<ArticuloEnStock>();
		if (articulo != null) {
			int cantRequerida = artEnStockDTO.getCantidad();
			int cantAUbicar; 
			while (cantRequerida > 0) {
				if (articulo.getCantMaxUbicacion() >= cantRequerida) {
					cantAUbicar = cantRequerida;
					cantRequerida = 0;
				}
				else {
					cantAUbicar = articulo.getCantMaxUbicacion();
					cantRequerida = cantRequerida - articulo.getCantMaxUbicacion();
				}
				Stock stock = obtenerUbicacionLibre();
				ArticuloEnStock artEnStock = new ArticuloEnStock(stock.getCodigoUbicacion(), cantAUbicar, artEnStockDTO);
				stock.actualizarCantidadReal(cantAUbicar);
				stock.saveMe();
				articulo.agregarArtEnStock(artEnStock);
				articulosEnStock.add(artEnStock);
				// Aqu� deber�a generarse el movimiento de ajuste de stock por compra
			}	
			articulo.saveMe();
		}
		return articulosEnStock;
	}
	
	public void ajustarStockPorInventario(int cant, String codB, String lote, String ubicacion) {
	
	}
	
	public void ajustarStockPorMant(int cant, String usuarioRegistrado, String autorizante, String destinoFinal, String ubicacion) {
	
	}
	
}