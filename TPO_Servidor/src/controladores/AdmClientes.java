// @Marce: Revisar llamada al aplicar Pago
package controladores;

import java.rmi.RemoteException;

import dao.ClienteDAO;
import dao.ClienteEmpresaDAO;
import dao.ClientePersonaDAO;
import dto.ClienteEmpresaDTO;
import dto.ClientePersonaDTO;
import excepciones.ExcepcionSistema;
import negocio.ClienteEmpresa;
import negocio.ClientePersona;
import negocio.CtaCte;
import negocio.Factura;
import negocio.Pago;

public class AdmClientes {

	private static AdmClientes instancia;

	private AdmClientes() {

	}

	public static AdmClientes getInstancia() {
		if (instancia == null) {
			instancia = new AdmClientes();
		}
		return instancia;
	}	

	// Crea un Cliente de tipo Empresa y crea su correspondiente Cta Cte
	public ClienteEmpresa altaClienteEmpresa(ClienteEmpresaDTO cteEmpresaDTO) {
		ClienteEmpresa cteEmpresa = new ClienteEmpresa(cteEmpresaDTO);
		CtaCte ctaCte = new CtaCte(cteEmpresaDTO.getLimiteCredito());
		cteEmpresa.setCtaCte(ctaCte);
		cteEmpresa.saveMe();
		return cteEmpresa;
	}
	
	// Crea un Cliente de tipo Persona y crea su correspondiente Cta Cte
	public ClientePersona altaClientePersona(ClientePersonaDTO ctePersonaDTO) {
		ClientePersona ctePersona = new ClientePersona(ctePersonaDTO);
		CtaCte ctaCte = new CtaCte(ctePersonaDTO.getLimiteCredito());
		ctePersona.setCtaCte(ctaCte);
		ctePersona.saveMe();
		return ctePersona;
	}
	
	// @Facu: Invoca al DAO para recuperar el tipo de cliente ('E' o 'P')
	// de la BD para un idCliente dado. Si no lo encuentra devuelve '0'
	public char obtenerTipoCliente(int idCliente) {
		char tipo= 'N';
		tipo= ClienteDAO.getInstancia().getTipoCliente(idCliente);
		return tipo;
	}

	// Obtiene un Cliente Empresa
	public ClienteEmpresa obtenerClienteEmpresa(int idCliente) {
		ClienteEmpresa aux;
		aux=ClienteEmpresaDAO.getInstance().findByID(idCliente);
		return aux;
	}
	
	// Obtiene un Cliente Persona
	public ClientePersona obtenerClientePersona(int idCliente) {
		ClientePersona aux;
		aux= ClientePersonaDAO.getInstance().findByID(idCliente);
		return aux;
	}

	// @Facu: ver si hay que hacer saveMe de la Cta Cte o se graba con el saveMe del CteEmpresa
	// Actualiza las propiedades editables de un Cliente Empresa
	public ClienteEmpresa modificarCteEmpresa(ClienteEmpresaDTO cteEmpresaDTO) {
		ClienteEmpresa cteEmpresa = this.obtenerClienteEmpresa(cteEmpresaDTO.getIdCliente());
		cteEmpresa.modificarCteEmpresa(cteEmpresaDTO);
		// cteEmpresa.getCtaCte().updateMe();
		cteEmpresa.updateMe();
		return cteEmpresa;
	}

	// @Facu: ver si hay que hacer saveMe de la Cta Cte o se graba con el saveMe del CtePersona
	// Actualiza las propiedades editables de un Cliente Persona
	public ClientePersona modificarCtePersona(ClientePersonaDTO ctePersonaDTO) {
		ClientePersona ctePersona = this.obtenerClientePersona(ctePersonaDTO.getIdCliente());
		ctePersona.modificarCtePersona(ctePersonaDTO);
		//ctePersona.getCtaCte().updateMe();
		ctePersona.updateMe();
		return ctePersona;
	}

	// Identifica el tipo de cliente para el idCliente dado e inactiva
	// el Cliente identificado. Si no lo encuentra devuelve false
	public boolean bajaCliente(int idCliente) {	
		char tipoCliente = this.obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa cteEmpresa = this.obtenerClienteEmpresa(idCliente);
			cteEmpresa.setEstado('I');
			cteEmpresa.updateMe();
			return true;
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona ctePersona = this.obtenerClientePersona(idCliente);
				ctePersona.setEstado('I');
				ctePersona.updateMe();
				return true;
			}
			else
				return false;
		}
	}
	
	// Devuelve el objeto CtaCte para un cliente dado
	public CtaCte obtenerCtaCte(int idCliente) throws RemoteException, ExcepcionSistema {
		CtaCte ctaCte;
		char tipoCliente = this.obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa cteEmpresa = this.obtenerClienteEmpresa(idCliente);
			ctaCte = cteEmpresa.getCtaCte();
			return ctaCte;
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona ctePersona = this.obtenerClientePersona(idCliente);
				ctaCte = ctePersona.getCtaCte();
				return ctaCte;
			}
			else
				return null;
		}
	}
	
	// Registra una factura en la Cta Cte de un Cliente
	public boolean registrarFacturaEnCtaCte(int idCliente, Factura factura) {
		char tipoCliente = this.obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa cteEmpresa = this.obtenerClienteEmpresa(idCliente);
			cteEmpresa.getCtaCte().registrarFactura(factura);
			cteEmpresa.getCtaCte().updateMe();
			return true;
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona ctePersona = this.obtenerClientePersona(idCliente);
				ctePersona.getCtaCte().registrarFactura(factura);
				ctePersona.getCtaCte().updateMe();
				return true;
			}
			else
				return false;
		}
	}

	// Registra un pago en la Cta Cte de un Cliente
	public String registrarPagoEnCtaCte(int idCliente, Pago pago) {
		char tipoCliente = this.obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa clienteEmpresa = this.obtenerClienteEmpresa(idCliente);
			clienteEmpresa.getCtaCte().registrarPago(pago);
			clienteEmpresa.getCtaCte().updateMe();
			return clienteEmpresa.getCondicionesEspeciales();
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona clientePersona = this.obtenerClientePersona(idCliente);
				clientePersona.getCtaCte().registrarPago(pago);
				clientePersona.getCtaCte().updateMe();
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