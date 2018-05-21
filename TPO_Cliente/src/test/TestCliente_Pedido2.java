package test;

import delegados.SistemaBD;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

public class TestCliente_Pedido2 {
		public static void main(String[] args) {
			try {
				SistemaBD bd = new SistemaBD();
				bd.rechazarPedido(1, "Direccion Erronea");
				//System.out.println(bd.obtenerProveedores("AAA111"));
			
			
			
			
			} catch (ExcepcionComunicacion e) {
				System.out.println(e.getMensaje());
			} catch (ExcepcionSistema es) {
				System.out.println(es.getMensaje());
			}
		}
}
