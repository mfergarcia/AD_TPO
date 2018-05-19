// LISTO PARA PROBAR
package dto;

import java.io.Serializable;

public class ClienteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCliente;
	private char tipo;
	private String identificacion;
	private String descripcion;
	private String direccionFacturacion;
	
	public ClienteDTO() {

	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	
	public char getTipoCliente() {
		return tipo;
	}

	public void setTipoCliente(char tipoCliente) {
		this.tipo = tipoCliente;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccionFacturacion() {
		return direccionFacturacion;
	}

	public void setDireccionFacturacion(String direccionFacturacion) {
		this.direccionFacturacion = direccionFacturacion;
	}

}
