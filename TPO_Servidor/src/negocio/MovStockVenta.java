// @Marce: implementar funcionalidad
// @Facu: implementar saveMe
package negocio;

import java.util.Date;

import dao.MovStockVentaDAO;
import entities.MovStockVentaEntity;

public class MovStockVenta extends MovimientoStock {

	public MovStockVenta(MovStockVentaEntity ms) {
		super(ms);
		this.setPedido(new Pedido(ms.getPe()));
	}

	private Pedido pedido;
	
	public MovStockVenta(char tipoAjuste,Date fecha,int cant, Pedido p) {
		// TODO Auto-generated constructor stub
		super(tipoAjuste,fecha,cant);
		this.setPedido(p);
		setTipoMov("SM");
	}

	// NOTAS_FG: Ver si se puede reemplazar usando el constructor	
	public void registrarMovStockVenta(Pedido p, int cantidad) {
		
	}


	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}	
	
	public void saveMe() {
		MovStockVentaDAO.getInstance().grabar(this);
	}	

	//@Facu: implementar metodo
	public void updateMe() {

	}	

}
