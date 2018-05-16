//PENDIENTE: Completar programacion y desde VERRRR
package controladores;

import java.util.Collection;

import negocio.ClienteEmpresa;
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
		// Inicializar controlador
		cargarFacturas();
	}

	// Invoca al DAO, carga las Facturas existentes
	// de la BD en la colección facturas y setea los 
	// numeradores de factura con el max(numFactura) + 1
	// para cada tipo de Factura, el numerador de remitos
	// con el max(numRemito) + 1 y el numerador de pagos
	// con el max(numPago) + 1
	private void cargarFacturas() {
		
	}
	
	public static AdmFacturacion getInstancia() {
		if (instancia == null) {
			instancia = new AdmFacturacion();
		}
		return instancia;
	}
	
	// Genera la factura y el correspondiente Remito para un determinado Pedido
	// Incrementa los numeradores de Factura y Remito
	// Le pide al AdmClientes que registre la misma en la CtaCte del Cliente
	public Factura facturar(Pedido pedido) {
		char tipoFactura = AdmClientes.getInstancia().obtenerTipoFacturaCliente(pedido.getIdCliente());
		int numFactura;
		if (tipoFactura == 'A') {
			numFactura = numeradorFactA;
			numeradorFactA++;
		}
		else {
			if (tipoFactura == 'B') {
				numFactura = numeradorFactB;
				numeradorFactB++;
			}
			else {
				numFactura = numeradorFactC;
				numeradorFactC++;
			}
				
		}
		Factura factura = new Factura(tipoFactura, numFactura, pedido);
		Remito remito = new Remito(numeradorRemito, factura);
		numeradorRemito++;
		factura.setRemito(remito);
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
