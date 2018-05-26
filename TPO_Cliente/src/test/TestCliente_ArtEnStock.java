package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import delegados.SistemaBD;
import dto.ArticuloEnStockDTO;
import dto.ItemOCDTO;
import dto.OrdenDeCompraDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

public class TestCliente_ArtEnStock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SistemaBD bd = new SistemaBD();
			
			// Prueba Obtener Orden De Compra
			
			int numOCABuscar = 1;
			OrdenDeCompraDTO ordenOC = bd.obtenerOrdenDeCompra(numOCABuscar);
			System.out.println("Se obtuvo la Orden De Compra: " + ordenOC.getNumOC());
			
			// Prueba Cargar Articulos En Stock
			
			Collection<ArticuloEnStockDTO> posiciones = new ArrayList<ArticuloEnStockDTO>();
			ItemOCDTO auxItemOC;
			int contador = 10;
			System.out.println("Cantidad de items OC: " + ordenOC.getItems().size());
			
			
			for (Iterator<ItemOCDTO> i = ordenOC.getItems().iterator(); i.hasNext(); ) {
				auxItemOC = i.next();
				ArticuloEnStockDTO artEnStock = new ArticuloEnStockDTO();
				artEnStock.setCodigoBarras(auxItemOC.getArticulo().getCodigoBarras());
				artEnStock.setCantidad(auxItemOC.getCantidad());
				artEnStock.setFechaCompra(Calendar.getInstance().getTime());
				Calendar cal = Calendar.getInstance();
				cal.setTime(Calendar.getInstance().getTime());
				cal.add(Calendar.DAY_OF_YEAR, contador);
				artEnStock.setFechaVencimiento(cal.getTime());
				artEnStock.setLote(auxItemOC.getArticulo().getCodigoBarras().substring(3));
				artEnStock.setPrecioCompra(auxItemOC.getArticulo().getPrecioVta()/2);
				artEnStock.setProveedor(ordenOC.getProveedor());
				System.out.print("Estoy por llamar al cargar articulo ");
				posiciones = bd.cargarArticuloEnStock(ordenOC.getNumOC(), artEnStock);
				System.out.print("Me devolvio " + posiciones.size() + " posiciones");
				ArticuloEnStockDTO printAES;
				for (Iterator<ArticuloEnStockDTO> j = posiciones.iterator(); j.hasNext(); ) {
					printAES = j.next();
					System.out.println("Posicion " + printAES.getCodigoUbicacion() + ": " + printAES.getCantidad());
				}
				contador++;
			}	
			
			// Prueba Cumplir Orden De Compra
			
			String nuevoEstado = bd.cumplirOrdenDeCompra(numOCABuscar);
			System.out.println("El nuevo estado de la Orden De Compra " + numOCABuscar + " es: " + nuevoEstado);
			
		} catch (ExcepcionComunicacion e) {
			System.out.println(e.getMensaje());
		} catch (ExcepcionSistema es) {
			System.out.println(es.getMensaje());
		}
	}
}