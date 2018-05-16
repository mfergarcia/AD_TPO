//PENDIENTE: 
// @Facu: cambiar para que la herencia se guarde en una sola tabla y 
// adaptar los contructores. Descomentar los constructores de entity cuando
// esten funcionando
package negocio;


import dao.ClientePersonaDAO;
//import entities.ClientePersonaEntity;
import dto.ClientePersonaDTO;

public class ClientePersona extends Cliente {

	private String dni;
	private String apellido;
	private String nombre;
	
	//
	/*
	public ClientePersona(ClientePersonaEntity cpe) {
		super(cpe);
		this.dni=cpe.getDni();
		this.apellido=cpe.getApellido();
		this.nombre=cpe.getNombre();
	}
	*/
	
	public ClientePersona(ClientePersonaDTO ctePersonaDTO) {
		super(ctePersonaDTO);
		this.setDni(ctePersonaDTO.getDni());
		this.setApellido(ctePersonaDTO.getApellido());
		this.setNombre(ctePersonaDTO.getNombre());
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

	public ClientePersonaDTO toDTO() {
		ClientePersonaDTO clientePersonaDTO = new ClientePersonaDTO();
		clientePersonaDTO.setIdCliente(this.getIdCliente());
		clientePersonaDTO.setTipoFactura(this.getTipoFactura());
		clientePersonaDTO.setCondicionesEspeciales(this.getCondicionesEspeciales());
		clientePersonaDTO.setLimiteCredito(this.getCtaCte().getLimiteCredito());
		clientePersonaDTO.setDireccionFacturacion(this.getDireccionFacturacion().toDTO());
		clientePersonaDTO.setDni(this.getDni());
		clientePersonaDTO.setApellido(this.getApellido());
		clientePersonaDTO.setNombre(this.getNombre());
		return clientePersonaDTO;
	}
	
	public void saveMe() {
		ClientePersonaDAO.getInstance().grabar(this);
	}	

}
