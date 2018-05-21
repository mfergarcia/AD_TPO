// @Marce: implementar funcionalidad
// @Facu: implementar saveMe
package negocio;

import java.util.Date;

import dao.MovStockMantenimientoDAO;
import entities.MovStockMantenimientoEntity;

public class MovStockMantenimiento extends MovimientoStock {

	

	private String usuarioRegistrado;
	private String autorizante;
	private String destinoFinal;

	
	public MovStockMantenimiento(MovStockMantenimientoEntity ms) {
		super(ms);
		this.setAutorizante(ms.getAutorizante());
		this.setUsuarioRegistrado(ms.getUsuarioRegistrado());
		this.setDestinoFinal(ms.getDestinoFinal());
	}
	
	public MovStockMantenimiento(char tipoAjuste,Date fecha,int cant,String usuarioRegistrado,String autorizante,String destinoFinal) {
		super(tipoAjuste,fecha,cant);
		this.usuarioRegistrado = usuarioRegistrado;
		this.autorizante = autorizante;
		this.destinoFinal = destinoFinal;
		setTipoMov("SM");
	}

	
	
	// NOTAS_FG: Ver si se puede reemplazar usando el constructor	
	public void registrarMovStockMantenimiento(int cant, String usuarioRegistrado, String autorizante, String destinoFinal) {
		
	}

	
	public String getUsuarioRegistrado() {
		return usuarioRegistrado;
	}

	public void setUsuarioRegistrado(String usuarioRegistrado) {
		this.usuarioRegistrado = usuarioRegistrado;
	}

	public String getAutorizante() {
		return autorizante;
	}

	public void setAutorizante(String autorizante) {
		this.autorizante = autorizante;
	}

	public String getDestinoFinal() {
		return destinoFinal;
	}

	public void setDestinoFinal(String destinoFinal) {
		this.destinoFinal = destinoFinal;
	}

	public void saveMe() {
		this.setIdMov(MovStockMantenimientoDAO.getInstance().grabar(this));
	}	

	public void updateMe() {
		MovStockMantenimientoDAO.getInstance().update(this);
	}	

}
