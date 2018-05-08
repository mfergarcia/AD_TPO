//PENDIENTE: Completar programacion y desde VERRRR
package controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import negocio.Direccion;
import negocio.ItemArticulo;
import negocio.Pedido;

public class AdmPedidos {

	private static AdmPedidos instancia;
	private int numeradorPedidos;
	private Collection<Pedido> pedidos;
	
	// Constructor privado (Patron Singleton)
	private AdmPedidos() {
		// TODO Auto-generated constructor stub
		// Inicializar controlador
		cargarPedidos();
	}

	// Inicializa la coleccion pedidos, invoca al DAO y carga los Pedidos existentes
	// de la BD en dicha coleccion. Tambien setea el numeradorPedidos con el max(numPedido) + 1
	private void cargarPedidos() {
		//NOTAS_FG: Solo para pruebas **REEMPLAZAR**;
		this.numeradorPedidos = 1;
	}
	
	// Devuelve los pedidos cuyo estado coincide con el estado solicitado
	private Collection<Pedido> obtenerPedidosPorEstado(String estado) {
		Collection<Pedido> pedidos = new ArrayList<Pedido>();
		Pedido aux;
		for (Iterator<Pedido> i = this.pedidos.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getEstado().equals(estado)) {
				pedidos.add(aux);
			}
		}
		return pedidos;
	}

	// Ubica un determinado Pedido dentro de la colleción pedidos
	private Pedido obtenerPedido(int numPedido) {
		Pedido aux;
		for (Iterator<Pedido> i = this.pedidos.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getNumPedido() == numPedido) {
				return aux;
			}
		}
		return null;
	}
	
	public static AdmPedidos getInstancia() {
		if (instancia == null) {
			instancia = new AdmPedidos();
		}
		return instancia;
	}	

	// Crea un nuevo Pedido con el numeradorPedidos, incrementa el numeradorPedidos, 
	// agrega el Pedido a la coleccion pedidos y devuelve el objeto Pedido
	public Pedido generarPedido(int idCliente, Direccion direccionEntrega, Collection<ItemArticulo> items) {
		Pedido pedido = new Pedido(this.numeradorPedidos, idCliente, direccionEntrega);
		numeradorPedidos++;
		ItemArticulo aux;
		for (Iterator<ItemArticulo> i = items.iterator(); i.hasNext(); ) {
			aux = i.next();
			pedido.agregarItem(aux);
		}
		this.pedidos.add(pedido);
		return pedido;
	}
	
	// Devuelve una coleccion con los Pedidos correspondiente al idCliente dado
	public Collection<Pedido> obtenerPedidosPorCliente(int idCliente) {
		Collection<Pedido> pedidosCliente = new ArrayList<Pedido>();
		Pedido aux;
		for(Iterator<Pedido> i = this.pedidos.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getIdCliente() == idCliente)
				pedidosCliente.add(aux);
		}
		return pedidosCliente;
	}

	// Devuelve los pedidos en estado "A CONFIRMAR"
	public Collection<Pedido> obtenerPedidosAConfirmar() {
		return obtenerPedidosPorEstado("A CONFIRMAR");
	}
	
	public String aprobarPedido(int numPedido) {
		Pedido pedido = obtenerPedido(numPedido);
		String nuevoEstadoPedido;
		if (pedido != null) {
			nuevoEstadoPedido = AdmStock.getInstancia().reservarStockPedido(pedido);
			pedido.setEstado(nuevoEstadoPedido);
			return nuevoEstadoPedido;
		}
		else
			return null;
	}
	
}
