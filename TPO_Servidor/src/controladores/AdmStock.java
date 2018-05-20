// @Marce: Completar programación de mov de stock
// @Facu: Revisar usos del saveMe y completar búsquedas en la BD
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
	//@Facu: remover colecciones cuando estén implementadas las búsquedas en la BD
	private Collection<Articulo> articulos;
	private Collection<Stock> stock;
	private Collection <MovimientoStock> movimientosStock;	
	
	// Constructor privado (Patron Singleton)
	private AdmStock() {
		// @Facu: removar llamadas cuando funcione la búsqueda en la BD
		this.articulos = new ArrayList<Articulo>();
		this.stock = new ArrayList<Stock>();
		this.movimientosStock = new ArrayList<MovimientoStock>();
	}
	
	// @Facu: Reemplazar esta búsqueda por búsqueda en la BD.
	// Devuelve el Stock para un codigo de ubicacion dado
	private Stock obtenerStock(String codUbicacion) {
		return StockDAO.getInstance().findByID(codUbicacion);
	}
	
	// @Facu: reemplazar búsqueda en la colección por búsqueda en la BD
	// Recupera una ubicación libre para que sea asignada a un nuevo Articulo En Stock
	// La ubicación se bloquea hasta que sea asignado el stock
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
	
	// @Facu: modificar búsqueda para recuperar el articulo de la BD y no de la
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
	
	// @Facu: modificar búsqueda para recuperar los articulos de la BD
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
	// que hay stock faltante, se genera una Orden de Pedido de Reposición
	public String reservarStockPedido(Pedido pedido) {
		String estadoPedido = "COMPLETO";
		Collection<ArticuloEnStock> artEnStock;
		ItemArticulo auxItemArt;
		// Recorre los articulos del pedido para tratar de reservar el stock
		for (Iterator<ItemArticulo> i = pedido.getArticulos().iterator(); i.hasNext(); ) {
			auxItemArt = i.next();
			if (!auxItemArt.getEstadoStock().equals("RESERVADO")) {
				// El ítem aún no fue reservado
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
								// Reserva todo lo que hay y sigue buscando más cantidad en el siguiente 
								// Articulo En Stock
								if (stock.reservarStock(cantReservable)) {
									stock.saveMe();
									cantRequerida = cantRequerida - cantReservable;
								}	
							}
						}
					}
				}
				// Verifica si quedó cantidad pendiente por reservar o si se acabaron los Articulos En Stock
				if (cantRequerida > 0) {
					// Ya recorrió todos los Articulos En Stock y aún queda cantidad pendiente por reservar
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
		// Obtiene todos los Articulos en Stock del Artículo, ordenado por Fecha de Vencimiento
		Collection<ArticuloEnStock> artEnStockAll = articulo.obtenerArtEnStockOrdenFV();
		ArticuloEnStock aux;
		Iterator<ArticuloEnStock> i = artEnStockAll.iterator();
		while (cantRequerida > 0 && i.hasNext()) {
			aux = i.next();
			// Recupera el stock de cada Articulo en Stock
			stock = this.obtenerStock(aux.getCodigoUbicacion());
			// Verifica si la ubicación no se encuentra bloqueada por otro Pedido en curso
			if(stock.getEstado().equals("OCUPADA")) {
				stockLocalizado.add(aux);
				// Bloquea la ubicación hasta que se actualice el stock
				stock.actualizarEstado("BLOQUEADA");
				stock.saveMe();
				if (cantRequerida <= stock.getCantidadReal())
					// Si la posicion tiene más stock de la cantidad requerida, se corta el ciclo
					cantRequerida = 0;
				else
					// Si la posición tiene menos stock del requerido, se continua buscando la diferencia
					cantRequerida = cantRequerida - stock.getCantidadReal();
			}
		}
		return stockLocalizado;
	}
	
	// @Marce: agregar la generación del movimiento de stock por venta
	// @Facu: Revisar si está ok el uso del saveMe//Si, probar
	// NOTAS_FG: Revisar si está ok la ejecución de los ciclos
	public boolean actualizarStockPorVenta(Pedido pedido, Collection<ArticuloEnStockDTO> artEnStockDTO) {
		int cantNecesaria;
		int cantADescontar;
		Stock stock;
		ItemArticulo aux;
		Iterator<ArticuloEnStockDTO> j = artEnStockDTO.iterator();
		ArticuloEnStockDTO auxDTO;
		for (Iterator<ItemArticulo> i = pedido.getArticulos().iterator(); i.hasNext(); ) {
			aux = i.next();
			// Almacena la cantidad requerida para este Artículo
			cantNecesaria = aux.getCant();
			while (cantNecesaria > 0 && j.hasNext()) {
				auxDTO = j.next();
				if (aux.getArticulo().getCodigoBarras().equals(auxDTO.getCodigoBarras())) {
					stock = this.obtenerStock(auxDTO.getCodigoUbicacion());
					if (cantNecesaria > stock.getCantidadReal()) {
						// No hay stock suficiente en esta posición para descontar todo lo que se necesita
						cantADescontar = stock.getCantidadReal();
						cantNecesaria = cantNecesaria - cantADescontar;
					}
					else {
						// El stock en esta ubicación alcanza para cubrir lo que se necesita
						cantADescontar = cantNecesaria;
						cantNecesaria = 0;
					}
					// Actualiza la cantidad real de la posición con la nueva cantidad
					stock.actualizarCantidadReal(stock.getCantidadReal() - cantADescontar);
					stock.saveMe();
					aux.setEstadoStock("DESCONTADO");
					aux.saveMe();
					if (stock.getCantidadReal() == 0) {
						// Ya no queda más stock de este Artículo en Stock, se desasigna el código de ubicación
						ArticuloEnStock artEnStock = aux.getArticulo().obtenerArtEnStock(auxDTO.getId());
						artEnStock.setCodigoUbicacion(null);
						artEnStock.saveMe();
					}
					// Aquí debería generarse el movimiento de stock por venta.
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
	
	// @Facu: preparar búsqueda de los últimos 3 proveedores de un Artículo
	// Obtiene los últimos 3 proveedores del Artículo y los concatena en un solo String("Proveedores anteriores: " + proveedor1 + " " + proveedor2 + " " proveedor3)
	// Si no encuentra ni un solo proveedor, el string deberá contener el mensaje "No se registran proveedores de este Artículo"
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
			return new String("No se registran proveedores de este Artículo");
		
		return ulttresprov;
	}

	//@Marce: agregar creación del movimiento de stock
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
				// Aquí debería generarse el movimiento de ajuste de stock por compra
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