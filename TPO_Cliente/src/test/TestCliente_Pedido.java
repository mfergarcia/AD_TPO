package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import delegados.SistemaBD;
import dto.ArticuloDTO;
import dto.DireccionDTO;
import dto.ItemArticuloDTO;
import dto.ItemOCDTO;
import dto.OrdenDeCompraDTO;
import dto.PedidoDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

public class TestCliente_Pedido {

	public static void main(String[] args) {
		try {
			SistemaBD bd = new SistemaBD();

			// Prueba Generar Pedido
			
			int numCliente = 4;
			int numOCABuscar = 1;
			DireccionDTO dirEntrega = new DireccionDTO();
			dirEntrega.setCalle("Av de Mayo");
			dirEntrega.setNumero(200);
			dirEntrega.setCodigoPostal("1424");
			dirEntrega.setLocalidad("C.A.B.A.");
			PedidoDTO pedido = new PedidoDTO();
			pedido.setIdCliente(numCliente);
			pedido.setDirEntrega(dirEntrega);
			OrdenDeCompraDTO ordenOC = bd.obtenerOrdenDeCompra(numOCABuscar);
			ItemOCDTO auxItemOC;
			ItemArticuloDTO itemArt;
			for(Iterator<ItemOCDTO> i = ordenOC.getItems().iterator(); i.hasNext(); ) {
				auxItemOC = i.next();
				itemArt = new ItemArticuloDTO();
				itemArt.setArticuloDTO(auxItemOC.getArticulo());
				itemArt.setCant(5);
				pedido.agregarItem(itemArt);
			}
			pedido = bd.generarPedido(pedido);
			System.out.println("Se ha generado el pedido : " + pedido.getNumPedido() + "en estado " + pedido.getEstado() + " con los siguientes items: ");
			ItemArticuloDTO auxItemArt;
			for (Iterator<ItemArticuloDTO> j = pedido.getItems().iterator(); j.hasNext(); ) {
				auxItemArt = j.next();
				System.out.println(auxItemArt.getArticuloDTO().getCodigoBarras() + " cant: " + auxItemArt.getCant());
			}
			/*	 */
			/*
			// Prueba Obtener Pedidos Por Cliente
			
			Collection<PedidoDTO> pedidos = bd.obtenerPedidosPorCliente(numCliente);
			System.out.println("Se han obtenido los siguientes pedidos del cliente " + numCliente);
			PedidoDTO auxPedido;
			for (Iterator<PedidoDTO> k = pedidos.iterator(); k.hasNext(); ) {
				auxPedido = k.next();
				System.out.println("Pedido " + auxPedido.getNumPedido());
			}
			

			// Prueba Obtener Pedidos A Confirmar
			
			pedidos = bd.obtenerPedidosAConfirmar();
			System.out.println("Se han obtenido los siguientes pedidos A Confirmar");
			for (Iterator<PedidoDTO> l = pedidos.iterator(); l.hasNext(); ) {
				auxPedido = l.next();
				System.out.println("Pedido " + auxPedido.getNumPedido());
			}

			*/
			// Prueba Aprobar Pedido

			int numPedidoABuscar = 1;
			String nuevoEstado = bd.aprobarPedido(numPedidoABuscar);
			System.out.println("El estado del pedido " + numPedidoABuscar + " es: " + nuevoEstado);
			
			
			
		} catch (ExcepcionComunicacion e) {
			System.out.println(e.getMensaje());
		} catch (ExcepcionSistema es) {
			System.out.println(es.getMensaje());
		}
	}

}
