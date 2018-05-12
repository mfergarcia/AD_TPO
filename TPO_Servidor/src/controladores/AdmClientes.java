//PENDIENTE: Revisar implementación de la herencia de cliente, programar metodos 
// pendientes y desde VERRRR
package controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import negocio.Cliente;
import negocio.ClienteEmpresa;
import negocio.ClientePersona;
import negocio.CtaCte;
import negocio.Direccion;
import negocio.Factura;
import negocio.Pago;

public class AdmClientes {

	private static AdmClientes instancia;
	private int numeradorClientes;
	//NOTAS_FG: entiendo que no se puede tener una sola coleccion de clientes x eso use 2
	private Collection<ClienteEmpresa> clientesEmpresa;
	private Collection<ClientePersona> clientesPersona;
	
	// Constructor privado (Patron Singleton)
	private AdmClientes() {
		// TODO Auto-generated constructor stub
		// Inicializar controlador
		cargarClientes();
	}

	// Crea las colecciones clientesEmpresa y clientesPersona, invoca al DAO
	// y carga los Clientes existentes de la BD en dichas colecciones.
	// Tambien setea el numeradorClientes con el max(idCliente) + 1
	private void cargarClientes() {
		//NOTAS_FG: Solo para prueba ** REEMPLAZAR **
		this.numeradorClientes = 1;
		this.clientesEmpresa = new ArrayList<ClienteEmpresa>();
		this.clientesPersona = new ArrayList<ClientePersona>();
	}
	
	public static AdmClientes getInstancia() {
		if (instancia == null) {
			instancia = new AdmClientes();
		}
		return instancia;
	}	

	// Invoca al DAO para recuperar el tipo de cliente ('E' o 'P')
	// para un idCliente dado. Si no lo encuentra devuelve '0'
	public char obtenerTipoCliente(int idCliente) {
		//NOTAS_FG: Solo para prueba ** REEMPLAZAR **
		return 'E';
	}

	// Recorre la coleccion clientesPersona y devuelve el objeto ClientePersona
	// con el idCliente dado
	public ClienteEmpresa obtenerClienteEmpresa(int idCliente) {
		ClienteEmpresa aux;
		for (Iterator<ClienteEmpresa> i = this.clientesEmpresa.iterator(); i.hasNext() ; ) {
			aux = i.next();
			if (aux.getIdCliente() == idCliente) 
				return aux; 
		}
		return null;
	}
	
	// Recorre la coleccion clientesPersona y devuelve el objeto ClientePersona
	// con el idCliente dado
	public ClientePersona obtenerClientePersona(int idCliente) {
		return null;
	}
	
	// Crea un Cliente de tipo Empresa usando el numeradorClientes, crea su correspondiente
	// Cta Cte, agrega el nuevo cliente a la coleccion clientesEmpresa, incrementa el numerador
	// de clientes y devuelve el objeto ClienteEmpresa.
	public ClienteEmpresa altaClienteEmpresa(String cuit, String razonSocial, Direccion direccionFacturacion, char tipoFactura, String condicionesEspeciales, float limiteCredito) {
		ClienteEmpresa clienteEmpresa = null;//new ClienteEmpresa();
		clienteEmpresa.setIdCliente(this.numeradorClientes);
		this.numeradorClientes++;
		clienteEmpresa.setCuit(cuit);
		clienteEmpresa.setRazonSocial(razonSocial);
		clienteEmpresa.setDireccionFacturacion(direccionFacturacion);
		clienteEmpresa.setCondicionesEspeciales(condicionesEspeciales);
		CtaCte ctaCte = new CtaCte();
		ctaCte.setLimiteCredito(limiteCredito);
		clienteEmpresa.setCtaCte(ctaCte);
		clienteEmpresa.setTipo('E');
		clienteEmpresa.setEstado('A');
		this.clientesEmpresa.add(clienteEmpresa);
		return clienteEmpresa;
	}	

