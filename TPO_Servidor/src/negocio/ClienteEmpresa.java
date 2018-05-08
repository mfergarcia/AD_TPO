//PENDIENTE: Verificar si esta bien implementada la estructura de la herencia
package negocio;

import dto.ClienteDTO;

public class ClienteEmpresa extends Cliente {

	private String cuit;
	private String razonSocial;
	
	public ClienteEmpresa() {
		// TODO Auto-generated constructor stub
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

	public ClienteDTO toDTO() {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setIdCliente(this.getIdCliente());
		clienteDTO.setIdentificacion("CUIT: " + this.getCuit());
		clienteDTO.setDescripcion(this.getRazonSocial());
		clienteDTO.setDireccionFacturacion(this.getDireccionFacturacion().toString());
		return clienteDTO;
	}	
}
