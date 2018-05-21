// @Marce: implementar funcionalidad
// @Facu: implementar saveMe
package negocio;

import java.util.Date;

import dao.MovStockAjusteDAO;
import entities.MovimientoStockEntity;

public class MovStockAjuste extends MovimientoStock {

	public MovStockAjuste(MovimientoStockEntity ms) {
		super(ms);
		// TODO Auto-generated constructor stub
	}

	private ArticuloEnStock artEnStock;	
	
	public MovStockAjuste(char tipoAjuste,Date fecha,int cant) {
		super(tipoAjuste,fecha,cant);
		setTipoMov("SA");
	}

	// NOTAS_FG: Ver si se puede reemplazar usando el constructor, MD No lo borre xq tengo dudas
	public void registrarMovStockAjuste(int cant) {
		
	}

	
	public ArticuloEnStock getArtEnStock() {
		return artEnStock;
	}

	public void setArtEnStock(ArticuloEnStock artEnStock) {
		this.artEnStock = artEnStock;
	}

	public void saveMe() {
		this.setIdMov(MovStockAjusteDAO.getInstance().grabar(this));
	}	

	public void updateMe() {
		MovStockAjusteDAO.getInstance().update(this);
	}	

}
