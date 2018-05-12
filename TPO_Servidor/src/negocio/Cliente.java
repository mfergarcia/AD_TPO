//PENDIENTE: Verificar si esta bien implementada la estructura de la herencia
package negocio;

import entity.ClienteEmpresaEntity;
import entity.ClientePersonaEntity;

public class Cliente {
	
	private int idCliente;
	private CtaCte ctaCte;
	private char tipoFactura;//?
	private Direccion direccionFacturacion;
	private String condicionesEspeciales;
	// tipo 'E' (Empresa), 'P' (Persona)
	private char tipo;
	// estado: 'A' (activo), 'I' (inactivo)
	private char estado;
	
	public Cliente(ClienteEmpresaEntity cee) {
		this.idCliente=cee.getIdCliente();
		this.ctaCte=new CtaCte(cee.getCc());
		this.condicionesEspeciales=cee.getCondicionesEspeciales();//TODO
	}
	
	public Cliente(ClientePersonaEntity cpe) {
		this.idCliente=cpe.getIdCliente();
		this.ctaCte=new CtaCte(cpe.getCc());
		this.condicionesEspeciales=cpe.getCondicionesEspeciales();//TODO
	}

	// Valida que el objeto sea un determinado idCliente
	public boolean sosCliente(int idCliente) {
		return (this.getIdCliente() == idCliente);
	}
	
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public CtaCte getCtaCte() {
		return ctaCte;
	}

	public void setCtaCte(CtaCte ctaCte) {
		this.ctaCte = ctaCte;
	}

	public char getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(char tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public Direccion getDireccionFacturacion() {
		return direccionFacturacion;
	}

	public void setDireccionFacturacion(Direccion direccionFacturacion) {
		this.direccionFacturacion = direccionFacturacion;
	}

	public String getCondicionesEspeciales() {
		return condicionesEspeciales;
	}

	public void setCondicionesEspeciales(String condicionesEspeciales) {
		this.condicionesEspeciales = condicionesEspeciales;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}
}