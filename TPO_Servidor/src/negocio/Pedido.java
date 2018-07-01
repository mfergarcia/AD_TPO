package negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import controladores.AdmCompras;
import controladores.AdmStock;
import dao.PedidoDAO;
import dto.PedidoDTO;
import entities.FacturaEntity;
import entities.ItemArticuloEntity;
import entities.PedidoEntity;

public class Pedido {

	private int numPedido;
	private Cliente cliente;
	private Direccion dirEntrega;
	private Date fechaEntrega;
	private List<ItemArticulo> articulos;
	private Factura factura;
	private String motivoRechazo;
	private Date fechaGen;
	private Date fechaRechazo;
	private Date fechaPendRepo;
	private Date fechaCompleto;
	private Date fechaPendDepo;
	private Date fechaPendDesp;
	private Date fechaDespachado;
	// estado: "A CONFIRMAR", "RECHAZADO", "PENDIENTE REPOSICION", "COMPLETO", "PENDIENTE DEPOSITO", "PENDIENTE DESPACHO", "DESPACHADO"
	private String estado;	
	
	public Pedido() {

	}

	// Creación de un nuevo Pedido, inicializa la coleccion de items y setea estado inicial
	public Pedido(Cliente cliente, Direccion dirEntrega) {
		this.setCliente(cliente);
		this.setDirEntrega(dirEntrega);
		this.articulos = new ArrayList<ItemArticulo>();
		this.setFechaGen(null);
		this.setFechaRechazo(null);
		this.setFechaPendRepo(null);
		this.setFechaCompleto(null);
		this.setFechaPendDepo(null);
		this.setFechaPendDesp(null);
		this.setFechaDespachado(null);
		this.setEstado("A CONFIRMAR");
	}
	
	// @Facu: adaptar PedidoEntity, ahora guarda el objeto Cliente y el objeto Factura
	public Pedido(PedidoEntity pe) {
		this.setCliente(new Cliente(pe.getCliente()));
		this.setDirEntrega(new Direccion(pe.getDirEntrega()));
		this.setEstado(pe.getEstado());
		this.setFechaEntrega(pe.getFechaEntrega());
		this.setFechaGen(pe.getFechaGen());
		this.setMotivoRechazo(pe.getMotivoRechazo());
		if (pe.getFactura() != null)
			this.setFactura(new Factura(pe.getFactura()));
		else
			this.setFactura(null);
		this.setFechaCompleto(pe.getFechaCompleto());
		this.setFechaDespachado(pe.getFechaDespachado());
		this.setFechaPendDepo(pe.getFechaPendDepo());
		this.setFechaPendDesp(pe.getFechaPendDesp());
		this.setFechaPendRepo(pe.getFechaPendRepo());
		this.setFechaRechazo(pe.getFechaRechazo());
		if(pe.getNumPedido()>0)
			this.setNumPedido(pe.getNumPedido());
		this.cargarList(pe.getIae());
	}
	
	
	private void cargarList(List<ItemArticuloEntity> ia){
		List<ItemArticulo> res= new ArrayList<ItemArticulo>();
		for(ItemArticuloEntity a: ia)
			res.add(new ItemArticulo(a));
		this.setArticulos(res);
	}
	
	// Permite agregar los items del Pedido
	public void agregarItem(ItemArticulo itemArticulo) {
		this.articulos.add(itemArticulo);
	}
	
	// Calcula el monto total del Pedido, sumarizando el monto de cada item
	public float calcularTotal() {
		float total = 0;
		ItemArticulo aux;
		for (Iterator<ItemArticulo> i = this.getArticulos().iterator(); i.hasNext(); ) {
			aux = i.next();
			total = total + aux.calcularTotal();
		}
		return total;
	}

