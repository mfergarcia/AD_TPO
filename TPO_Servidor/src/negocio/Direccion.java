//LISTO PARA PROBAR
package negocio;

import dto.DireccionDTO;

public class Direccion {
	private int id;
	private String calle;
	private int numero;
	private String codigoPostal;
	private String localidad;

	public Direccion() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return (this.getCalle() + " " + this.getNumero() + ", " + this.getLocalidad() + " - CP: " + this.getCodigoPostal());
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

	public DireccionDTO toDTO() {
		DireccionDTO direccionDTO = new DireccionDTO();
		direccionDTO.setCalle(this.getCalle());
		direccionDTO.setNumero(this.getNumero());
		direccionDTO.setLocalidad(this.getLocalidad());
		direccionDTO.setCodigoPostal(this.getCodigoPostal());
		return direccionDTO;
	}
	
	//@Facu: implementar metodo
	public void saveMe() {

	}	
}
