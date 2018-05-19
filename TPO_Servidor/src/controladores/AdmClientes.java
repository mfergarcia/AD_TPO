// @Marce: Revisar llamada al aplicar Pago
// @Facu: Revisar usos del saveMe y completar búsquedas en la BD
package controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import dao.ClienteDAO;
import dao.ClienteEmpresaDAO;
import dao.ClientePersonaDAO;
import dto.ClienteEmpresaDTO;
import dto.ClientePersonaDTO;
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
	
	private AdmClientes() {
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
		char tipo= 'N';
		tipo= ClienteDAO.getInstancia().getTipoCliente(idCliente);
		return tipo;
	}

	// @Facu: Reemplazar la búsqueda en la colección por búsqueda en la BD
	public ClienteEmpresa obtenerClienteEmpresa(int idCliente) {
		ClienteEmpresa aux;
		aux=ClienteEmpresaDAO.getInstance().findByID(idCliente);
		/*
		for (Iterator<ClienteEmpresa> i = this.clientesEmpresa.iterator(); i.hasNext() ; ) {
			aux = i.next();
			if (aux.getIdCliente() == idCliente) 
				return aux; 
		}
		*/
		return aux;
	}
	
	// @Facu: Reemplazar la búsqueda en la colección por búsqueda en la BD
	public ClientePersona obtenerClientePersona(int idCliente) {
		ClientePersona aux;
		aux= ClientePersonaDAO.getInstance().findByID(idCliente);
		/*
		for (Iterator<ClientePersona> i = this.clientesPersona.iterator(); i.hasNext() ; ) {
			aux = i.next();
			if (aux.getIdCliente() == idCliente) 
				return aux; 
		}
		*/
		return null;
	}

	//@Facu: revisar la llamada al saveMe de la cuenta corriente y del cteEmpresa
	public ClienteEmpresa modificarCteEmpresa(ClienteEmpresaDTO cteEmpresaDTO) {
		ClienteEmpresa cteEmpresa = this.obtenerClienteEmpresa(cteEmpresaDTO.getIdCliente());
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
		ClientePersona ctePersona = this.obtenerClientePersona(ctePersonaDTO.getIdCliente());
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
		char tipoCliente = this.obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa cteEmpresa = this.obtenerClienteEmpresa(idCliente);
			cteEmpresa.setEstado('I');
			cteEmpresa.saveMe();
			return true;
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona ctePersona = this.obtenerClientePersona(idCliente);
				ctePersona.setEstado('I');
				ctePersona.saveMe();
				return true;
			}
			else
				return false;
		}
	}
	
	public char obtenerTipoFacturaCliente(int idCliente) {
		char tipoCliente = this.obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa clienteEmpresa = this.obtenerClienteEmpresa(idCliente);
			return clienteEmpresa.getTipoFactura();
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona clientePersona = this.obtenerClientePersona(idCliente);
				return clientePersona.getTipoFactura();
			}
			else
				return 0;
		}
	}
	
	// @Facu: revisar uso del saveMe
	// Registra una factura en la Cta Cte de un Cliente
	public boolean registrarFacturaEnCtaCte(int idCliente, Factura factura) {
		char tipoCliente = this.obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa cteEmpresa = this.obtenerClienteEmpresa(idCliente);
			cteEmpresa.getCtaCte().registrarFactura(factura);
			cteEmpresa.getCtaCte().saveMe();
			return true;
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona ctePersona = this.obtenerClientePersona(idCliente);
				ctePersona.getCtaCte().registrarFactura(factura);
				ctePersona.getCtaCte().saveMe();
				return true;
			}
			else
				return false;
		}
	}

	// @Facu: revisar el uso del saveMe
	// Registra un pago en la Cta Cte de un Cliente
	public String registrarPagoEnCtaCte(int idCliente, Pago pago) {
		char tipoCliente = this.obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa clienteEmpresa = this.obtenerClienteEmpresa(idCliente);
			clienteEmpresa.getCtaCte().registrarPago(pago);
			clienteEmpresa.getCtaCte().saveMe();
			return clienteEmpresa.getCondicionesEspeciales();
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona clientePersona = this.obtenerClientePersona(idCliente);
				clientePersona.getCtaCte().registrarPago(pago);
				clientePersona.getCtaCte().saveMe();
				return clientePersona.getCondicionesEspeciales();
			}
			else
				return null;
		}
	}

	// @Marce: verificar si está ok
	// Dispara la aplicacion de un Pago en una Cta Cte
	// Si no encuentra el Cliente devuelve false
	public boolean aplicarPago(int idCliente, float descuento) {
		char tipoCliente = this.obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa clienteEmpresa = this.obtenerClienteEmpresa(idCliente);
			clienteEmpresa.getCtaCte().pagarFacturas(descuento);
			return true;
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona clientePersona = this.obtenerClientePersona(idCliente);
				clientePersona.getCtaCte().pagarFacturas(descuento);
				return true;
			}
			else
				return false;
		}
	}

}	