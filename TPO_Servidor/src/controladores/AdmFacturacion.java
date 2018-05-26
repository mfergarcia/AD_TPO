// @Marce: Revisar llamada al aplicar Pago
// @Facu: Revisar usos del saveMe y updateMe en facturar
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
	
	// @Facu: revisar el uso de los saveMe y updateMe para Factura y Remito
	// Genera la Factura y el correspondiente Remito para un determinado Pedido
	// Le pide al AdmClientes que registre la misma en la CtaCte del Cliente
	public Factura facturar(Pedido pedido) {
		char tipoFactura = pedido.getCliente().getTipoFactura();
		Factura factura = new Factura(tipoFactura, pedido);
		factura.saveMe();
		Remito remito = new Remito(factura);
		remito.saveMe();
		factura.setRemito(remito);
		factura.updateMe();
		if (AdmClientes.getInstancia().registrarFacturaEnCtaCte(pedido.getCliente().getIdCliente(), factura))
			return factura;
		else
			return null;
	}
	
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
