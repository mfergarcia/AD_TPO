//PENDIENTE: Remover propiedades, implementar búsquedas a través del DAO
package remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import interfaces.InterfazRemota;
import controladores.AdmClientes;
import controladores.AdmCompras;
import controladores.AdmStock;
import controladores.AdmPedidos;
import controladores.AdmFacturacion;
import negocio.*;
import dto.*;
import excepciones.ExcepcionSistema;

public class Sistema extends UnicastRemoteObject implements InterfazRemota{

	private static final long serialVersionUID = 1L;

	private AdmClientes admClientes;
	private AdmCompras admCompras;
	private AdmStock admStock;
	private AdmPedidos admPedidos;
	private AdmFacturacion admFacturacion;
	//@Facu: remover colecciones de clientes cuando se puedan reemplazar las
	//busquedas en las colecciones por búsquedas en la BD.
	private Collection<UsuarioCliente> usuariosCliente;
	private Collection<UsuarioEmpleado> usuariosEmpleado;
	
	public Sistema() throws RemoteException {
		//@Facu: remover estas llamadas cuando se puedan reemplazar las 
		//busquedas en las colecciones por búsquedas en la BD.
		cargarUsuariosCliente();
		cargarUsuariosEmpleado();
	}

	//@Facu: remover este metodo cuando se puedan buscar los usuarios
	//en la BD
	private void cargarUsuariosCliente() {
		//NOTAS_FG: Solo para prueba ** REEMPLAZAR **
		this.usuariosCliente = new ArrayList<UsuarioCliente>();
		UsuarioCliente usuarioCliente = new UsuarioCliente();
		usuarioCliente.setUsuario("maria");
		usuarioCliente.setPwd("MARIA");
		usuarioCliente.setIdCliente(1);
		this.usuariosCliente.add(usuarioCliente);
		usuarioCliente = new UsuarioCliente();
		usuarioCliente.setUsuario("fernanda");
		usuarioCliente.setPwd("FERNANDA");
		usuarioCliente.setIdCliente(2);
		this.usuariosCliente.add(usuarioCliente);
	}

	//@Facu: remover este metodo cuando se puedan buscar los usuarios
	//en la BD
	private void cargarUsuariosEmpleado() {
		this.usuariosEmpleado = new ArrayList<UsuarioEmpleado>();
		UsuarioEmpleado usuarioEmpleado = new UsuarioEmpleado();
		usuarioEmpleado.setUsuario("user_clientes");
		usuarioEmpleado.setPwd("USER_CLIENTES");
		usuarioEmpleado.setMenu("ADM_CLIENTES");
		this.usuariosEmpleado.add(usuarioEmpleado);
		usuarioEmpleado = new UsuarioEmpleado();
		usuarioEmpleado.setUsuario("user_compras");
		usuarioEmpleado.setPwd("USER_COMPRAS");
		usuarioEmpleado.setMenu("ADM_COMPRAS");
		this.usuariosEmpleado.add(usuarioEmpleado);
		usuarioEmpleado = new UsuarioEmpleado();
		usuarioEmpleado.setUsuario("user_deposito");
		usuarioEmpleado.setPwd("USER_DEPOSITO");
		usuarioEmpleado.setMenu("ADM_DEPOSITO");
		this.usuariosEmpleado.add(usuarioEmpleado);	
		this.usuariosEmpleado.add(usuarioEmpleado);
		usuarioEmpleado = new UsuarioEmpleado();
		usuarioEmpleado.setUsuario("user_despacho");
		usuarioEmpleado.setPwd("USER_DESPACHO");
		usuarioEmpleado.setMenu("ADM_DESPACHO");
		this.usuariosEmpleado.add(usuarioEmpleado);
		usuarioEmpleado = new UsuarioEmpleado();
		usuarioEmpleado.setUsuario("user_facturacion");
		usuarioEmpleado.setPwd("USER_FACTURACION");
		usuarioEmpleado.setMenu("ADM_FACTURACION");
		this.usuariosEmpleado.add(usuarioEmpleado);
	}

