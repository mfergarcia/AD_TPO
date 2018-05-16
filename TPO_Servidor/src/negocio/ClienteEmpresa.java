//PENDIENTE: 
// @Facu: cambiar para que la herencia se guarde en una sola tabla y 
// adaptar los contructores. Descomentar los constructores de entity cuando
// esten funcionando
package negocio;

import dao.ClienteEmpresaDAO;
import dto.ClienteEmpresaDTO;
//import entities.ClienteEmpresaEntity;

public class ClienteEmpresa extends Cliente {

	private String cuit;
	private String razonSocial;
	
	//FACU: Revisar
	/*
	public ClienteEmpresa(ClienteEmpresaEntity cee) {
		super(cee);
		this.setCuit(cee.getCuit());
		this.setRazonSocial(cee.getRazonSocial());
	}
	*/

	public ClienteEmpresa(ClienteEmpresaDTO cteEmpresaDTO) {
		super(cteEmpresaDTO);
		this.setCuit(cteEmpresaDTO.getCuit());
		this.setRazonSocial(cteEmpresaDTO.getRazonSocial());
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

	public ClienteEmpresaDTO toDTO() {
		ClienteEmpresaDTO clienteEmpresaDTO = new ClienteEmpresaDTO();
		clienteEmpresaDTO.setIdCliente(this.getIdCliente());
		clienteEmpresaDTO.setTipoFactura(this.getTipoFactura());
		clienteEmpresaDTO.setCondicionesEspeciales(this.getCondicionesEspeciales());
		clienteEmpresaDTO.setLimiteCredito(this.getCtaCte().getLimiteCredito());
		clienteEmpresaDTO.setDireccionFacturacion(this.getDireccionFacturacion().toDTO());
		clienteEmpresaDTO.setCuit(this.getCuit());
		clienteEmpresaDTO.setRazonSocial(this.getRazonSocial());
		return clienteEmpresaDTO;
	}

	public void saveMe() {
		ClienteEmpresaDAO.getInstance().grabar(this);
	}	
}
