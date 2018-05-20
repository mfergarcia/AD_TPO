//LISTO PARA PROBAR
// LISTO PARA PROBAR
package dto;

import java.io.Serializable;

public class ClientePersonaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCliente;
	private char tipoFactura;
	private DireccionDTO direccionFacturacion;
	private String condicionesEspeciales;
	private float limiteCredito;
	private String dni;
	private String apellido;
	private String nombre;
	private char estado;
	
	public ClientePersonaDTO() {

	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public char getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(char tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public DireccionDTO getDireccionFacturacion() {
		return direccionFacturacion;
	}

	public void setDireccionFacturacion(DireccionDTO direccionFacturacion) {
		this.direccionFacturacion = direccionFacturacion;
	}

	public String getCondicionesEspeciales() {
		return condicionesEspeciales;
	}

	public void setCondicionesEspeciales(String condicionesEspeciales) {
		this.condicionesEspeciales = condicionesEspeciales;
	}

	public float getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

}