	//@Facu: reemplazar esta busqueda en coleccion por busqueda en la BD
	private UsuarioCliente buscarUsuarioCliente(String usuario) {
		UsuarioCliente aux;
		for (Iterator<UsuarioCliente> i = usuariosCliente.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getUsuario().equals(usuario))
				return aux; 
		}
		return null;
	}

	//@Facu: reemplazar esta busqueda en coleccion por busqueda en la BD	
	private UsuarioEmpleado buscarUsuarioEmpleado(String usuario) {
		UsuarioEmpleado aux;
		for (Iterator<UsuarioEmpleado> i = usuariosEmpleado.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getUsuario().equals(usuario))
				return aux; 
		}	
		return null;
	}
	
	@Override
	public int loginCliente(String usuario, String pwd) throws RemoteException, ExcepcionSistema {
		UsuarioCliente usuarioCliente = buscarUsuarioCliente(usuario);
		if (usuarioCliente != null) {
			if (usuarioCliente.getPwd().equals(pwd))
				return usuarioCliente.getIdCliente();
			else
				throw new ExcepcionSistema("Password incorrecta");
		}
		else
			throw new ExcepcionSistema("Usuario no encontrado");
	}
	
	@Override
	public String loginEmpleado(String usuario, String pwd) throws RemoteException, ExcepcionSistema {
		UsuarioEmpleado usuarioEmpleado = buscarUsuarioEmpleado(usuario);
		if (usuarioEmpleado != null) {
			if (usuarioEmpleado.getPwd().equals(pwd))
				return usuarioEmpleado.getMenu();
			else
				throw new ExcepcionSistema("Password incorrecta");
		}
		else 
			throw new ExcepcionSistema("Usuario no encontrado");
	}

	@Override
	public ClienteEmpresaDTO altaClienteEmpresa(ClienteEmpresaDTO cteEmpresaDTO) throws RemoteException, ExcepcionSistema {
		return AdmClientes.getInstancia().altaClienteEmpresa(cteEmpresaDTO).toDTO();
	}

	@Override
	public ClientePersonaDTO altaClientePersona(ClientePersonaDTO ctePersonaDTO) throws RemoteException, ExcepcionSistema {
		return AdmClientes.getInstancia().altaClientePersona(ctePersonaDTO).toDTO();
	}

	@Override
	public char obtenerTipoCliente(int idCliente) throws RemoteException, ExcepcionSistema {
		char tipoCliente = AdmClientes.getInstancia().obtenerTipoCliente(idCliente);
		if (tipoCliente != 0)
			return tipoCliente;
		else
			throw new ExcepcionSistema("Cliente no encontrado");
	}

	@Override
	public ClienteEmpresaDTO obtenerCteEmpresa(int idCliente) throws RemoteException, ExcepcionSistema {
		return AdmClientes.getInstancia().obtenerClienteEmpresa(idCliente).toDTO();
	}

	@Override
	public ClientePersonaDTO obtenerCtePersona(int idCliente) throws RemoteException, ExcepcionSistema {
		return AdmClientes.getInstancia().obtenerClientePersona(idCliente).toDTO();
	}

	
	@Override
	public ClienteEmpresaDTO modificarCteEmpresa(ClienteEmpresaDTO cteEmpresaDTO) throws RemoteException, ExcepcionSistema {
		return AdmClientes.getInstancia().modificarCteEmpresa(cteEmpresaDTO).toDTO();
	}

	@Override
	public ClientePersonaDTO modificarCtePersona(ClientePersonaDTO ctePersonaDTO) throws RemoteException, ExcepcionSistema {
		return AdmClientes.getInstancia().modificarCtePersona(ctePersonaDTO).toDTO();
	}

	@Override
	public void bajaCliente(int idCliente) throws RemoteException, ExcepcionSistema {
		if (!AdmClientes.getInstancia().bajaCliente(idCliente))
			throw new ExcepcionSistema("Cliente no encontrado");
	}

	@Override
	public ArticuloDTO altaArticulo(ArticuloDTO articuloDTO) throws RemoteException, ExcepcionSistema {
		return AdmCompras.getInstancia().altaArticulo(articuloDTO).toDTO();
	}

	@Override
	public ArticuloDTO obtenerArticulo(String codBarras) throws RemoteException, ExcepcionSistema {
		Articulo articulo = AdmStock.getInstancia().obtenerArticulo(codBarras);
		if (articulo != null)
			return articulo.toDTO();
		else
			throw new ExcepcionSistema("Articulo no encontrado");
	}
	
	@Override
	public ArticuloDTO modificarArticulo(ArticuloDTO articuloDTO) throws RemoteException, ExcepcionSistema {
		return AdmCompras.getInstancia().modificarArticulo(articuloDTO).toDTO();
	}

	@Override
	public void bajaArticulo(String codBarras) throws RemoteException, ExcepcionSistema {
		if (!AdmCompras.getInstancia().bajaArticulo(codBarras))
			throw new ExcepcionSistema("Articulo no encontrado");
	}

	@Override
	public Collection<ArticuloDTO> obtenerCatalogo() throws RemoteException, ExcepcionSistema {
		Collection<ArticuloDTO> catalogo = new ArrayList<ArticuloDTO>();
		Articulo aux;
		for (Iterator<Articulo> i = AdmStock.getInstancia().obtenerCatalogo().iterator(); i.hasNext(); ) {
			aux = i.next();
			catalogo.add(aux.toDTO());
		}
		return catalogo;
	}

	@Override
	public PedidoDTO generarPedido(PedidoDTO pedidoDTO) throws RemoteException, ExcepcionSistema {
		return AdmPedidos.getInstancia().generarPedido(pedidoDTO).toDTO();
	}

	@Override
	public Collection<PedidoDTO> obtenerPedidosPorCliente(int idCliente) throws RemoteException, ExcepcionSistema {
		Collection<PedidoDTO> pedidosClienteDTO = new ArrayList<PedidoDTO>();
		Collection<Pedido> pedidosCliente = AdmPedidos.getInstancia().obtenerPedidosPorCliente(idCliente);
		if (pedidosCliente != null && !pedidosCliente.isEmpty()) {
			Pedido aux;
			for (Iterator<Pedido> i = pedidosCliente.iterator(); i.hasNext(); ) {
				aux = i.next();
				pedidosClienteDTO.add(aux.toDTO());
			}
			return pedidosClienteDTO;
		}
		else
			throw new ExcepcionSistema("No se registran Pedidos para el Cliente");

	}

	@Override
	public Collection<PedidoDTO> obtenerPedidosAConfirmar() throws RemoteException, ExcepcionSistema {
		Collection<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		Collection<Pedido> pedidos = AdmPedidos.getInstancia().obtenerPedidosAConfirmar();
		if (pedidos != null && !pedidos.isEmpty()) {
			Pedido aux;
			for (Iterator<Pedido> i = pedidos.iterator(); i.hasNext(); ) {
				aux = i.next();
				pedidosDTO.add(aux.toDTO());
			}
			return pedidosDTO;
		}
		else
			throw new ExcepcionSistema("No se registran Pedidos A Confirmar");

	}

	@Override
	public String aprobarPedido(int numPedido) throws RemoteException, ExcepcionSistema {
		String nuevoEstadoPedido = AdmPedidos.getInstancia().aprobarPedido(numPedido);
		if (nuevoEstadoPedido != null) 
			return nuevoEstadoPedido;
		else
			throw new ExcepcionSistema("No se ha podido completar la aprobacion del Pedido");
	}

	@Override	
	public String rechazarPedido(int numPedido, String motivo) throws RemoteException, ExcepcionSistema {
		String nuevoEstadoPedido = AdmPedidos.getInstancia().rechazarPedido(numPedido, motivo);
		if (nuevoEstadoPedido != null) 
			return nuevoEstadoPedido;
		else
			throw new ExcepcionSistema("No se ha podido completar el rechazo del Pedido");
	}

	@Override
	public Collection<PedidoDTO> obtenerPedidosCompletos() throws RemoteException, ExcepcionSistema {
		Collection<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		Collection<Pedido> pedidos = AdmPedidos.getInstancia().obtenerPedidosCompletos();
		if (pedidos != null && !pedidos.isEmpty() ) {
			Pedido aux;
			for (Iterator<Pedido> i = pedidos.iterator(); i.hasNext(); ) {
				aux = i.next();
				pedidosDTO.add(aux.toDTO());
			}
			return pedidosDTO;
		}
		else
			throw new ExcepcionSistema("No se registran Pedidos Completos");
	}

	@Override
	public String solicitarPedido(int numPedido) throws RemoteException, ExcepcionSistema {
		String nuevoEstadoPedido = AdmPedidos.getInstancia().solicitarPedido(numPedido);
		if (nuevoEstadoPedido != null) 
			return nuevoEstadoPedido;
		else
			throw new ExcepcionSistema("No se ha podido ejecutar la solicitud del Pedido");
	}

	@Override
	public Collection<PedidoDTO> obtenerPedidosPendDeposito() throws RemoteException, ExcepcionSistema {
		Collection<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		Collection<Pedido> pedidos = AdmPedidos.getInstancia().obtenerPedidosPendDeposito();
		if (pedidos != null && !pedidos.isEmpty() ) {
			Pedido aux;
			for (Iterator<Pedido> i = pedidos.iterator(); i.hasNext(); ) {
				aux = i.next();
				pedidosDTO.add(aux.toDTO());
			}
			return pedidosDTO;
		}
		else
			throw new ExcepcionSistema("No se registran Pedidos Pendientes de Deposito");
	}

	@Override
	public Collection<ArticuloEnStockDTO> prepararPedido(int numPedido) throws RemoteException, ExcepcionSistema {
		Collection<ArticuloEnStockDTO> artEnStockDTO = new ArrayList<ArticuloEnStockDTO>();
		Collection<ArticuloEnStock> artEnStock = AdmPedidos.getInstancia().prepararPedido(numPedido);
		ArticuloEnStock aux;
		for (Iterator<ArticuloEnStock> i = artEnStock.iterator(); i.hasNext(); ) {
			aux = i.next();
			artEnStockDTO.add(aux.toDTO());
		}
		return artEnStockDTO;
	}

	//NOTAS_FG: Pendiente de programar
	@Override
	public String actualizarStockPorVenta(int numPedido, Collection<ArticuloEnStockDTO> artEnStockDTO) throws RemoteException, ExcepcionSistema {
		String nuevoEstadoPedido = AdmPedidos.getInstancia().actualizarStockPorVenta(numPedido, artEnStockDTO);
		if (nuevoEstadoPedido != null) 
			return nuevoEstadoPedido;
		else
			throw new ExcepcionSistema("No se ha podido actualizar el stock del Pedido");
	}
	
	@Override
	public Collection<PedidoDTO> obtenerPedidosADespachar() throws RemoteException, ExcepcionSistema {
		Collection<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		Collection<Pedido>	pedidos = AdmPedidos.getInstancia().obtenerPedidosADespachar();
		if (pedidos != null && !pedidos.isEmpty() ) {
			Pedido aux;
			for (Iterator<Pedido> i = pedidos.iterator(); i.hasNext(); ) {
				aux = i.next();
				pedidosDTO.add(aux.toDTO());
			}
		}
		else
			throw new ExcepcionSistema("No se registran Pedidos A Despachar");
		return pedidosDTO;
	}

	@Override
	public String registrarFechaEntrega(int numPedido, Date fechaEntrega) throws RemoteException, ExcepcionSistema {
		String nuevoEstadoPedido = AdmPedidos.getInstancia().registrarFechaEntrega(numPedido, fechaEntrega);
		if (nuevoEstadoPedido != null) 
			return nuevoEstadoPedido;
		else
			throw new ExcepcionSistema("No se ha podido registrar la fecha de entrega del Pedido");
	}

	@Override
	public Collection<OrdenPedidoRepoDTO> obtenerOPRPendientes() throws RemoteException, ExcepcionSistema {
		Collection<OrdenPedidoRepoDTO> ordenPRDTO = new ArrayList<OrdenPedidoRepoDTO>();
		Collection<OrdenPedidoRepo> ordenPR = AdmCompras.getInstancia().obtenerOPRPendientes();
		if (ordenPR != null && !ordenPR.isEmpty() ) {
			OrdenPedidoRepo aux;
			for (Iterator<OrdenPedidoRepo> i = ordenPR.iterator(); i.hasNext(); ) {
				aux = i.next();
				ordenPRDTO.add(aux.toDTO());
			}
			return ordenPRDTO;
		}
		else
			throw new ExcepcionSistema("No se registran Ordenes de Pedido de Reposición Pendientes");
	}

	// NOTA_FG: Pendiente programar
	@Override
	public OrdenDeCompraDTO generarOrdenDeCompra(String proveedor, Collection<ArticuloDTO> articulos)
			throws RemoteException, ExcepcionSistema {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public OrdenDeCompraDTO procesarOrdenDeCompra(int numOC) throws RemoteException, ExcepcionSistema {
		OrdenDeCompra ordenDeCompra = admCompras.buscarOrdenDeCompra(numOC);
		if (ordenDeCompra != null)
			return ordenDeCompra.toDTO();
		else
			throw new ExcepcionSistema("Orden de Compra no encontrada");
	}
	
	@Override
	public Collection<ArticuloEnStockDTO> cargarArticuloEnStock(int numOC, String codBarras, int cantidad, String lote, Date fechaVenc, String proveedor, float precioCompra) throws RemoteException, ExcepcionSistema {
		Collection<ArticuloEnStockDTO> artEnStockDTO = new ArrayList<ArticuloEnStockDTO>();
		Collection<ArticuloEnStock> artEnStock = admStock.cargarArticuloEnStock(numOC, codBarras, cantidad, lote, fechaVenc, proveedor, precioCompra);
		if (artEnStock != null && !artEnStock.isEmpty() ) {
			ArticuloEnStock aux;
			for (Iterator<ArticuloEnStock> i = artEnStock.iterator(); i.hasNext(); ) {
				aux = i.next();
				artEnStockDTO.add(aux.toDTO());
			}
		}
		else
			throw new ExcepcionSistema("No se ha podido cargar el stock del articulo");
		return artEnStockDTO;
		}

	public String cumplirOrdenDeCompra(int numOC) throws RemoteException, ExcepcionSistema {
		String nuevoEstadoOC = admCompras.cumplirOrdenDeCompra(numOC);
		if (nuevoEstadoOC != null)
			return nuevoEstadoOC;
		else
			throw new ExcepcionSistema("Orden de Compra no encontrada");
	}




}