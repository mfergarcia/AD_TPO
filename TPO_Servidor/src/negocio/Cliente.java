//PENDIENTE: 
// @Facu: cambiar para que la herencia se guarde en una sola tabla y 
// adaptar los contructores. Descomentar los constructores de entity cuando
// esten funcionando
package negocio;

import dto.ClienteEmpresaDTO;
import dto.ClientePersonaDTO;
import entities.ClienteEmpresaEntity;
import entities.ClienteEntity;


public class Cliente {
	
	private int idCliente;
	private CtaCte ctaCte;
	private char tipoFactura;
	private String condicionesEspeciales;
	private Direccion direccionFacturacion;
	// tipo 'E' (Empresa), 'P' (Persona)
	private char tipo;
	// estado: 'A' (activo), 'I' (inactivo)
	private char estado;
	
	//FACU: Revisar 
	
	public Cliente(ClienteEntity ce) {
		this.setIdCliente(ce.getIdCliente());
		Direccion d= new Direccion();
		d.setId(ce.getDireccionFacturacion().getId());
		d.setCalle(ce.getDireccionFacturacion().getCalle());
		d.setNumero(ce.getDireccionFacturacion().getNumero());
		d.setCodigoPostal(ce.getDireccionFacturacion().getCodigoPostal());
		d.setLocalidad(ce.getDireccionFacturacion().getLocalidad());
		this.setDireccionFacturacion(d);
		this.setCtaCte(new CtaCte(ce.getCtaCte().getLimiteCredito()));
		this.setTipoFactura(ce.getTipoFactura());
		this.condicionesEspeciales=ce.getCondicionesEspeciales();
		this.setTipo(ce.getTipo());
	}
	
	
	public Cliente(CtaCte ctaCte, char tipoFactura, String condicionesEspeciales,
			Direccion direccionFacturacion, char estado) {
		super();
		this.ctaCte = ctaCte;
		this.tipoFactura = tipoFactura;
		this.condicionesEspeciales = condicionesEspeciales;
		this.direccionFacturacion = direccionFacturacion;
		this.estado = estado;
	}
	
	public Cliente(ClienteEmpresaDTO cteEmpresaDTO) {
		this.setTipoFactura(cteEmpresaDTO.getTipoFactura());
		this.setCondicionesEspeciales(cteEmpresaDTO.getCondicionesEspeciales());
		Direccion direccionFacturacion = new Direccion();
		direccionFacturacion.setCalle(cteEmpresaDTO.getDireccionFacturacion().getCalle());
		direccionFacturacion.setNumero(cteEmpresaDTO.getDireccionFacturacion().getNumero());
		direccionFacturacion.setCodigoPostal(cteEmpresaDTO.getDireccionFacturacion().getCodigoPostal());
		direccionFacturacion.setLocalidad(cteEmpresaDTO.getDireccionFacturacion().getLocalidad());
		this.setDireccionFacturacion(direccionFacturacion);
		this.setTipo('E');
		this.setEstado('A');
	}

	public Cliente(ClientePersonaDTO ctePersonaDTO) {
		this.setTipoFactura(ctePersonaDTO.getTipoFactura());
		this.setCondicionesEspeciales(ctePersonaDTO.getCondicionesEspeciales());
		Direccion direccionFacturacion = new Direccion();
		direccionFacturacion.setCalle(ctePersonaDTO.getDireccionFacturacion().getCalle());
		direccionFacturacion.setNumero(ctePersonaDTO.getDireccionFacturacion().getNumero());
		direccionFacturacion.setCodigoPostal(ctePersonaDTO.getDireccionFacturacion().getCodigoPostal());
		direccionFacturacion.setLocalidad(ctePersonaDTO.getDireccionFacturacion().getLocalidad());
		this.setDireccionFacturacion(direccionFacturacion);
		this.setTipo('P');
		this.setEstado('A');
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

	public String getCondicionesEspeciales() {
		return condicionesEspeciales;
	}

	public void setCondicionesEspeciales(String condicionesEspeciales) {
		this.condicionesEspeciales = condicionesEspeciales;
	}

	public Direccion getDireccionFacturacion() {
		return direccionFacturacion;
	}

	public void setDireccionFacturacion(Direccion direccionFacturacion) {
		this.direccionFacturacion = direccionFacturacion;
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