	// Crea un Cliente de tipo Persona usando el numeradorClientes, crea su correspondiente
	// Cta Cte, agrega el nuevo cliente a la coleccion clientesPersona, incrementa el numerador
	// de clientes y devuelve el objeto ClientePersona.
	public ClientePersona altaClientePersona(String dni, String apellido, String nombre, Direccion direccionFacturacion, char tipoFactura, String condicionesEspeciales, float limiteCredito) {
		this.numeradorClientes++;
		return null;
	}
	
	// Identifica el tipo de cliente para el idCliente dado y modifica el domicilio de
	// facturacion del Cliente identificado. Si no lo encuentra devuelve false
	public boolean modificarDireccionFacturacion(int idCliente, String calle, int numero, String localidad, String codigoPostal) {	
		char tipoCliente = obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa clienteEmpresa = obtenerClienteEmpresa(idCliente);
			clienteEmpresa.getDireccionFacturacion().setCalle(calle);
			clienteEmpresa.getDireccionFacturacion().setNumero(numero);
			clienteEmpresa.getDireccionFacturacion().setLocalidad(localidad);
			clienteEmpresa.getDireccionFacturacion().setCodigoPostal(codigoPostal);
			return true;
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona clientePersona = obtenerClientePersona(idCliente);
				clientePersona.getDireccionFacturacion().setCalle(calle);
				clientePersona.getDireccionFacturacion().setNumero(numero);
				clientePersona.getDireccionFacturacion().setLocalidad(localidad);
				clientePersona.getDireccionFacturacion().setCodigoPostal(codigoPostal);
				return true;
			}
		}
		return false;
	}	

	// Identifica el tipo de cliente para el idCliente dado e inactiva
	// el Cliente identificado. Si no lo encuentra devuelve false
	public boolean bajaCliente(int idCliente) {	
		char tipoCliente = obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa clienteEmpresa = obtenerClienteEmpresa(idCliente);
			clienteEmpresa.setEstado('I');
			return true;
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona clientePersona = obtenerClientePersona(idCliente);
				clientePersona.setEstado('I');
				return true;
			}
			else
				return false;
		}
	}
	
	// Registra una factura en la Cta Cte de un Cliente
	public boolean registrarFacturaEnCtaCte(int idCliente, Factura factura) {
		char tipoCliente = obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa clienteEmpresa = obtenerClienteEmpresa(idCliente);
			clienteEmpresa.getCtaCte().registrarFactura(factura);
			return true;
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona clientePersona = obtenerClientePersona(idCliente);
				clientePersona.getCtaCte().registrarFactura(factura);
				return true;
			}
			else
				return false;
		}
	}

	// Registra un pago en la Cta Cte de un Cliente
	public String registrarPagoEnCtaCte(int idCliente, Pago pago) {
		char tipoCliente = obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa clienteEmpresa = obtenerClienteEmpresa(idCliente);
			clienteEmpresa.getCtaCte().registrarPago(pago);
			return clienteEmpresa.getCondicionesEspeciales();
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona clientePersona = obtenerClientePersona(idCliente);
				clientePersona.getCtaCte().registrarPago(pago);
				return clientePersona.getCondicionesEspeciales();
			}
			else
				return null;
		}
	}

	// Dispara la aplicacion de un Pago en una Cta Cte
	// NOTAS_FG: Pendiente completar
	// Si no encuentra el Cliente devuelve false
	public boolean aplicarPago(int idCliente, float descuento) {
		char tipoCliente = obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa clienteEmpresa = obtenerClienteEmpresa(idCliente);
			clienteEmpresa.getCtaCte().pagarFacturas(descuento);
			return true;
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona clientePersona = obtenerClientePersona(idCliente);
				clientePersona.getCtaCte().pagarFacturas(descuento);
				return true;
			}
			else
				return false;
		}
	}
	
}	