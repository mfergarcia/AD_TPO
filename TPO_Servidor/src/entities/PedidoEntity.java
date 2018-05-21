package entities;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import negocio.ItemArticulo;
import negocio.Pedido;

@Entity
@Table (name="Pedidos")
public class PedidoEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer numPedido;
	private Integer idCliente;
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="idDireccion")
	private DireccionEntity dirEntrega;
	private Date fechaGen;
	private Date fechaEntrega;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="numPedido")
	private List<ItemArticuloEntity> iae;
	private char tipoFactura;
	private Integer numFactura;
	private String motivoRechazo;
	private String estado;
	
	public PedidoEntity(){}
	
	public PedidoEntity(Integer numPedido, Integer idCliente, DireccionEntity dirEntrega, Date fechaEntrega, char tipoFactura, Integer numFactura, String motivoRechazo, String estado) {
		super();
		this.setNumPedido(numPedido);
		this.setFechaGen((Date)Calendar.getInstance().getTime());
		this.setIdCliente(idCliente);
		this.setDirEntrega(dirEntrega);
		this.setFechaEntrega(fechaEntrega);
		this.setTipoFactura(tipoFactura);
		this.setNumFactura(numFactura);
		this.setMotivoRechazo(motivoRechazo);
		this.setEstado(estado);
	}
	
	public PedidoEntity(Pedido pedido) {
		this.setFechaGen((Date)Calendar.getInstance().getTime());
		this.setIdCliente(pedido.getIdCliente());
		this.setDirEntrega(new DireccionEntity(pedido.getDirEntrega()));
		this.setFechaEntrega(pedido.getFechaEntrega());
		this.setTipoFactura(pedido.getTipoFactura());
		this.setNumFactura(pedido.getNumFactura());
		this.setMotivoRechazo(pedido.getMotivoRechazo());
		this.setEstado(pedido.getEstado());
		this.cargarList(pedido.getArticulos());
	}

	private void cargarList(List<ItemArticulo> ia){
		List<ItemArticuloEntity> res= new ArrayList<ItemArticuloEntity>();
		for(ItemArticulo a: ia)
			res.add(new ItemArticuloEntity(a));
		this.setIae(res);
	}
	
	public List<ItemArticuloEntity> getIae() {
		return iae;
	}

	public void setIae(List<ItemArticuloEntity> iae) {
		this.iae = iae;
	}

	public Integer getNumPedido() {
		return numPedido;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public DireccionEntity getDirEntrega() {
		return dirEntrega;
	}

	public void setDirEntrega(DireccionEntity dirEntrega) {
		this.dirEntrega = dirEntrega;
	}

	public Date getFechaGen() {
		return fechaGen;
	}


	public void setFechaGen(Date fechaGen) {
		this.fechaGen = fechaGen;
	}


	public Date getFechaEntrega() {
		return fechaEntrega;
	}


	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}


	public char getTipoFactura() {
		return tipoFactura;
	}


	public void setTipoFactura(char tipoFactura) {
		this.tipoFactura = tipoFactura;
	}


	public Integer getNumFactura() {
		return numFactura;
	}


	public void setNumFactura(Integer numFactura) {
		this.numFactura = numFactura;
	}


	public String getMotivoRechazo() {
		return motivoRechazo;
	}


	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public void setNumPedido(Integer numPedido) {
		this.numPedido = numPedido;
	}
	
}


