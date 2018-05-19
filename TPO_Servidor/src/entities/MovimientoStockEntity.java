package entities;

import java.util.Date;

public class MovimientoStockEntity {
	
	private int idMov;
	// tipoAjuste: 'A' (alta), 'B' (baja)
	private char tipoAjuste;
	private Date fecha;
	private int cant;
	// tipoMov:'SV' Stock Venta, 'SC'Stock Compra, 'SM'Stock Mantenimiento, 'SA'Stock Ajuste
	private char tipoMov;
	
	public MovimientoStockEntity(char tipoAjuste,Date fecha,int cant, char tipoMov) {
		super();
		this.tipoAjuste = tipoAjuste;
		this.fecha = fecha;
		this.cant = cant;
		this.tipoMov = tipoMov;
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
	public char getTipoMov() {
		return tipoMov;
	}
	public void setTipoMov(char tipoMov) {
		this.tipoMov = tipoMov;
	}

	

}
