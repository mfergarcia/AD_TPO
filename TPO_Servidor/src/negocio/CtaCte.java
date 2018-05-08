//Pendiente: Programar pago de facturas y revisar si pagarFacturas tambien necesita el parámetro descuento

package negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CtaCte {

	private float limiteCredito;
	private Collection<Factura> facturas;
	private Collection<Pago> pagos;
		
	public CtaCte() {
		// TODO Auto-generated constructor stub
	}

	// Crea una cuenta corriente con el dato de limite de credito
	// del Cliente e inicializa las colecciones
	public CtaCte(float limiteCredito) {
		this.setLimiteCredito(limiteCredito);
		this.facturas = new ArrayList<Factura>();
		this.pagos = new ArrayList<Pago>();
	}

	// Recorre la colección de facturas y devuelve aquellas con saldo pendiente
	private Collection<Factura> buscarFacturasImpagas() {
		Collection<Factura> facturasImpagas = new ArrayList<Factura>();
		Factura aux;
		for (Iterator<Factura> i = this.getFacturas().iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getMontoAdeudado() > 0)
				facturasImpagas.add(aux);
		}
		return facturasImpagas;
	}
	
	// Agrega una factura a la cuenta corriente
	public void registrarFactura(Factura factura) {
		this.getFacturas().add(factura);
	}

	// Agrega un pago a la cuenta corriente 
	// NOTAS_FG: OJO: esto no aplica el pago. El pago se aplica desde AdmFacturacion
	public void registrarPago(Pago pago) {
		this.getPagos().add(pago);
	}
	
	// Devuelve el saldo de la cuenta corriente
	// El control impuesto sobre monto adeudado permite calcular el saldo sin necesidad de
	// recorrer tambien los pagos
	public float calcularSaldo() {
		float saldo = 0;
		Factura aux;
		for (Iterator<Factura> i = this.getFacturas().iterator(); i.hasNext(); ) {
			aux = i.next();
			saldo = saldo + aux.getMontoAdeudado();
		}
		return saldo;
	}
	
	// Recorre las pagos y detecta el que se encuentra en estado recibido.
	// Recorre las facturas <> "PAGADA", aplica el pagos pendiente considerando el 
	// descuento recibido como parámetro, y actualiza el estado del pago a "APLICADO"
	// NOTA_FG: Los pagos se aplican de a uno por vez, luego de ser ingresado.
	public void pagarFacturas(float descuento) {
		
	}
	
	public float getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public Collection<Factura> getFacturas() {
		return facturas;
	}

	public Collection<Pago> getPagos() {
		return pagos;
	}

}