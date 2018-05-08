//PENDIENTE: Completar programación y desde VERRRR
package controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import negocio.Articulo;
import negocio.ArticuloEnStock;
import negocio.ItemArticulo;
import negocio.MovimientoStock;
import negocio.Pedido;
import negocio.Stock;

public class AdmStock {

	private static AdmStock instancia;
	private Collection<Articulo> articulos;
	private Collection<Stock> stock;
	/* NOTA_FG: Para mi no es necesaria esta coleccion porque el stock de articulos se
	 * accede a traves de los articulos, nunca directamente.
	private Collection<ArticuloEnStock> articulosEnStock;
	*/
	private Collection <MovimientoStock> movimientosStock;	
	
	// Constructor privado (Patron Singleton)
	private AdmStock() {
		// TODO Auto-generated constructor stub
		// Inicializar controlador
		cargarArticulos();
		cargarStock();
		cargarMovimientosStock();
	}
	
	// Invoca al DAO, carga los Articulos existentes
	// de la BD en la colección articulos 
	private void cargarArticulos() {
		articulos = new ArrayList<Articulo>();
	}

	// Invoca al DAO, carga las posiciones de Stock existentes
	// de la BD en la colección stock 
	private void cargarStock() {
		
	}

	// Invoca al DAO, carga los movimientos de Stock existentes
	// de la BD en la colección movimientosStock 
	private void cargarMovimientosStock() {
		
	}

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
	
	// Agrega un articulo al catálogo
	public void agregarArticulo(Articulo art) {
		this.articulos.add(art);
	}
	
	// Recorre la coleccion articulos y devuelve el objeto Articulo que 
	// coincide con el codBarras dado. Si no lo encuentra devuelve null
	public Articulo buscarArticulo(String codBarras) {
		Articulo aux;
		for (Iterator<Articulo> i = this.articulos.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getCodigoBarras().equals(codBarras))
				return aux;
		}
		return null;
	}
	
	// Recorre la coleccion articulos y devuelve aquellos que están en 
	// estado ACTIVO ('A')
	public Collection<Articulo> obtenerCatalogo() {
		Collection<Articulo> catalogoArticulos = new ArrayList<Articulo>();
		Articulo aux;
		for (Iterator<Articulo> i = this.articulos.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getEstado() == 'A')
				catalogoArticulos.add(aux);
		}
		return catalogoArticulos;
	}
	
	//NOTAS_FG: Revisar si se puede adaptar para ser re-ejecutado cuando se cumple una OC
	public String reservarStockPedido(Pedido pedido) {
		String estadoPedido = "COMPLETO";
		Collection<ArticuloEnStock> articulosEnStock;
		ItemArticulo auxItemArt;
		// Recorro los articulos del pedido para tratar de reservar el stock
		for (Iterator<ItemArticulo> i = pedido.getArticulos().iterator(); i.hasNext(); ) {
			auxItemArt = i.next();
			// Solicito ordenar los Articulos En Stock por Vecha de Vencimiento
			auxItemArt.getArticulo().ordenarArtEnStockPorFechVenc();
			articulosEnStock = auxItemArt.getArticulo().getArticulosEnStock();
			int cantAReservar = auxItemArt.getCant();
			int cantReservable;
			ArticuloEnStock auxArtEnStock;
			// Recorro los articulos que tengo en stock para ver si puedo reservar la cantidad
			// pedida de este articulo
			Iterator<ArticuloEnStock> j = articulosEnStock.iterator();
			while (cantAReservar > 0 && j.hasNext()) {
				auxArtEnStock = j.next();
				// Obtengo el stock del Articulo En Stock
				Stock stock = obtenerStock(auxArtEnStock.getCodigoUbicacion());
				if (stock != null) {
					cantReservable = stock.cantidadReservableEnStock();
					if (cantReservable > 0) {
						// Hay stock, necesito comparar cuanto hay vs lo que necesito
						if (cantReservable >= cantAReservar) {
							// Reservo solo lo que necesito
							if (stock.reservarStock(cantAReservar))
								cantAReservar = 0;
						}
						else {
							// Reservo todo lo que hay y sigo buscando más cantidad en el siguiente 
							// Articulo En Stock
							if (stock.reservarStock(cantReservable))
								cantAReservar = cantAReservar - cantReservable;
						}
					}
				}
			}
			// Verifico si quedo cantidad pendiente por reservar o si se acabaron los Articulos En Stock
			if (cantAReservar > 0) {
				// Ya recorrí todos los Articulos En Stock y aún queda cantidad pendiente por reservar
				// Tengo que generar una Orden de Pedido de Reposicion por la cantidad faltante
				AdmCompras.getInstancia().generarOrdenPedidoRepo(pedido.getNumPedido(), auxItemArt.getArticulo(), cantAReservar);
				auxItemArt.setEstadoStock("PENDIENTE OPR");
				estadoPedido = "PENDIENTE REPOSICION";
			}
			else
				auxItemArt.setEstadoStock("RESERVADO");
		}
		return estadoPedido;
	}
	
	public Collection<Stock> localizarStockArticulo(Articulo articulo, int cantidad) {
		return stock;
		
	}
	
	public void solicitarArticulos(int numPedido) {
	
	}
	
	public void ajustarInventario(int cant, String codB, String lote, String ubicacion) {
	
	}
	
	public void ajustarStockC(int idOC) {
	
	}
	
	public void ajustarStockMant(int cant, String usuarioRegistrado, String autorizante, String destinoFinal, String ubicacion) {
	
	}
	
	public void cargarArticuloEnStock(String codB, String lote, Date venc, Date fCompra, String proov, float preCom) {
	
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
	
	public Collection<ArticuloEnStock> procesarOrdenDeCompra(int idOC) {
		return null;
	}
	
	public void descontarStock(Articulo articulo, ArticuloEnStock articuloEnStock, Stock stock, int cantidad, int numPedido) {
	
	}
	
}