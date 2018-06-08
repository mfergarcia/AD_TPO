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

import negocio.Cliente;
import negocio.ItemArticulo;
import negocio.Pedido;

@Entity
@Table (name="Pedidos")
public class PedidoEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer numPedido;
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="idCliente")
	private ClienteEntity cliente;
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="idDireccion")
	private DireccionEntity dirEntrega;
	private Date fechaGen;
	private Date fechaEntrega;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="numPedido")
	private List<ItemArticuloEntity> iae;
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="idFactura")
	private FacturaEntity factura;
	private String motivoRechazo;
	private String estado;
	private Date fechaRechazo;
	private Date fechaPendRepo;
	private Date fechaCompleto;
	private Date fechaPendDepo;
	private Date fechaPendDesp;
	private Date fechaDespachado;
	
	public PedidoEntity(){}
	/*
	public PedidoEntity(Integer numPedido, ClienteEntity cliente, DireccionEntity dirEntrega, Date fechaEntrega, char tipoFactura, Integer numFactura, String motivoRechazo, String estado) {
		super();
		this.setNumPedido(numPedido);
		this.setFechaGen((Date)Calendar.getInstance().getTime());
		this.setCliente(cliente);
		this.setDirEntrega(dirEntrega);
		this.setFechaEntrega(fechaEntrega);
		this.setTipoFactura(tipoFactura);
		this.setNumFactura(numFactura);
		this.setMotivoRechazo(motivoRechazo);
		this.setEstado(estado);
	}
	*/
	public PedidoEntity(Pedido pedido) {
		if(pedido.getNumPedido()>0)
			this.setNumPedido(pedido.getNumPedido());
		this.setFechaGen((Date)Calendar.getInstance().getTime());
		this.setCliente(new ClienteEntity(pedido.getCliente()));
		this.setDirEntrega(new DireccionEntity(pedido.getDirEntrega()));
		this.setFechaEntrega(pedido.getFechaEntrega());
		this.setFactura(new FacturaEntity(pedido.getFactura()));
		this.setMotivoRechazo(pedido.getMotivoRechazo());
		this.setEstado(pedido.getEstado());
		this.setFechaCompleto(pedido.getFechaCompleto());
		this.setFechaDespachado(pedido.getFechaDespachado());
		this.setFechaPendDepo(pedido.getFechaPendDepo());
		this.setFechaPendDesp(pedido.getFechaPendDesp());
		this.setFechaPendRepo(pedido.getFechaPendRepo());
		this.setFechaRechazo(pedido.getFechaRechazo());
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

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
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

	
	public FacturaEntity getFactura() {
		return factura;
	}


	public void setFactura(FacturaEntity factura) {
		this.factura = factura;
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
	public Date getFechaRechazo() {
		return fechaRechazo;
	}
	public void setFechaRechazo(Date fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}
	public Date getFechaPendRepo() {
		return fechaPendRepo;
	}
	public void setFechaPendRepo(Date fechaPendRepo) {
		this.fechaPendRepo = fechaPendRepo;
	}
	public Date getFechaCompleto() {
		return fechaCompleto;
	}
	public void setFechaCompleto(Date fechaCompleto) {
		this.fechaCompleto = fechaCompleto;
	}
	public Date getFechaPendDepo() {
		return fechaPendDepo;
	}
	public void setFechaPendDepo(Date fechaPendDepo) {
		this.fechaPendDepo = fechaPendDepo;
	}
	public Date getFechaPendDesp() {
		return fechaPendDesp;
	}
	public void setFechaPendDesp(Date fechaPendDesp) {
		this.fechaPendDesp = fechaPendDesp;
	}
	public Date getFechaDespachado() {
		return fechaDespachado;
	}
	public void setFechaDespachado(Date fechaDespachado) {
		this.fechaDespachado = fechaDespachado;
	}
	
	
}


