package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import delegados.SistemaBD;
import dto.ArticuloDTO;
import dto.OrdenDeCompraDTO;
import dto.ItemOCDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

public class TestCliente_Articulos {

	public static void main(String[] args) {
		try {
			SistemaBD bd = new SistemaBD();

			// Prueba Alta Articulo

			Collection<ArticuloDTO> articulos = new ArrayList<ArticuloDTO>();
			ArticuloDTO articulo = new ArticuloDTO();
			articulo.setCodigoBarras("JJJ777");
			articulo.setDescripcion("Cindor");
			articulo.setPresentacion("PACK");
			articulo.setTamaño(100);
			articulo.setUnidad("Carton 250ml");
			articulo.setPrecioVta(1500);
			articulo.setCantMaxUbicacion(1000);
			articulo.setCantFijaCompra(1000);
			articulo = bd.altaArticulo(articulo);
			System.out.println("Alta Articulo: " + articulo.getCodigoBarras() + ", " + articulo.getDescripcion() + " " + articulo.getPresentacion() + " " + articulo.getTamaño() + " " + articulo.getUnidad());
			articulos.add(articulo);
			
			articulo = new ArticuloDTO();
			articulo.setCodigoBarras("KKK888");
			articulo.setDescripcion("Sprite");
			articulo.setPresentacion("CAJON");
			articulo.setTamaño(8);
			articulo.setUnidad("Botellas 1500ml");
			articulo.setPrecioVta(320);
			articulo.setCantMaxUbicacion(50);
			articulo.setCantFijaCompra(100);
			articulo = bd.altaArticulo(articulo);
			System.out.println("Alta Articulo: " + articulo.getCodigoBarras() + ", " + articulo.getDescripcion() + " " + articulo.getPresentacion() + " " + articulo.getTamaño() + " " + articulo.getUnidad());
			articulos.add(articulo);
			
			articulo = new ArticuloDTO();
			articulo.setCodigoBarras("LLL999");
			articulo.setDescripcion("Fideos Spaghetti Matarazzo");
			articulo.setPresentacion("Pack");
			articulo.setTamaño(10);
			articulo.setUnidad("Bolsas 1kg");
			articulo.setPrecioVta(200);
			articulo.setCantMaxUbicacion(1000);
			articulo.setCantFijaCompra(500);
			articulo = bd.altaArticulo(articulo);
			System.out.println("Alta Articulo: " + articulo.getCodigoBarras() + ", " + articulo.getDescripcion() + " " + articulo.getPresentacion() + " " + articulo.getTamaño() + " " + articulo.getUnidad());
			articulos.add(articulo);
			
			// Prueba Obtener Articulo
			
			ArticuloDTO articulo2 = bd.obtenerArticulo("LLL999");
			System.out.println("Se obtuvo el articulo: " + articulo2.getCodigoBarras() + ", " + articulo2.getDescripcion());;
			
			// Prueba Obtener Catalogo
			
			Collection<ArticuloDTO> catalogo = new ArrayList<ArticuloDTO>();
			catalogo = bd.obtenerCatalogo();
			System.out.println("Articulos del catalogo");
			ArticuloDTO auxArt;
			for (Iterator<ArticuloDTO> i = catalogo.iterator(); i.hasNext(); ) {
				auxArt = i.next();
				System.out.println(auxArt.getCodigoBarras() + ", " + auxArt.getDescripcion());
			}
			
			
			// Prueba Generar Orden De Compra

			String proveedor = "MAYORISTA PEPITO CIA";
			OrdenDeCompraDTO ordenOC = bd.generarOrdenDeCompra(proveedor, articulos);
			System.out.println("El numero de OC es: " + ordenOC.getNumOC());
			ItemOCDTO auxItemOC;
			int contador = 1;
			for (Iterator<ItemOCDTO> j = ordenOC.getItems().iterator(); j.hasNext(); ) {
				auxItemOC = j.next();
				System.out.println("Item " + contador + ": " + auxItemOC.getArticulo().getCodigoBarras() + ", cant: " + auxItemOC.getCantidad());
				contador++;
			}
			
			
		} catch (ExcepcionComunicacion e) {
			System.out.println(e.getMensaje());
		} catch (ExcepcionSistema es) {
			System.out.println(es.getMensaje());
		}
	}
	
}
