// @Marce: Revisar llamada al aplicar Pago
// @Facu: Revisar usos del saveMe y completar búsquedas en la BD
package controladores;

import negocio.Factura;
import negocio.Pago;
import negocio.Pedido;
import negocio.Remito;


public class AdmFacturacion {

	private static AdmFacturacion instancia;

	
	// Constructor privado (Patron Singleton)
	private AdmFacturacion() {

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
	
	// @Facu: revisar el uso del saveMe
	// Ingresa el pago y le solicita al controlador de Clientes registrar el mismo
	// en la Cuenta Corriente
	public String registrarPago(int idCliente, String tipoPago, float importe) {
		Pago pago = new Pago(tipoPago, importe);
		pago.saveMe();
		String condicionesEspeciales = AdmClientes.getInstancia().registrarPagoEnCtaCte(idCliente, pago);
		return condicionesEspeciales;
	}
	
	public boolean aplicarPago(int idCliente, float descuento) {
		return AdmClientes.getInstancia().aplicarPago(idCliente, descuento);
	}

}
