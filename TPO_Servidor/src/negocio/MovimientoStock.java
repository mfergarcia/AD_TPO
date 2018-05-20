// @Marce: implementar funcionalidad
// @Facu: implementar saveMe
package negocio;

import java.util.Date;

import entities.MovimientoStockEntity;

public class MovimientoStock {

	private int idMov;
	// tipoAjuste: 'A' (alta), 'B' (baja)
	private char tipoAjuste;
	private Date fecha;
	private int cant;
	// tipoMov:'SV' Stock Venta, 'SC'Stock Compra, 'SM'Stock Mantenimiento, 'SA'Stock Ajuste
	private String tipoMov;
	
	
	public MovimientoStock(char tipoAjuste,Date fecha,int cant) {
		super();
		this.tipoAjuste = tipoAjuste;
		this.fecha = fecha;
		this.cant = cant;
		
	}
	
	public MovimientoStock(MovimientoStockEntity ms) {
		this.setIdMov(ms.getIdMov());
		this.setTipoAjuste(ms.getTipoAjuste());;
		this.setFecha(ms.getFecha());
		this.setCant(ms.getCant());
		this.setTipoMov(ms.getTipoMov());
		
	}
	
	// Valida que el objeto sea un determinado MovStock
	public boolean sosMovimiento(int idMov) {
		return (this.getIdMov() == idMov);
	}
	
	
	public int getIdMov() {
		return idMov;
	}

	public void setIdMov(int idMov) {
		this.idMov = idMov;
	}

	public char getTipoAjuste() {
		return tipoAjuste;
	}

	public void setTipoAjuste(char tipoAjuste) {
		this.tipoAjuste = tipoAjuste;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}	
	
	public String getTipoMov() {
		return tipoMov;
	}

	public void setTipoMov(String string) {
		this.tipoMov = string;
	}
	
}
