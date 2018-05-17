//PENDIENTE: Completar programacion y desde VERRRR
package controladores;

import java.util.Collection;

import negocio.ClienteEmpresa;
import negocio.ClientePersona;
import negocio.Factura;
import negocio.Pago;
import negocio.Pedido;
import negocio.Remito;


public class AdmFacturacion {

	private static AdmFacturacion instancia;
	private int numeradorFactA;
	private int numeradorFactB;
	private int numeradorFactC;
	private int numeradorRemito;
	
	// Constructor privado (Patron Singleton)
	private AdmFacturacion() {
		// TODO Auto-generated constructor stub
	}
	
	public static AdmFacturacion getInstancia() {
		if (instancia == null) {
			instancia = new AdmFacturacion();
		}
		return instancia;
	}
	
	// @Facu: revisar el uso de los saveMe para Factura y Remito
	// Genera la factura y el correspondiente Remito para un determinado Pedido
	// Le pide al AdmClientes que registre la misma en la CtaCte del Cliente
	public Factura facturar(Pedido pedido) {
		char tipoFactura = AdmClientes.getInstancia().obtenerTipoFacturaCliente(pedido.getIdCliente());
		Factura factura = new Factura(tipoFactura, pedido);
		Remito remito = new Remito(factura);
		factura.setRemito(remito);
		factura.saveMe();
		remito.saveMe();
		if (AdmClientes.getInstancia().registrarFacturaEnCtaCte(pedido.getIdCliente(), factura))
			return factura;
		else
			return null;
	}
	
	// Ingresa el pago y lo registra en la Cta Cte del Cliente
	public String ingresarPago(int idCliente, String tipoPago, float importe) {
		Pago pago = new Pago(tipoPago, importe);
		String condicionesEspeciales = AdmClientes.getInstancia().registrarPagoEnCtaCte(idCliente, pago);
		return condicionesEspeciales;
	}
	
	public boolean aplicarPago(int idCliente, float descuento) {
		return AdmClientes.getInstancia().aplicarPago(idCliente, descuento);
	}

}
