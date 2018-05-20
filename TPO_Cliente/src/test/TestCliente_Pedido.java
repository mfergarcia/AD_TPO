package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import delegados.SistemaBD;
import dto.ArticuloDTO;
import dto.DireccionDTO;
import dto.ItemArticuloDTO;
import dto.PedidoDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

public class TestCliente_Pedido {

	public static void main(String[] args) {
		try {
			SistemaBD bd = new SistemaBD();

			// Prueba Generar Pedido
			
			int numCliente = 1;
			DireccionDTO dirEntrega = new DireccionDTO();
			dirEntrega.setCalle("Venezuela");
			dirEntrega.setNumero(200);
			dirEntrega.setCodigoPostal("1424");
			dirEntrega.setLocalidad("C.A.B.A.");
			PedidoDTO pedido = new PedidoDTO();
			pedido.setIdCliente(numCliente);
			pedido.setDirEntrega(dirEntrega);
			Collection<ArticuloDTO> catalogo = new ArrayList<ArticuloDTO>();
			catalogo = bd.obtenerCatalogo();
			ArticuloDTO auxArt;
			ItemArticuloDTO itemArticulo;
			for(Iterator<ArticuloDTO> i = catalogo.iterator(); i.hasNext(); ) {
				auxArt = i.next();
				itemArticulo = new ItemArticuloDTO();
				itemArticulo.setArticuloDTO(auxArt);
				itemArticulo.setCant(10);
				pedido.agregarItem(itemArticulo);
			}
			pedido = bd.generarPedido(pedido);
			System.out.println("Se ha generado el pedido : " + pedido.getNumPedido() + "en estado " + pedido.getEstado() + " con los siguientes items: ");
			ItemArticuloDTO auxItemArt;
			for (Iterator<ItemArticuloDTO> j = pedido.getItems().iterator(); j.hasNext(); ) {
				auxItemArt = j.next();
				System.out.println(auxItemArt.getArticuloDTO().getCodigoBarras() + " cant: " + auxItemArt.getCant());
			}

			// Prueba Obtener Pedidos Por Cliente
			Collection<PedidoDTO> pedidos = bd.obtenerPedidosPorCliente(numCliente);
			System.out.println("Se han obtenido los siguientes pedidos del cliente " + numCliente);
			PedidoDTO auxPedido;
			for (Iterator<PedidoDTO> k = pedidos.iterator(); k.hasNext(); ) {
				auxPedido = k.next();
				System.out.println("Pedido " + auxPedido.getNumPedido());
			}
			
			
		} catch (ExcepcionComunicacion e) {
			System.out.println(e.getMensaje());
		} catch (ExcepcionSistema es) {
			System.out.println(es.getMensaje());
		}
	}

}
