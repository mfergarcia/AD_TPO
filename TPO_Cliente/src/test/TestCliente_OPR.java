package test;

import delegados.SistemaBD;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

public class TestCliente_OPR {
	public static void main(String[] args) {
		try{
			SistemaBD bd = new SistemaBD();
			
			bd.obtenerOPRPendientes();
			
			
			
		} catch (ExcepcionComunicacion e) {
			System.out.println(e.getMensaje());
		} catch (ExcepcionSistema es) {
			System.out.println(es.getMensaje());
		}
		
	}
}
