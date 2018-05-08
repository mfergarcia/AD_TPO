//PENDIENTE: Programar todo
package negocio;

import java.util.Date;

public class MovimientoStock {

	private int idMov;
	// tipoMov: 'A' (alta), 'B' (baja)
	private char tipoMov;
	private Date fecha;
	private int cant;
	
	public MovimientoStock() {
		// TODO Auto-generated constructor stub
	}

	// Valida que el objeto sea un determinado MovStock
	public boolean sosMovimiento(int idMov) {
		return (this.getIdMov() == idMov);
	}
	
	// NOTAS_FG: Para qué sirve este metodo? Qué calcula?
	public void calcularMoviento() {
		
	}

	public int getIdMov() {
		return idMov;
	}

	public void setIdMov(int idMov) {
		this.idMov = idMov;
	}

	public char getTipoMov() {
		return tipoMov;
	}

	public void setTipoMov(char tipoMov) {
		this.tipoMov = tipoMov;
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
	
}
