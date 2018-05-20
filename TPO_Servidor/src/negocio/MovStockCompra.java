// @Marce: implementar funcionalidad
// @Facu: implementar saveMe
package negocio;

import java.util.Date;

import dao.MovStockCompraDAO;
import entities.MovStockCompraEntity;
import entities.MovimientoStockEntity;

public class MovStockCompra extends MovimientoStock {

	

	private OrdenDeCompra oc;
	
	
	public MovStockCompra(MovStockCompraEntity ms) {
		super(ms);
		this.setOc(new OrdenDeCompra(ms.getOce()));
	}
	
	public MovStockCompra(char tipoAjuste,Date fecha,int cant, OrdenDeCompra oc) {
		// TODO Auto-generated constructor stub
		super(tipoAjuste,fecha,cant);
		this.setOc(oc);
		setTipoMov("SC");
		 
		
	}

	// NOTAS_FG: Ver si se puede reemplazar usando el constructor	
	public void crearMovStockCompra(int cant, OrdenDeCompra oc) {
		
	}


	public OrdenDeCompra getOc() {
		return oc;
	}

	public void setOc(OrdenDeCompra oc) {
		this.oc = oc;
	}	
	
	//@Facu: implementar metodo
	public void saveMe() {
		MovStockCompraDAO.getInstance().grabar(this);
	}	
	
}
