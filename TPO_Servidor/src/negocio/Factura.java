// @Marce: revisar actualizacion Monton Adeudado
// @Facu: implementar saveMe
package negocio;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import dao.FacturaDAO;
import dto.FacturaDTO;
import entities.FacturaEntity;

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

	}

	public Factura(char tipoFactura, Pedido pedido) {
		this.setTipoFactura(tipoFactura);
		this.setPedido(pedido);
		// Se crea la factura con la fecha/hora del dia
		this.setFechaFactura(Calendar.getInstance().getTime());
		this.setMontoAdeudado(this.pedido.calcularTotal());
		this.setEstadoFactura("IMPAGA");
	}
	
	public Factura(FacturaEntity f) {
		this.setNumFactura(f.getIdFactura());
		this.setEstadoFactura(f.getEstadoFactura());
		this.setFechaFactura(f.getFechaFactura());
		this.setMontoAdeudado(f.getMontoAdeudado());
		this.setPedido(new Pedido(f.getPe()));
		this.setRemito(new Remito(f.getRe()));
		this.setTipoFactura(f.getTipoFactura());
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

	// Se crea como privado para preservar el control del Monto Adeudado a la clase Factura
	private void setMontoAdeudado(float montoAdeudado) {
		this.montoAdeudado = montoAdeudado;
	}
	
	public void modificarMonto(float montoAdeudado) {
		setMontoAdeudado(montoAdeudado);
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
	
	public void saveMe() {
		this.setNumFactura(FacturaDAO.getInstance().grabar(this));
	}	

	public void updateMe() {
		FacturaDAO.getInstance().update(this);
	}	

}
