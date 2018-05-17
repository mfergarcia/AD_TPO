// PENDIENTE: Revisar implementación de la herencia de cliente, programar metodos 
// pendientes y desde VERRRR
package controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import dao.ClienteEmpresaDAO;
import dto.ClienteDTO;
import dto.ClienteEmpresaDTO;
import dto.ClientePersonaDTO;
import negocio.Cliente;
import negocio.ClienteEmpresa;
import negocio.ClientePersona;
import negocio.CtaCte;
import negocio.Direccion;
import negocio.Factura;
import negocio.Pago;

public class AdmClientes {

	private static AdmClientes instancia;

	// @Facu: remover estas colecciones cuando se puedan reemplazar las búsquedas
	// en colecciones por búsquedas en la BD
	private Collection<ClienteEmpresa> clientesEmpresa;
	private Collection<ClientePersona> clientesPersona;
	
	// Constructor privado (Patron Singleton)
	private AdmClientes() {
		//@Facu: remover estas llamada cuando se puedan reemplazar las búsquedas
		//en colecciones por búsquedas en la BD
		this.clientesEmpresa = new ArrayList<ClienteEmpresa>();
		this.clientesPersona = new ArrayList<ClientePersona>();
	}

	public static AdmClientes getInstancia() {
		if (instancia == null) {
			instancia = new AdmClientes();
		}
		return instancia;
	}	

	// @Facu: revisar que funcione ok la implementación del DAO
	// Crea un Cliente de tipo Empresa y crea su correspondiente Cta Cte
	public ClienteEmpresa altaClienteEmpresa(ClienteEmpresaDTO cteEmpresaDTO) {
		ClienteEmpresa cteEmpresa = new ClienteEmpresa(cteEmpresaDTO);
		CtaCte ctaCte = new CtaCte(cteEmpresaDTO.getLimiteCredito());
		cteEmpresa.setCtaCte(ctaCte);
		cteEmpresa.saveMe();
		// @Facu: Remover la actualizacion de esta coleccion cuando funcionen las búsquedas en la BD
		this.clientesEmpresa.add(cteEmpresa);
		return cteEmpresa;
	}
	
	// @Facu: revisar que funcione ok la implementación del DAO
	// Crea un Cliente de tipo Persona y crea su correspondiente Cta Cte
	public ClientePersona altaClientePersona(ClientePersonaDTO ctePersonaDTO) {
		ClientePersona ctePersona = new ClientePersona(ctePersonaDTO);
		CtaCte ctaCte = new CtaCte(ctePersonaDTO.getLimiteCredito());
		ctePersona.setCtaCte(ctaCte);
		ctePersona.saveMe();
		// @Facu: Remover la actualizacion de esta coleccion cuando funcionen las búsquedas en la BD
		this.clientesPersona.add(ctePersona);
		return ctePersona;
	}
	
	// @Facu: Invoca al DAO para recuperar el tipo de cliente ('E' o 'P')
	// de la BD para un idCliente dado. Si no lo encuentra devuelve '0'
	public char obtenerTipoCliente(int idCliente) {
		//NOTAS_FG: Solo para prueba ** REEMPLAZAR **
		return 'E';
	}

	// @Facu: Reemplazar la búsqueda en la colección por búsqueda en la BD
	public ClienteEmpresa obtenerClienteEmpresa(int idCliente) {
		ClienteEmpresa aux;
		for (Iterator<ClienteEmpresa> i = this.clientesEmpresa.iterator(); i.hasNext() ; ) {
			aux = i.next();
			if (aux.getIdCliente() == idCliente) 
				return aux; 
		}
		return null;
	}
	
	// @Facu: Reemplazar la búsqueda en la colección por búsqueda en la BD
	public ClientePersona obtenerClientePersona(int idCliente) {
		ClientePersona aux;
		for (Iterator<ClientePersona> i = this.clientesPersona.iterator(); i.hasNext() ; ) {
			aux = i.next();
			if (aux.getIdCliente() == idCliente) 
				return aux; 
		}
		return null;
	}

