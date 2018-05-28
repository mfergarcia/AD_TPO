package negocio;


import dao.ClientePersonaDAO;
//import entities.ClientePersonaEntity;
import dto.ClientePersonaDTO;
import entities.ClientePersonaEntity;

public class ClientePersona extends Cliente {

	private String dni;
	private String apellido;
	private String nombre;
	
	
	public ClientePersona(ClientePersonaEntity cpe) {
		super(cpe);
		this.dni=cpe.getDni();
		this.apellido=cpe.getApellido();
		this.nombre=cpe.getNombre();
	}
	
	public ClientePersona(CtaCte ctaCte, char tipoFactura, String condEsp, Direccion d, char estado, String dni, String apellido, String nombre){
		super(ctaCte, tipoFactura, condEsp, d, estado);
		this.dni=dni;
		this.apellido=apellido;
		this.nombre=nombre;
		setTipo('P');
	}
	
	public ClientePersona(ClientePersonaDTO ctePersonaDTO) {
		super(ctePersonaDTO);
		this.setDni(ctePersonaDTO.getDni());
		this.setApellido(ctePersonaDTO.getApellido());
		this.setNombre(ctePersonaDTO.getNombre());
	}

	public void modificarCtePersona(ClientePersonaDTO ctePersonaDTO) {
		this.setCondicionesEspeciales(ctePersonaDTO.getCondicionesEspeciales());
		Direccion direccion = new Direccion();
		direccion.setCalle(ctePersonaDTO.getDireccionFacturacion().getCalle());
		direccion.setNumero(ctePersonaDTO.getDireccionFacturacion().getNumero());
		direccion.setCodigoPostal(ctePersonaDTO.getDireccionFacturacion().getCodigoPostal());
		direccion.setLocalidad(ctePersonaDTO.getDireccionFacturacion().getLocalidad());
		this.setDireccionFacturacion(direccion);
		this.getCtaCte().setLimiteCredito(ctePersonaDTO.getLimiteCredito());
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
		ClientePersonaDTO ctePersonaDTO = new ClientePersonaDTO();
		ctePersonaDTO.setIdCliente(this.getIdCliente());
		ctePersonaDTO.setTipoFactura(this.getTipoFactura());
		ctePersonaDTO.setCondicionesEspeciales(this.getCondicionesEspeciales());
		ctePersonaDTO.setLimiteCredito(this.getCtaCte().getLimiteCredito());
		ctePersonaDTO.setDireccionFacturacion(this.getDireccionFacturacion().toDTO());
		ctePersonaDTO.setDni(this.getDni());
		ctePersonaDTO.setApellido(this.getApellido());
		ctePersonaDTO.setNombre(this.getNombre());
		ctePersonaDTO.setEstado(this.getEstado());
		return ctePersonaDTO;
	}
	
	public void saveMe() {
		super.setIdCliente(ClientePersonaDAO.getInstance().grabar(this));
	}	

	
	public void updateMe() {
		ClientePersonaDAO.getInstance().update(this);
	}	

}
