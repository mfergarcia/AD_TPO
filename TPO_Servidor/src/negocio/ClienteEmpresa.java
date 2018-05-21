// @Facu: cambiar para que la herencia se guarde en una sola tabla y 
// adaptar los contructores. Descomentar los constructores de entity cuando
// esten funcionando
package negocio;

import dao.ClienteEmpresaDAO;
import dto.ClienteEmpresaDTO;
import entities.ClienteEmpresaEntity;
//import entities.ClienteEmpresaEntity;

public class ClienteEmpresa extends Cliente {

	private String cuit;
	private String razonSocial;
	
	//FACU: Revisar
	public ClienteEmpresa(ClienteEmpresaEntity cee) {
		super(cee);
		this.setCuit(cee.getCuit());
		this.setRazonSocial(cee.getRazonSocial());
	}
	
	public ClienteEmpresa( CtaCte cC, char tipoFactura, String cndesp, Direccion d, char estado, String cuit, String rs){
		super(cC, tipoFactura, cndesp, d, estado);
		this.cuit=cuit;
		this.razonSocial=rs;
		setTipo('E');
	}

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
		ClienteEmpresaDTO cteEmpresaDTO = new ClienteEmpresaDTO();
		cteEmpresaDTO.setIdCliente(this.getIdCliente());
		cteEmpresaDTO.setTipoFactura(this.getTipoFactura());
		cteEmpresaDTO.setCondicionesEspeciales(this.getCondicionesEspeciales());
		cteEmpresaDTO.setLimiteCredito(this.getCtaCte().getLimiteCredito());
		cteEmpresaDTO.setDireccionFacturacion(this.getDireccionFacturacion().toDTO());
		cteEmpresaDTO.setCuit(this.getCuit());
		cteEmpresaDTO.setRazonSocial(this.getRazonSocial());
		cteEmpresaDTO.setEstado(this.getEstado());
		return cteEmpresaDTO;
	}

	public void saveMe() {
		super.setIdCliente(ClienteEmpresaDAO.getInstance().grabar(this));
	}	
	
	//@Facu: implementar metodo
	public void updateMe() {

	}	

}
