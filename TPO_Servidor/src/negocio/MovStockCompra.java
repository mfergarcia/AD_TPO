// @Marce: implementar funcionalidad
// @Facu: implementar saveMe
package negocio;

import entities.MovimientoStockEntity;

public class MovStockCompra extends MovimientoStock {

	public MovStockCompra(MovimientoStockEntity ms) {
		super(ms);
		// TODO Auto-generated constructor stub
	}

	private OrdenDeCompra oc;
	
	/*public MovStockCompra() {
		// TODO Auto-generated constructor stub
	}
*/
	// NOTAS_FG: Ver si se puede reemplazar usando el constructor	
	public void crearMovStockCompra(int cant, OrdenDeCompra oc) {
		
	}

	// NOTAS_FG: Para qu� sirve este metodo? Qu� calcula?
	public void calcularMoviento() {
	
	}

	public OrdenDeCompra getOc() {
		return oc;
	}

	public void setOc(OrdenDeCompra oc) {
		this.oc = oc;
	}	
	
	//@Facu: implementar metodo
	public void saveMe() {

	}	
	
}
