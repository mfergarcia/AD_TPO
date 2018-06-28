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
	private Collection<Factura> facturas;
	private Collection<Pago> pagos;
		
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

	// @Facu: revisar si hay que reemplazar búsqueda en la colección por búsqueda en la BD
	// Recorre la colección de facturas y devuelve aquellas con saldo pendiente
	private Collection<Factura> buscarFacturasImpagas() {
		this.facturas=CtaCteDAO.getInstance().obtenerFacturas(this.id);
		Collection<Factura> facturasImpagas = new ArrayList<Factura>();
		Factura aux;
		for (Iterator<Factura> i = this.getFacturas().iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getMontoAdeudado() > 0)
				facturasImpagas.add(aux);
		}
		return facturasImpagas;
	}
	
	public Collection<Pago> obtenerPagosRECIBIDOS(){
		Collection<Pago> p= CtaCteDAO.getInstance().obtenerPagos(this.id);
		Collection<Pago> res= new ArrayList<Pago>();
		for(Pago pago: p)
		{
			if(pago.getEstado().equals("RECIBIDO"))
				res.add(pago);
		}
		return res;
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
	// la evaluación de la aplicación de un descuento. El pago se aplica desde AdmFacturacion
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
	

	public void pagarFacturas(float descuento) {
			Collection<Factura> facturas = this.buscarFacturasImpagas();
			Factura auxFact;
			Iterator<Factura> x = facturas.iterator();
			auxFact = x.next();
			float montoAux = auxFact.getMontoAdeudado();
			Collection<Pago> lp= obtenerPagosRECIBIDOS();
			for(Pago pago: lp) {
				float pagoAux =pago.getImporte() + descuento;
				while (pagoAux>0 && x.hasNext()){		
					if(pagoAux < montoAux){
						auxFact.modificarMonto (montoAux-pagoAux);
						if(montoAux-pagoAux>0){
							auxFact.setEstadoFactura("Pago Parcial");
							}
							else{
								auxFact.setEstadoFactura("Pagada");
							}
					}else
						{
						auxFact.modificarMonto(0);
						auxFact.setEstadoFactura("Pagada");
						pagoAux = pagoAux-montoAux;
						
						}
				}
				pago.setEstado("APLICADO");	
				pago.updateMe();
			}
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