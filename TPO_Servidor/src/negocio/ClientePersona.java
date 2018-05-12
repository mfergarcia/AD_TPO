//PENDIENTE: Verificar si esta bien implementada la estructura de la herencia
package negocio;

import dto.ClienteDTO;
import entity.ClientePersonaEntity;

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

	public ClienteDTO toDTO() {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setIdCliente(this.getIdCliente());
		clienteDTO.setIdentificacion("DNI: " + this.getDni());
		clienteDTO.setDescripcion(this.getApellido() + ", " + this.getNombre());
		clienteDTO.setDireccionFacturacion(this.getDireccionFacturacion().toString());
		return clienteDTO;
	}	
}
