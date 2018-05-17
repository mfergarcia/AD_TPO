//PENDIENTE: Revisar si está ok la actualización de montos
package negocio;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import dto.FacturaDTO;

public class Factura {

	private char tipoFactura;
	private int numFactura;
	private Date fechaFactura;
	private Pedido pedido;
	private Remito remito;
	private float montoAdeudado;
	// estadoFactura: "IMPAGA", "PAGO PARCIAL", "PAGADA"
	private String estadoFactura;
	
	public Factura() {
		// TODO Auto-generated constructor stub
	}

	public Factura(char tipoFactura, Pedido pedido) {
		this.setTipoFactura(tipoFactura);
		this.setPedido(pedido);
		// Se crea la factura con la fecha/hora del dia
		this.setFechaFactura(Calendar.getInstance().getTime());
		this.setMontoAdeudado(this.pedido.calcularTotal());
		this.setEstadoFactura("IMPAGA");
	}
	
	// Si el monto adeudado es 0 la factura cambia de estado
	public void actualizarMontoAdeudado(float monto){
		if (monto == 0)
			this.setEstadoFactura("PAGADA");
		else 
			this.setEstadoFactura("PAGO PARCIAL");
		this.setMontoAdeudado(monto);
	}
	
	public float calcularTotal() {
		return this.getPedido().calcularTotal();
	}
		
	public char getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(char tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public int getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(int numFactura) {
		this.numFactura = numFactura;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	
	public float getMontoAdeudado() {
		return montoAdeudado;
	}

	public void setMontoAdeudado(float montoAdeudado) {
		this.montoAdeudado = montoAdeudado;
	}

	public String getEstadoFactura() {
		return estadoFactura;
	}

	public void setEstadoFactura(String estadoFactura) {
		this.estadoFactura = estadoFactura;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Remito getRemito() {
		return remito;
	}

	public void setRemito(Remito remito) {
		this.remito = remito;
	}

	public FacturaDTO toDTO() {
		FacturaDTO facturaDTO = new FacturaDTO();
		facturaDTO.setTipoFactura(this.getTipoFactura());
		facturaDTO.setNumFactura(this.getNumFactura());
		facturaDTO.setFechaFactura(this.getFechaFactura());
		facturaDTO.setNumPedido(this.getPedido().getNumPedido());
		facturaDTO.setRemitoDTO(this.getRemito().toDTO());
		facturaDTO.setMontoAdeudado(this.getMontoAdeudado());
		facturaDTO.setEstadoFactura(this.getEstadoFactura());
		ItemArticulo aux;
		for(Iterator<ItemArticulo> i = (this.getPedido().getArticulos()).iterator(); i.hasNext(); ) {
			aux = i.next();
			facturaDTO.agregarItem(aux.toDTO());
		}
		return facturaDTO;
	}
	
	//@Facu: implementar metodo
	public void saveMe() {

	}	
}
