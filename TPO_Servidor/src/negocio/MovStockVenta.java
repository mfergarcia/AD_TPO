// @Marce: implementar funcionalidad
// @Facu: implementar saveMe
package negocio;

import entities.MovimientoStockEntity;

public class MovStockVenta extends MovimientoStock {

	public MovStockVenta(MovimientoStockEntity ms) {
		super(ms);
		// TODO Auto-generated constructor stub
	}

	private Pedido pedido;
	
	/*public MovStockVenta() {
		// TODO Auto-generated constructor stub
	}*/

	// NOTAS_FG: Ver si se puede reemplazar usando el constructor	
	public void registrarMovStockVenta(Pedido p, int cantidad) {
		
	}

	// NOTAS_FG: Para qué sirve este metodo? Qué calcula?	
	public void calcularMoviento() {
	
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}	
	
	//@Facu: implementar metodo
	public void saveMe() {

	}	

}
