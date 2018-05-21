package test;

import delegados.SistemaBD;
import dto.ArticuloDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

public class TestCliente_BajaArticulo_ModificarArticulo {
	public static void main(String[] args) {
	
	try {
		SistemaBD bd = new SistemaBD();
		
		String codbarrasArticuloBaja="AAA111";
		bd.bajaArticulo(codbarrasArticuloBaja);
		ArticuloDTO a=bd.obtenerArticulo(codbarrasArticuloBaja);
		System.out.println("Se elimino el Articulo: "+ a.getDescripcion()+" Estado: "+ a.getEstado());
		a.setEstado('A');
		a.setDescripcion("Bebida");
		bd.modificarArticulo(a);
		a=bd.obtenerArticulo(a.getCodigoBarras());
		System.out.println("Se Activo el Articulo: "+ a.getDescripcion()+" Estado: "+ a.getEstado());
	}catch (ExcepcionComunicacion e) {
		System.out.println(e.getMensaje());
	}catch (ExcepcionSistema es) {
		System.out.println(es.getMensaje());
	}
	

	}
}

