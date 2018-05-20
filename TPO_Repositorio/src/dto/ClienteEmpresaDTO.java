//LISTO PARA PROBAR
package dto;

import java.io.Serializable;

public class ClienteEmpresaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCliente;
	private char tipoFactura;
	private DireccionDTO direccionFacturacion;
	private String condicionesEspeciales;
	private float limiteCredito;
	private String cuit;
	private String razonSocial;
	private char estado;
	
	public ClienteEmpresaDTO() {

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

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}
	
}
