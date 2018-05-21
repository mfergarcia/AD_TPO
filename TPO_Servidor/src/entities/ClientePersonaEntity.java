package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import negocio.CtaCte;
import negocio.Direccion;

@Entity
@DiscriminatorValue("P")
@Table(name="Clientes")
public class ClientePersonaEntity extends ClienteEntity{
	private String dni;
	private String apellido;
	private String nombre;

	//Facu: ver como dar de alta tambien la cuenta corriente
	public ClientePersonaEntity(Integer idCliente, CtaCteEntity ctaCte, char tipoFactura, String condicionesEspeciales, DireccionEntity direccionFacturacion, char tipo, char estado, String dni, String apellido, String nombre) {
		super(idCliente, ctaCte, tipoFactura, condicionesEspeciales, direccionFacturacion, tipo, estado);
		this.setTipoFactura(tipoFactura);
		this.setCondicionesEspeciales(condicionesEspeciales);
		this.setDireccionFacturacion(direccionFacturacion);
		this.setTipo(tipo);
		this.setEstado(estado);
		this.setDni(dni);
		this.setApellido(apellido);
		this.setNombre(nombre);
	}

	public ClientePersonaEntity(){}
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

}