	public String reservarStockPedido() {
		String estadoPedido = "COMPLETO";
		Collection<ArticuloEnStock> artEnStock;
		ItemArticulo auxItemArt;
		// Recorre los articulos del pedido para tratar de reservar el stock
		for (Iterator<ItemArticulo> i = this.getArticulos().iterator(); i.hasNext(); ) {
			auxItemArt = i.next();
			if (!auxItemArt.getEstadoStock().equals("RESERVADO")) {
				// El ítem aún no pudo ser reservado
				// Obtiene los Articulos en Stock ordenados por Fecha de Vencimiento
				artEnStock = auxItemArt.getArticulo().obtenerArtEnStockOrdenFV();
				int cantRequerida = auxItemArt.getCant();
				int cantReservable;
				ArticuloEnStock auxArtEnStock;
				// Recorre los Articulos en Stock para ver si se puede reservar la cantidad
				// requerida del Articulo
				Iterator<ArticuloEnStock> j = artEnStock.iterator();
				while (cantRequerida > 0 && j.hasNext()) {
					auxArtEnStock = j.next();
					// Obtiene el Stock actual del Articulo En Stock
					Stock stock = AdmStock.getInstancia().obtenerStock(auxArtEnStock.getCodigoUbicacion());
					if (stock != null) {
						cantReservable = stock.cantidadReservableEnStock();
						if (cantReservable > 0) {
							// Hay stock para reservar, necesita comparar cuanto hay vs lo que se necesita
							if (cantReservable >= cantRequerida) {
								// Reserva solo lo que se necesita
								if (stock.reservarStock(cantRequerida)) {
									stock.updateMe();
									cantRequerida = 0;
								}	
							}
							else {
								// Reserva todo lo que hay y sigue buscando más cantidad en el siguiente 
								// Articulo En Stock
								if (stock.reservarStock(cantReservable)) {
									stock.updateMe();
									cantRequerida = cantRequerida - cantReservable;
								}	
							}
						}
					}
				}
				// Verifica si quedó cantidad pendiente por reservar o si se acabaron los Articulos En Stock
				if (cantRequerida > 0) {
					// Ya recorrió todos los Articulos En Stock y aún queda cantidad pendiente por reservar
					// Genera entonces una Orden de Pedido de Reposicion por la cantidad faltante
					AdmCompras.getInstancia().generarOrdenPedidoRepo(this.getNumPedido(), auxItemArt.getArticulo(), cantRequerida);
					auxItemArt.setEstadoStock("PENDIENTE OPR");
					estadoPedido = "PENDIENTE REPOSICION";
				}
				else
					auxItemArt.setEstadoStock("RESERVADO");
				auxItemArt.updateMe();
			}
		}
		return estadoPedido;
	}
	
	public int getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public void setArticulos(List<ItemArticulo> articulos) {
		this.articulos = articulos;
	}

	public Date getFechaGen() {
		return fechaGen;
	}

	public void setFechaGen(Date fechaGen) {
		this.fechaGen = fechaGen;
	}

	public Direccion getDirEntrega() {
		return dirEntrega;
	}

	public void setDirEntrega(Direccion dirEntrega) {
		this.dirEntrega = dirEntrega;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}	
	
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public List<ItemArticulo> getArticulos() {
		return articulos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
		if (estado.equals("A CONFIRMAR"))
			this.setFechaGen(Calendar.getInstance().getTime());	
		else if (estado.equals("RECHAZADO"))
			this.setFechaRechazo(Calendar.getInstance().getTime());	
		else if (estado.equals("PENDIENTE REPOSICION"))
			this.setFechaPendRepo(Calendar.getInstance().getTime());		
		else if (estado.equals("COMPLETO"))
			this.setFechaCompleto(Calendar.getInstance().getTime());
		else if (estado.equals("PENDIENTE DEPOSITO"))
			this.setFechaPendDepo(Calendar.getInstance().getTime());
		else if (estado.equals("PENDIENTE DESPACHO"))
			this.setFechaPendDesp(Calendar.getInstance().getTime());
		else if (estado.equals("DESPACHADO"))
			this.setFechaDespachado(Calendar.getInstance().getTime());
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

	public PedidoDTO toDTO() {
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setNumPedido(this.getNumPedido());
		pedidoDTO.setIdCliente(this.getCliente().getIdCliente());
		pedidoDTO.setDirEntrega(this.getDirEntrega().toDTO());
		pedidoDTO.setFechaEntrega(this.getFechaEntrega());
		if (this.getFactura() != null)
			pedidoDTO.setFactura(this.getFactura().toDTO());
		else
			pedidoDTO.setFactura(null);
		pedidoDTO.setMotivoRechazo(this.getMotivoRechazo());
		pedidoDTO.setFechaGen(this.getFechaGen());
		pedidoDTO.setFechaRechazo(this.getFechaRechazo());
		pedidoDTO.setFechaPendRepo(this.getFechaPendRepo());
		pedidoDTO.setFechaCompleto(this.getFechaCompleto());
		pedidoDTO.setFechaPendDepo(this.getFechaPendDepo());
		pedidoDTO.setFechaPendDesp(this.getFechaPendDesp());
		pedidoDTO.setFechaDespachado(this.getFechaDespachado());
		pedidoDTO.setEstado(this.getEstado());
		ItemArticulo aux=new ItemArticulo();
		for(Iterator<ItemArticulo> i = this.getArticulos().iterator(); i.hasNext(); ) {
			aux = i.next();
			pedidoDTO.agregarItem(aux.toDTO());
		}
		return pedidoDTO;
	}
	
	public void saveMe() {
		this.setNumPedido(PedidoDAO.getIntance().grabar(this));
	}	
	

	public void updateMe() {
		PedidoDAO.getIntance().update(this);
	}	

}
