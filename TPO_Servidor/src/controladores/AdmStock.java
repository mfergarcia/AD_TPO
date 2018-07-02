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
import negocio.OrdenDeCompra;
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
	public Stock obtenerUbicacionLibre() {
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
		if (articulo != null) {
			OrdenDeCompra ordenCompra = AdmCompras.getInstancia().obtenerOrdenDeCompra(numOC);
			artEnStockDTO.setProveedor(ordenCompra.getProveedor());
			return articulo.cargarStock(artEnStockDTO);
		}
		else 
			return null;
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
	
	public void registrarMovStockVenta(Pedido pedido) {
		MovStockVenta movSV = new MovStockVenta('B',Calendar.getInstance().getTime(), 0,pedido );
		movSV.saveMe();
	}
	
}