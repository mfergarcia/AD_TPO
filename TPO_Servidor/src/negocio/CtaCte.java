// @Marce: implementar aplicar Pago
// @Facu: implementar saveMe
package negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import dao.CtaCteDAO;
import dto.CtaCteDTO;

public class CtaCte {
	
	private int id;
	private float limiteCredito;
	private List<Factura> facturas;
	private List<Pago> pagos;
		
	public CtaCte() {

	}

	// Crea una cuenta corriente con el dato de limite de credito
	// del Cliente e inicializa las colecciones
	public CtaCte(float limiteCredito) {
		this.setLimiteCredito(limiteCredito);
		this.facturas = new ArrayList<Factura>();
		this.pagos = new ArrayList<Pago>();
	}
	
	public CtaCte(float limiteCredito2, Integer idCtaCte) {
		this.setLimiteCredito(limiteCredito);
		this.setId(idCtaCte);
		this.facturas = new ArrayList<Factura>();
		this.pagos = new ArrayList<Pago>();
	}

	// @Facu: revisar si hay que reemplazar b�squeda en la colecci�n por b�squeda en la BD
	// Recorre la colecci�n de facturas y devuelve aquellas con saldo pendiente
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// Agrega una factura a la cuenta corriente
	public void registrarFactura(Factura factura) {
		this.facturas.add(factura);
	}

	// Agrega un pago a la CtaCte, pero no aplica el mismo ya que dicho proceso requiere
	// la evaluaci�n de la aplicaci�n de un descuento. El pago se aplica desde AdmFacturacion
	public void registrarPago(Pago pago) {
		this.pagos.add(pago);
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
	
	// @Marce: implementar el m�todo
	// Recorre las pagos y detecta el que se encuentra en estado recibido.
	// Recorre las facturas <> "PAGADA", aplica el pago pendiente considerando el 
	// descuento recibido como par�metro (actualizando el monto adeudado de las facturas)
	// y haciendo saveMe de cada una (revisar con Facu). Al finalizar la aplicaci�n de un pago, cambia el 
	// el estado del pago a "APLICADO" y hace el saveMe del Pago y/o la cuenta corriente
	// A tener en cuenta: Los pagos se aplican de a uno por vez, luego de ser ingresado.
	public void pagarFacturas(float descuento) {
		Collection<Factura> facturas = this.buscarFacturasImpagas();
	}
	
	public float getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public List<Pago> getPagos() {
		return pagos;
	}
	
	public CtaCteDTO toDTO() {
		CtaCteDTO ctaCteDTO = new CtaCteDTO();
		ctaCteDTO.setId(this.getId());
		ctaCteDTO.setLimiteCredito(this.getLimiteCredito());
		Factura auxFact;
		for (Iterator<Factura> i = this.getFacturas().iterator(); i.hasNext(); ) {
			auxFact = i.next();
			ctaCteDTO.agregarFactura(auxFact.toDTO());
		}
		Pago auxPago;
		for (Iterator<Pago> j = this.getPagos().iterator(); j.hasNext(); ) {
			auxPago = j.next();
			ctaCteDTO.agregarPago(auxPago.toDTO());
		}
		ctaCteDTO.setSaldo(this.calcularSaldo());
		return ctaCteDTO;
	}
	
	public void saveMe() {
		CtaCteDAO.getInstance().grabar(this);
	}	

	public void updateMe() {
		CtaCteDAO.getInstance().update(this);
	}	

}