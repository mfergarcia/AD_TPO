// @Marce: Completar programación de mov de stock
package controladores;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
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
import negocio.MovStockAjuste;
import negocio.MovStockMantenimiento;
import negocio.MovStockVenta;
import negocio.MovimientoStock;
import negocio.Pedido;
import negocio.Stock;

public class AdmStock {

	private static AdmStock instancia;
	
	// Constructor privado (Patron Singleton)
	private AdmStock() {
	}
	
	// Devuelve el Stock para un codigo de ubicacion dado
	public Stock obtenerStock(String codUbicacion) {
		return StockDAO.getInstance().findByID(codUbicacion);
	}
	
	// Recupera una ubicación libre para que sea asignada a un nuevo Articulo En Stock
	// La ubicación se bloquea hasta que sea asignado el stock
	private Stock obtenerUbicacionLibre() {
		return StockDAO.getInstance().findByEstadoLibre();
	}
	
	public static AdmStock getInstancia() {
		if (instancia == null) {
			instancia = new AdmStock();
		}
		return instancia;
	}	
	
	// Obtiene un Articulo para un determinado codigo de barras
	public Articulo obtenerArticulo(String codBarras) {
		return ArticuloDAO.getInstance().findByID(codBarras);
	}
	
	// Devuelve todos los articulos activos (estado = 'A') 
	public Collection<Articulo> obtenerCatalogo() {
		return ArticuloDAO.getInstance().showAll();
	}
	
	
	
	// Verifica la existencia de Stock de cada item del Pedido y actualiza el estado
	// del stock de cada Item Articulo del Pedido. Si detecta que hay stock faltante,
	// se genera una Orden de Pedido de Reposición
	/*
	public String reservarStockPedido(Pedido pedido) {
		String estadoPedido = "COMPLETO";
		Collection<ArticuloEnStock> artEnStock;
		ItemArticulo auxItemArt;
		// Recorre los articulos del pedido para tratar de reservar el stock
		for (Iterator<ItemArticulo> i = pedido.getArticulos().iterator(); i.hasNext(); ) {
			auxItemArt = i.next();
			if (!auxItemArt.getEstadoStock().equals("RESERVADO")) {
				// El ítem aún no pudo ser reservado
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
					// Obtiene el Stock actual del Articulo En Stock
					Stock stock = this.obtenerStock(auxArtEnStock.getCodigoUbicacion());
					if (stock != null) {
						cantReservable = stock.cantidadReservableEnStock();
						if (cantReservable > 0) {
							// Hay stock para reservar, necesita comparar cuanto hay vs lo que se necesita
							if (cantReservable >= cantRequerida) {
								// Reserva solo lo que se necesita
								if (stock.reservarStock(cantRequerida)) {
									stock.updateMe();
									cantRequerida = 0;
								}	
							}
							else {
								// Reserva todo lo que hay y sigue buscando más cantidad en el siguiente 
								// Articulo En Stock
								if (stock.reservarStock(cantReservable)) {
									stock.updateMe();
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
				auxItemArt.updateMe();
			}
		}
		return estadoPedido;
	}
	*/
	
	// Localiza los Articulos En Stock que se deben considerar para preparar el Pedido
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
				stock.updateMe();
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
	// NOTAS_FG: Revisar si está ok la ejecución de los ciclos
	// Ejecuta la actualización del Stock por la Venta de un Pedido
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
					stock.updateMe();
					aux.setEstadoStock("DESCONTADO");
					aux.updateMe();
					if (stock.getCantidadReal() == 0) {
						// Ya no queda más stock de este Artículo en Stock, se desasigna el código de ubicación
						// para que no se intente recuperar más el Artículo en Stock en futuras reservas o 
						// preparaciones de Pedido
						ArticuloEnStock artEnStock = aux.getArticulo().obtenerArtEnStock(auxDTO.getId());
						artEnStock.setCodigoUbicacion(null);
						artEnStock.updateMe();
					}
					// Aquí debería generarse el movimiento de stock por venta.
				}
			}
		}
		boolean ok = true;
		for (Iterator<ItemArticulo> k = pedido.getArticulos().iterator(); k.hasNext(); ) {
			aux = k.next();
			if (!aux.getEstadoStock().equals("DESCONTADO"))
				// Verifica que todos los Item Articulo hayan sido descontados del Stock
				ok = false;
		}
		return ok;
	}
	
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

	// @Marce: agregar creación del movimiento de stock
	// Ingresa el Stock de un Artículo adquirido a través de una Orden De Compra
	// Devuelve una colección de Articulos En Stock que permite identificar qué cantidad
	// se debe almacenar en cada ubicación del Stock, considerando la cantidad máxima de 
	// ocupación del Artículo
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
				Stock stock = this.obtenerUbicacionLibre();
				stock.actualizarEstado("BLOQUEADA");
				stock.updateMe();
				ArticuloEnStock artEnStock = new ArticuloEnStock(stock.getCodigoUbicacion(), cantAUbicar, artEnStockDTO);
				// artEnStock.saveMe();
				stock.actualizarCantidadReal(cantAUbicar);
				stock.updateMe();
				articulo.agregarArtEnStock(artEnStock);
				articulosEnStock.add(artEnStock);
				// Aquí debería generarse el movimiento de ajuste de stock por compra
			}	
			articulo.updateMe();
		}
		System.out.println(articulosEnStock.size());
		return articulosEnStock;
	}
	
	public void ajustarStockPorInventario(int cant, String codB, String lote, String ubicacion) {
		Stock stock = this.obtenerStock(ubicacion);
		stock.actualizarCantidadReal(cant);
		stock.updateMe();
		if (cant >0){
			MovStockAjuste movSA = new MovStockAjuste('A',Calendar.getInstance().getTime(), 0 );
			movSA.saveMe();
		}
			else{
				MovStockAjuste movSA = new MovStockAjuste('B',Calendar.getInstance().getTime(), 0 );
				movSA.saveMe();
			}
	}
	
	public void ajustarStockPorMant(int cant, String usuarioRegistrado, String autorizante, String destinoFinal, String ubicacion) {
		Stock stock = this.obtenerStock(ubicacion);
		stock.actualizarCantidadReal(cant);
		stock.updateMe();
		
		if (cant >0){
			MovStockMantenimiento movSM = new MovStockMantenimiento('A', Calendar.getInstance().getTime(),0, usuarioRegistrado, autorizante, destinoFinal);
			movSM.saveMe();
		}
		else
		{
			MovStockMantenimiento movSM = new MovStockMantenimiento('B', Calendar.getInstance().getTime(),0, usuarioRegistrado, autorizante, destinoFinal);
			movSM.saveMe();
		}
	}
	
	public void RegistrarMovStockVenta(Pedido pedido) {
		MovStockVenta movSV = new MovStockVenta('B',Calendar.getInstance().getTime(), 0,pedido );
		movSV.saveMe();
	}
	
	
}