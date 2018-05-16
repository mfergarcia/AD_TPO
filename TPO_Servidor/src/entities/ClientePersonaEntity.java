package entities;

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
@Table(name="Clientes")
public class ClientePersonaEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer idCliente;
	@OneToOne()
	@JoinColumn(name="idCtaCte")
	private CtaCteEntity ctaCte;
	private char tipoFactura;
	private String condicionesEspeciales;
	@OneToOne()
	@JoinColumn(name="idDireccion")
	private Direccion direccionFacturacion;
	// tipo 'E' (Empresa), 'P' (Persona)
	private char tipo;
	private char estado;
	private String dni;
	private String apellido;
	private String nombre;
	
	public ClientePersonaEntity() {
		
	}

	//Facu: ver como dar de alta tambien la cuenta corriente
	public ClientePersonaEntity(CtaCte ctaCte, char tipoFactura, String condicionesEspeciales, Direccion direccionFacturacion, char tipo, char estado, String dni, String apellido, String nombre) {
		// this.setCtaCte(ctaCte);
		this.setTipoFactura(tipoFactura);
		this.setCondicionesEspeciales(condicionesEspeciales);
		this.setDireccionFacturacion(direccionFacturacion);
		this.setTipo(tipo);
		this.setEstado(estado);
		this.setDni(dni);
		this.setApellido(apellido);
		this.setNombre(nombre);
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public CtaCteEntity getCtaCte() {
		return ctaCte;
	}

	public void setCtaCte(CtaCteEntity ctaCte) {
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
