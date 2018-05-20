// @Marce: implementar funcionalidad
// @Facu: implementar saveMe
package negocio;

import entities.MovimientoStockEntity;

public class MovStockAjuste extends MovimientoStock {

	public MovStockAjuste(MovimientoStockEntity ms) {
		super(ms);
		// TODO Auto-generated constructor stub
	}

	private ArticuloEnStock artEnStock;	
	
	/*public MovStockAjuste() {
		// TODO Auto-generated constructor stub
	}*/

	// NOTAS_FG: Ver si se puede reemplazar usando el constructor
	public void registrarMovStockAjuste(int cant) {
		
	}

	// NOTAS_FG: Para qué sirve este metodo? Qué calcula?	
	public void calcularMoviento() {
		
	}
	
	public ArticuloEnStock getArtEnStock() {
		return artEnStock;
	}

	public void setArtEnStock(ArticuloEnStock artEnStock) {
		this.artEnStock = artEnStock;
	}

	//@Facu: implementar metodo
	public void saveMe() {

	}	

}
