// LISTO PARA PROBAR
package dto;

import java.io.Serializable;

public class DireccionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String calle;
	private int numero;
	private String codigoPostal;
	private String localidad;
	
	public DireccionDTO() {

	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String toString() {
		String direccion = this.getCalle() + " " + this.getNumero() + ", " + this.getLocalidad() + " - " + this.getCodigoPostal();
		return direccion;
	}
	
}
