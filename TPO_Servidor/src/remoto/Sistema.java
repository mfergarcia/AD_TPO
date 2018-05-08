//PENDIENTE: Programar toda la funcionalidad
package remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
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
	private Collection<UsuarioCliente> usuariosCliente;
	private Collection<UsuarioEmpleado> usuariosEmpleado;
	
	public Sistema() throws RemoteException {
		// TODO Auto-generated constructor stub
		cargarUsuariosCliente();
		cargarUsuariosEmpleado();
		admClientes = AdmClientes.getInstancia();
		admCompras = AdmCompras.getInstancia();
		admStock = AdmStock.getInstancia();
		admPedidos = AdmPedidos.getInstancia();
		admFacturacion = AdmFacturacion.getInstancia();
		
	}

	// Invoca al DAO, carga las UsuarioCliente existentes
	// de la BD en la colección usuariosCliente
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

	// Invoca al DAO, carga las UsuarioCliente existentes
	// de la BD en la colección usuariosCliente
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
	
	private UsuarioCliente buscarUsuarioCliente(String usuario) {
		UsuarioCliente aux;
		for (Iterator<UsuarioCliente> i = usuariosCliente.iterator(); i.hasNext(); ) {
			aux = i.next();
			if (aux.getUsuario().equals(usuario))
				return aux; 
		}
		return null;
	}
	
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
	public ClienteDTO loginCliente(String usuario, String pwd) throws RemoteException, ExcepcionSistema {
		ClienteDTO clienteDTO;
		UsuarioCliente usuarioCliente = buscarUsuarioCliente(usuario);
		if (usuarioCliente != null) {
			if (usuarioCliente.getPwd().equals(pwd)) {
				char tipoCliente = admClientes.obtenerTipoCliente(usuarioCliente.getIdCliente());
				if (tipoCliente == 'E')
					clienteDTO = admClientes.obtenerClienteEmpresa(usuarioCliente.getIdCliente()).toDTO();
				else {
					if (tipoCliente == 'P') 
						clienteDTO = admClientes.obtenerClientePersona(usuarioCliente.getIdCliente()).toDTO();
					else
						throw new ExcepcionSistema("Cliente no encontrado");
				}	
			}
			else
				throw new ExcepcionSistema("Password incorrecta");
		}
		else
			throw new ExcepcionSistema("Usuario no encontrado");
		return clienteDTO;
	}
	
	@Override
	public String loginEmpleado(String usuario, String pwd) throws RemoteException, ExcepcionSistema {
		String menu = "";
		UsuarioEmpleado usuarioEmpleado = buscarUsuarioEmpleado(usuario);
		if (usuarioEmpleado != null) {
			if ( usuarioEmpleado.getPwd().equals(pwd))
				menu = usuarioEmpleado.getMenu();
			else
				throw new ExcepcionSistema("Password incorrecta");
		}
		else {
			throw new ExcepcionSistema("Usuario no encontrado");
		}
		return menu;
	}

	/* Creo que no se necesita
	@Override
	public ClienteDTO buscarCliente(int idCliente) throws RemoteException, ExcepcionSistema {
		char tipoCliente = admClientes.obtenerTipoCliente(idCliente);
		if (tipoCliente == 'E') {
			ClienteEmpresa clienteEmpresa = admClientes.obtenerClienteEmpresa(idCliente);
			return clienteEmpresa.toDTO();
		}
		else {
			if (tipoCliente == 'P') {
				ClientePersona clientePersona = admClientes.obtenerClientePersona(idCliente);
				return clientePersona.toDTO();
			}
			else
				throw new ExcepcionSistema("Cliente no encontrado");
		}
	} 
	*/
	
	@Override
	public ClienteDTO altaClienteEmpresa(String cuit, String razonSocial, DireccionDTO direccionFacturacionDTO, char tipoFactura, String condicionesEspeciales, float limiteCredito) throws RemoteException, ExcepcionSistema {
		Direccion direccionFacturacion = new Direccion();
		direccionFacturacion.setCalle(direccionFacturacionDTO.getCalle());
		direccionFacturacion.setNumero(direccionFacturacionDTO.getNumero());
		direccionFacturacion.setLocalidad(direccionFacturacionDTO.getLocalidad());
		direccionFacturacion.setCodigoPostal(direccionFacturacionDTO.getCodigoPostal());
		ClienteEmpresa clienteEmpresa = admClientes.altaClienteEmpresa(cuit, razonSocial, direccionFacturacion, tipoFactura, condicionesEspeciales, limiteCredito);
		return clienteEmpresa.toDTO();
	}

	@Override
	public ClienteDTO altaClientePersona(String dni, String apellido, String nombre, DireccionDTO direccionFacturacionDTO, char tipoFactura, String condicionesEspeciales, float limiteCredito) throws RemoteException, ExcepcionSistema {
		// TODO Auto-generated method stub
		Direccion direccionFacturacion = new Direccion();
		direccionFacturacion.setCalle(direccionFacturacionDTO.getCalle());
		direccionFacturacion.setNumero(direccionFacturacionDTO.getNumero());
		direccionFacturacion.setLocalidad(direccionFacturacionDTO.getLocalidad());
		direccionFacturacion.setCodigoPostal(direccionFacturacionDTO.getCodigoPostal());
		ClientePersona clientePersona = admClientes.altaClientePersona(dni, apellido, nombre, direccionFacturacion, tipoFactura, condicionesEspeciales, limiteCredito);
		return clientePersona.toDTO();
	}

	@Override
	public void modificarDireccionCliente(int idCliente, DireccionDTO direccionDTO) throws RemoteException, ExcepcionSistema {
		if (!admClientes.modificarDireccionFacturacion(idCliente, direccionDTO.getCalle(), direccionDTO.getNumero(), direccionDTO.getLocalidad(), direccionDTO.getCodigoPostal()))
			throw new ExcepcionSistema("Cliente no encontrado");
	}

	@Override
	public void bajaCliente(int idCliente) throws RemoteException, ExcepcionSistema {
		if (!admClientes.bajaCliente(idCliente))
			throw new ExcepcionSistema("Cliente no encontrado");
	}

	@Override
	public ArticuloDTO altaArticulo(ArticuloDTO articuloDTO) throws RemoteException, ExcepcionSistema {
		Articulo articulo = admCompras.altaArticulo(articuloDTO.getCodigoBarras(), articuloDTO.getDescripcion(), articuloDTO.getPresentacion(), articuloDTO.getTamaño(), articuloDTO.getUnidad(), articuloDTO.getPrecioVta(), articuloDTO.getCantFijaCompra(), articuloDTO.getCantMaxUbicacion());
		return articulo.toDTO();
	}

	@Override
	public void modificarArticulo(String codBarras, String desc, String pres, int tamaño, String unidad, float precioVta, int cantFijaCompra, int cantMaxUbi) throws RemoteException, ExcepcionSistema {
		if (!admCompras.modificarArticulo(codBarras, desc, pres, tamaño, unidad, precioVta, cantFijaCompra, cantMaxUbi))
			throw new ExcepcionSistema("Articulo no encontrado");
	}

	@Override
	public void bajaArticulo(String codBarras) throws RemoteException, ExcepcionSistema {
		if (!admCompras.bajaArticulo(codBarras))
			throw new ExcepcionSistema("Articulo no encontrado");
	}

	@Override
	public Collection<ArticuloDTO> obtenerCatalogo() throws RemoteException, ExcepcionSistema {
		Collection<ArticuloDTO> catalogo = new ArrayList<ArticuloDTO>();
		Articulo aux;
		for (Iterator<Articulo> i = admStock.obtenerCatalogo().iterator(); i.hasNext(); ) {
			aux = i.next();
			catalogo.add(aux.toDTO());
		}
		return catalogo;
	}

	@Override
	public PedidoDTO generarPedido(PedidoDTO pedidoDTO) throws RemoteException, ExcepcionSistema {
		ItemArticulo item;
		Collection<ItemArticulo> articulos = new ArrayList<ItemArticulo>();
		ItemArticuloDTO aux;
		Direccion dirEntrega = new Direccion();
		dirEntrega.setCalle(pedidoDTO.getDirEntrega().getCalle());
		dirEntrega.setNumero(pedidoDTO.getDirEntrega().getNumero());
		dirEntrega.setLocalidad(pedidoDTO.getDirEntrega().getLocalidad());
		dirEntrega.setCodigoPostal(pedidoDTO.getDirEntrega().getCodigoPostal());
		// Recorre la coleccion de items del PedidoDTO y los convierte en items de negocio
		for (Iterator<ItemArticuloDTO> i = pedidoDTO.getArticulos().iterator(); i.hasNext(); ) {
			aux = i.next();
			item = new ItemArticulo();
			item.setArticulo(admStock.buscarArticulo(aux.getArticuloDTO().getCodigoBarras()));
			item.setCant(aux.getCant());
			item.setPrecioVta(aux.getPrecioVta());
			articulos.add(item);
		}
		Pedido pedido = admPedidos.generarPedido(pedidoDTO.getIdCliente(), dirEntrega, articulos);
		return pedido.toDTO();
	}

	@Override
	public Collection<PedidoDTO> obtenerPedidosPorCliente(int idCliente) throws RemoteException, ExcepcionSistema {
		Collection<Pedido> pedidosCliente;
		Collection<PedidoDTO> pedidosClienteDTO = new ArrayList<PedidoDTO>();
		pedidosCliente = admPedidos.obtenerPedidosPorCliente(idCliente);
		if (pedidosCliente != null && !pedidosCliente.isEmpty() ) {
			Pedido aux;
			for (Iterator<Pedido> i = pedidosCliente.iterator(); i.hasNext(); ) {
				aux = i.next();
				pedidosClienteDTO.add(aux.toDTO());
			}
		}
		else
			throw new ExcepcionSistema("No se registran Pedidos para el cliente dado");
		return pedidosClienteDTO;
	}

	@Override
	public Collection<PedidoDTO> obtenerPedidosAConfirmar() throws RemoteException, ExcepcionSistema {
		Collection<Pedido> pedidos;
		Collection<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		pedidos = admPedidos.obtenerPedidosAConfirmar();
		if (pedidos != null && !pedidos.isEmpty() ) {
			Pedido aux;
			for (Iterator<Pedido> i = pedidos.iterator(); i.hasNext(); ) {
				aux = i.next();
				pedidosDTO.add(aux.toDTO());
			}
		}
		else
			throw new ExcepcionSistema("No se registran Pedidos A Confirmar");
		return pedidosDTO;
	}

	@Override
	public String aprobarPedido(int numPedido) throws RemoteException, ExcepcionSistema {
		String nuevoEstadoPedido;
		nuevoEstadoPedido = admPedidos.aprobarPedido(numPedido);
		if (nuevoEstadoPedido != null) 
			return nuevoEstadoPedido;
		else
			throw new ExcepcionSistema("No se ha podido ejecutar la aprobacion del Pedido");
	}
	
}