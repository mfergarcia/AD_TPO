package test;

import java.util.ArrayList;
import java.util.Collection;
import delegados.SistemaBD;
import dto.ArticuloDTO;
import dto.ArticuloEnStockDTO;
import dto.PedidoDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

public class TestCliente_Pedido2 {
		public static void main(String[] args) {
			try {
				SistemaBD bd = new SistemaBD();
				
				//Prueba Rechazo Pedido
					bd.rechazarPedido(1, "Direccion Erronea");
				/*
				
				//Prueba Obtener Proveedores
				System.out.println(bd.obtenerProveedores("AAA111"));
				
				//Prueba Obtener Pedido
				Collection<PedidoDTO> p= new ArrayList<PedidoDTO>();
				p=bd.obtenerPedidosCompletos();
				for(PedidoDTO pd: p)
					System.out.println("Pedido numero: "+pd.getNumPedido()+" del Cliente: "+pd.getIdCliente());
			
				*/
				//Prueba Solicitar Pedido
				
				//System.out.println(bd.solicitarPedido(12));
				
				//Prueba Obtener Pedidos PENDIETE DEPOSITO
				/*
				Collection <PedidoDTO> po= new ArrayList<PedidoDTO>();
				po= bd.obtenerPedidosPendDeposito();
				for(PedidoDTO pd: po)
					System.out.println("Pedido numero: "+pd.getNumPedido()+" del Cliente: "+pd.getIdCliente()+ " estado: "+ pd.getEstado());
				*/
					
				//Prueba Preparar Pedido
				Collection<ArticuloEnStockDTO> art= new ArrayList<ArticuloEnStockDTO>();
				art= bd.prepararPedido(11);
			
			} catch (ExcepcionComunicacion e) {
				System.out.println(e.getMensaje());
			} catch (ExcepcionSistema es) {
				System.out.println(es.getMensaje());
			}
		}
}
