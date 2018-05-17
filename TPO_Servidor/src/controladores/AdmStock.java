//PENDIENTE: Completar programación y desde VERRRR
package controladores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import dto.ArticuloDTO;
import negocio.Articulo;
import negocio.ArticuloEnStock;
import negocio.ItemArticulo;
import negocio.ItemOC;
import negocio.MovimientoStock;
import negocio.OrdenDeCompra;
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
		// TODO Auto-generated constructor stub
		// @Facu: removar llamadas cuando funcione la búsqueda en la BD
		this.articulos = new ArrayList<Articulo>();
		this.stock = new ArrayList<Stock>();
		this.movimientosStock = new ArrayList<MovimientoStock>();
	}
	
	// @Facu: Reemplazar esta búsqueda por búsqueda en la BD. La búsqueda tiene 
	// que volver ordenada por Fecha de Vencimiento (primero los más viejos)
	private Collection<ArticuloEnStock> obtenerArtEnStockOrdenFV(String codBarras) {
		Articulo art = obtenerArticulo(codBarras);
		return art.getArticulosEnStock();
	}
	
	// @Facu: Reemplazar esta búsqueda por búsqueda en la BD.
	// Devuelve el Stock para un codigo de ubicacion dado
	private Stock obtenerStock(String codUbicacion) {
		Stock aux;
		for (Iterator<Stock> i = this.stock.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getCodigoUbicacion().equals(codUbicacion))
				return aux;
		}
		return null;
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
		Articulo aux;
		for (Iterator<Articulo> i = this.articulos.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getCodigoBarras().equals(codBarras))
				return aux;
		}
		return null;
	}
	
	// @Facu: modificar búsqueda para recuperar los articulos de la BD
	// Devuelve todos los articulos activos (estado = 'A') 
	public Collection<Articulo> obtenerCatalogo() {
		Collection<Articulo> catalogo = new ArrayList<Articulo>();
		Articulo aux;
		for (Iterator<Articulo> i = this.articulos.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getEstado() == 'A')
				catalogo.add(aux);
		}
		return catalogo;
	}
	
	// @Facu: Validar si los saveMe están bien puestos (para stock y para auxItemArt)
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
				artEnStock = this.obtenerArtEnStockOrdenFV(auxItemArt.getArticulo().getCodigoBarras());
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

	// @Facu: revisar el uso de saveMe
	public Collection<ArticuloEnStock> localizarStockArticulo(Articulo articulo, int cantidad) {
		// Coleccion para almacenar la seleccion de Articulos En Stock que se necesitan para este Pedido
		Collection<ArticuloEnStock> stockLocalizado = new ArrayList<ArticuloEnStock>();
		Stock stock;
		int cantRequerida = cantidad;
		// Obtiene todos los Articulos en Stock del Artículo, ordenado por Fecha de Vencimiento
		Collection<ArticuloEnStock> artEnStock = this.obtenerArtEnStockOrdenFV(articulo.getCodigoBarras());
		ArticuloEnStock aux;
		Iterator<ArticuloEnStock> i = artEnStock.iterator();
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
					// Si la posicion tiene más stock de la requerida, se corta el ciclo
					cantRequerida = 0;
				else
					// Si la posición tiene menos stock del requerido, se continua buscando la diferencia
					cantRequerida = cantRequerida - stock.getCantidadReal();
			}
		}
		return stockLocalizado;
	}
	
	// NOTAS_FG: Reemplazar por busqueda en el DAO
	public Stock obtenerUbicacionLibre() {
		Stock aux;
		for (Iterator<Stock> i = stock.iterator(); i.hasNext() ; ) { 
			aux = i.next();
			if (aux.getEstado().equals("LIBRE")) {
				aux.actualizarEstado("BLOQUEADA");
				return aux;
			}	
		}
		return null;
	}
	
	
	
	public void solicitarArticulos(int numPedido) {
	
	}
	
	public void ajustarInventario(int cant, String codB, String lote, String ubicacion) {
	
	}
	
	public void ajustarStockC(int idOC) {
	
	}
	
	public void ajustarStockMant(int cant, String usuarioRegistrado, String autorizante, String destinoFinal, String ubicacion) {
	
	}

	//NOTA_FG: Completar con el movimiento de ajuste por Compra
	public Collection<ArticuloEnStock> cargarArticuloEnStock(int numOC, String codBarras, int cantidad, String lote, Date fechaVenc, String proveedor, float precioCompra) {
		Articulo articulo = obtenerArticulo(codBarras);
		Collection<ArticuloEnStock> articulosEnStock = new ArrayList<ArticuloEnStock>();
		if (articulo != null) {
			int cantAUbicar; 
			while (cantidad > 0) {
				if (articulo.getCantMaxUbicacion() >= cantidad) {
					cantAUbicar = cantidad;
					cantidad = 0;
				}
				else {
					cantAUbicar = articulo.getCantMaxUbicacion();
					cantidad = cantidad - articulo.getCantMaxUbicacion();
				}
				Stock stock = obtenerUbicacionLibre();
				ArticuloEnStock artEnStock = new ArticuloEnStock(stock.getCodigoUbicacion(), cantAUbicar, lote, fechaVenc, Calendar.getInstance().getTime(), proveedor, precioCompra);
				stock.actualizarCantidadReal(cantAUbicar);
				articulo.getArticulosEnStock().add(artEnStock);
				articulosEnStock.add(artEnStock);
				// Aquí debería generarse el movimiento de ajuste de stock por compra
			}	
		}
		return articulosEnStock;
	}
	
	public ArticuloEnStock buscarArticuloEnStock(String codBarra, String lote) {
		return null;
	}
	
	public MovimientoStock buscarMovStock(int idMov) {
		return null;
	}
	
	public void modificarArticulo(String codB, String desc, String pres, int tam, String uni, float pre, int cantFC, int cantMxUb) {
	
	}
	
	public void modificarArticuloEnStock(String codB, String lote, Date venc, Date fCompra, String proov, float preCom) {
	
	}
	
	public void descontarStock(Articulo articulo, ArticuloEnStock articuloEnStock, Stock stock, int cantidad, int numPedido) {
	
	}
	
}