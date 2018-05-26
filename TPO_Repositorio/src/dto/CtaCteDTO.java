package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CtaCteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private float limiteCredito;
	private List<FacturaDTO> facturas;
	private List<PagoDTO> pagos;
	private float saldo;
	
	public CtaCteDTO() {
		this.facturas = new ArrayList<FacturaDTO>();
		this.pagos = new ArrayList<PagoDTO>();
	}

	public void agregarFactura(FacturaDTO factura) {
		this.facturas.add(factura);
	}

	public void agregarPago(PagoDTO pago) {
		this.pagos.add(pago);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public List<FacturaDTO> getFacturas() {
		return facturas;
	}

	public List<PagoDTO> getPagos() {
		return pagos;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
}