	//@Facu: revisar la llamada al saveMe de la cuenta corriente y del cteEmpresa
	public ClienteEmpresa modificarCteEmpresa(ClienteEmpresaDTO cteEmpresaDTO) {
		ClienteEmpresa cteEmpresa = obtenerClienteEmpresa(cteEmpresaDTO.getIdCliente());
		cteEmpresa.setCondicionesEspeciales(cteEmpresaDTO.getCondicionesEspeciales());
		Direccion direccion = new Direccion();
		direccion.setCalle(cteEmpresaDTO.getDireccionFacturacion().getCalle());
		direccion.setNumero(cteEmpresaDTO.getDireccionFacturacion().getNumero());
		direccion.setCodigoPostal(cteEmpresaDTO.getDireccionFacturacion().getCodigoPostal());
		direccion.setLocalidad(cteEmpresaDTO.getDireccionFacturacion().getLocalidad());
		cteEmpresa.setDireccionFacturacion(direccion);
		cteEmpresa.getCtaCte().setLimiteCredito(cteEmpresaDTO.getLimiteCredito());
		cteEmpresa.getCtaCte().saveMe();
		cteEmpresa.saveMe();
		return cteEmpresa;
	}

	//@Facu: revisar la llamada al saveMe de la cuenta corriente y del cteEmpresa
	public ClientePersona modificarCtePersona(ClientePersonaDTO ctePersonaDTO) {
		ClientePersona ctePersona = obtenerClientePersona(ctePersonaDTO.getIdCliente());
		ctePersona.setCondicionesEspeciales(ctePersonaDTO.getCondicionesEspeciales());
		Direccion direccion = new Direccion();
		direccion.setCalle(ctePersonaDTO.getDireccionFacturacion().getCalle());
		direccion.setNumero(ctePersonaDTO.getDireccionFacturacion().getNumero());
		direccion.setCodigoPostal(ctePersonaDTO.getDireccionFacturacion().getCodigoPostal());
		direccion.setLocalidad(ctePersonaDTO.getDireccionFacturacion().getLocalidad());
		ctePersona.setDireccionFacturacion(direccion);
		ctePersona.getCtaCte().setLimiteCredito(ctePersonaDTO.getLimiteCredito());
		ctePersona.getCtaCte().saveMe();
		ctePersona.saveMe();
		return ctePersona;
	}

	// @Facu: revisar la llamada al saveMe 
	// Identifica el tipo de cliente para el idCliente dado e inactiva
	// el Cliente identificado. Si no lo encuentra devuelve false
	public boolean bajaCliente(int idCliente) {	
		char tipoCliente = obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa cteEmpresa = obtenerClienteEmpresa(idCliente);
			cteEmpresa.setEstado('I');
			cteEmpresa.saveMe();
			return true;
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona ctePersona = obtenerClientePersona(idCliente);
				ctePersona.setEstado('I');
				ctePersona.saveMe();
				return true;
			}
			else
				return false;
		}
	}
	
	public char obtenerTipoFacturaCliente(int idCliente) {
		char tipoCliente = obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa clienteEmpresa = obtenerClienteEmpresa(idCliente);
			return clienteEmpresa.getTipoFactura();
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona clientePersona = obtenerClientePersona(idCliente);
				return clientePersona.getTipoFactura();
			}
			else
				return 0;
		}
	}
	
	// @Facu: revisar uso del saveMe
	// Registra una factura en la Cta Cte de un Cliente
	public boolean registrarFacturaEnCtaCte(int idCliente, Factura factura) {
		char tipoCliente = obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa cteEmpresa = obtenerClienteEmpresa(idCliente);
			cteEmpresa.getCtaCte().registrarFactura(factura);
			cteEmpresa.getCtaCte().saveMe();
			return true;
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona ctePersona = obtenerClientePersona(idCliente);
				ctePersona.getCtaCte().registrarFactura(factura);
				ctePersona.getCtaCte().saveMe();
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