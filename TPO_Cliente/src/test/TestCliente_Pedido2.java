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
				/*
				//Prueba Obtener ultimos 3 proveedores 
				System.out.println(bd.obtenerProveedores("III666"));
				
				Collection<PedidoDTO> p= new ArrayList<PedidoDTO>();

				// bd.rechazarPedido(1, "Direccion Erronea");
			
				
			
				//Prueba Obtener Pedido
				p= new ArrayList<PedidoDTO>();
				p=bd.obtenerPedidosCompletos();
				for(PedidoDTO pd: p)
					System.out.println("Pedido numero: "+pd.getNumPedido()+" del Cliente: "+pd.getIdCliente());
			
				
				//Prueba Solicitar Pedido
				*/
				System.out.println(bd.solicitarPedido(2));

				/*
				//Prueba Obtener Pedidos PENDIETE DEPOSITO
			
				p= new ArrayList<PedidoDTO>();
				p= bd.obtenerPedidosPendDeposito();
				for(PedidoDTO pd: p)
					System.out.println("Pedido numero: "+pd.getNumPedido()+" del Cliente: "+pd.getIdCliente()+ " estado: "+ pd.getEstado());
				
				//Prueba Preparar Pedido
				Collection<ArticuloEnStockDTO> art= new ArrayList<ArticuloEnStockDTO>();
				bd.actualizarStockPorVenta(1, bd.prepararPedido(1));
				
				//Prueba obtener Pedidos a despachar
				
				p= new ArrayList<PedidoDTO>();
				p=bd.obtenerPedidosADespachar();
				for(PedidoDTO pd: p)
					System.out.println("Pedido numero: "+pd.getNumPedido()+" del Cliente: "+pd.getIdCliente()+ " estado: "+ pd.getEstado());
				
				//Generar Fecha Entrega
				
				bd.registrarFechaEntrega(4, new java.util.Date(System.currentTimeMillis()));*/
				
				
				
				
			} catch (ExcepcionComunicacion e) {
				System.out.println(e.getMensaje());
			} catch (ExcepcionSistema es) {
				System.out.println(es.getMensaje());
			}
		}
}
